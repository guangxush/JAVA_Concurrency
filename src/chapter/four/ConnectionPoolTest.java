package chapter.four;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(10);
    //保证所有的ConnectionRunner能够同时开始
    static CountDownLatch start = new CountDownLatch(1);
    //main线程将会等待所有的ConnectionRunner结束才继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws Exception{
        //线程数量，可以修改线程数量进行观察
        int threadCount = 10;
        end = new CountDownLatch(threadCount);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for(int i = 0;i<threadCount;i++){
            Thread thread = new Thread(new ConnectionRunner(count,got,notGot),"ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();//同时被唤醒
        end.await();
        System.out.println("total invoke:"+(threadCount*count));
        System.out.println("got connection:"+got);
        System.out.println("not got connection"+notGot);
    }
    static class ConnectionRunner implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try{
                start.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            while(count>0){
                try{
                    //分别从线程池中获取连接，如果1000ms内无法获取，将返回null
                    //分别统计got和notGot的数量
                    Connection connection = pool.fetchConnection(1000);
                    if(connection!=null){
                        try{
                            connection.createStatement();
                            connection.commit();
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else{
                        notGot.incrementAndGet();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    count --;
                }
            }
            end.countDown();
        }
    }
    /**
     * CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。
     * 使用一个计数器进行实现。计数器初始值为线程的数量。
     * 当每一个线程完成自己任务后，计数器的值就会减一。
     * 当计数器的值为0时，表示所有的线程都已经完成了任务，然后在CountDownLatch上等待的线程就可以恢复执行任务。
     */
}
