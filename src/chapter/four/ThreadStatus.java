package chapter.four;

/**
 * Created by gshan on 2018/9/14
 */
public class ThreadStatus {
    public static void main(String args[]){
        new Thread(new TimeWaiting());
    }
    //该线程不断地进行睡眠
    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true){
                SleepUtils.second(100);
            }
        }
    }
    //该线程在Waiting.class实例上等待
    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try{
                        Waiting.class.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    //该线程在Block.class实例上加锁后不会释放锁
    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(100);
                }
            }
        }
    }
}
