package chapter.four;

import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by gshan on 2018/9/14
 */
public class Priority {
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws Exception{
        List<Job> jobs = new ArrayList<>();
        for(int i=0;i<10;i++){
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job,"Thread"+i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;
        for(Job job:jobs){
            System.out.println("Job Priority:"+job.priority+",Count:"+job.jobCount);
        }
    }

    static class Job implements Runnable{
        private int priority;
        private long jobCount;
        public Job(int priority){
            this.priority = priority;
        }
        public void run(){
            while(notStart){
                Thread.yield();//礼让方法
                //使当前线程从执行状态（运行状态）变为可执行态（就绪状态）
            }
            while(notEnd){
                Thread.yield();
                jobCount++;
            }
        }
    }
}
