package chapter.four;

import java.util.concurrent.TimeUnit;

public class Join {
    //当线程A等待thread线程终止之后，采用thread.join返回
    public static void main(String[] args) throws Exception{
        Thread prevoious = Thread.currentThread();
        for(int i=0;i<10;i++){
            //每个线程拥有一前一个线程的引用，需要当前一个线程终止之后，才能从等待中返回
            Thread thread = new Thread(new Domino(prevoious),String.valueOf(i));
            thread.start();
            prevoious = thread;
        }
        TimeUnit.SECONDS.sleep(5);//会终止当前线程
        System.out.println(Thread.currentThread().getName()+" terminate.");
    }
    static class Domino implements Runnable{
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try{
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" terminate.");
        }
    }
//    //Thread源码
//    //加锁线程当前对象
//    public final synchronized void join()throws InterruptedException{
//        //条件不满足，继续等待
//        while(isAlive()){
//            wait(0);
//        }
//        //条件符合，方法返回
//    }
}
