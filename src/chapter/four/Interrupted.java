package chapter.four;

import java.util.concurrent.TimeUnit;

public class Interrupted {
    public static void main(String[] args) throws Exception{
        //sleep线程不断尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        //busyRunner不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        //休眠五秒，让sleepThread和BusyThread充分运行
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        //SleepThread中断标志位被清除，并抛出异常，此时isInterrupted返回false
        System.out.print("SleepThread interrupted is "+ sleepThread.isInterrupted());
        //BusyThread中断标志位没有被清除，此时isInterrupted返回true
        System.out.print("BusyThread interrupted is"+ busyThread.isInterrupted());
        //防止立刻退出
        SleepUtils.second(2);
    }
    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while(true){
                SleepUtils.second(10);
            }
        }
    }
    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while (true){

            }
        }
    }
}
