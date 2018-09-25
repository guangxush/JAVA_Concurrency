package chapter.four;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    //线程池最大限制数
    private static final int MAX_WORKER_NUMBERS = 10;
    //线程池默认的数量
    private static final int DEFAULT_WORKER_NUMBERS= 5;
    //线程池最小的数量
    private static final int MIN_WORKER_NUMBERS = 1;
    //这是一个工作列表，将会在里面插入工作
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    //工作者列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    //工作者线程数量
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    //线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool(int num){
        workerNum = num > MAX_WORKER_NUMBERS?MAX_WORKER_NUMBERS:num<MIN_WORKER_NUMBERS?MIN_WORKER_NUMBERS:num;
        initializeWorkers(workerNum);
    }

    public void execute(Job job){
        if(job!=null){
            //添加一个工作，然后进行通知
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }
    public void shutdown(){
        for(Worker worker:workers){
            worker.shutdown();
        }
    }
    public void addWorkers(int num){
        synchronized (jobs){
            //限制新增的Worker数量不能超过最大值
            if(num+this.workerNum>MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS-this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum+=num;
        }
    }
    public void removeWorker(int num) throws IllegalAccessException {
        synchronized (jobs){
            if(num>=this.workerNum){
                throw new IllegalAccessException("beyond workNum");
            }
            //按照给定的数量停止线程
            int count = 0;
            while(count<num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }
    public int getJobSize(){
        return jobs.size();
    }
    //初始化线程工作者
    private void initializeWorkers(int num){
        for(int i=0;i<num;i++){
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker,"ThreadPool-Worker-"+threadNum.incrementAndGet());
            thread.start();
        }
    }

    //工作者，负责消费任务
    class Worker implements Runnable{
        //是否工作
        private volatile boolean runnuing = true;
        @Override
        public void run() {
            while(runnuing){
                Job job = null;
                synchronized (jobs){
                    //如果工作列表时空的，那么等待、
                    try{
                        jobs.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                        //感知到外部对workThread的中断操作，返回
                        Thread.currentThread().interrupt();
                        return;
                    }
                    //取出一个job
                    job = jobs.removeFirst();
                }
                if(job!=null){
                    try{
                        job.run();
                    }catch(Exception e){
                        //忽略Job执行的exception
                        e.printStackTrace();
                    }
                }
            }
        }
        public void shutdown(){
            runnuing = false;
        }
    }
}
