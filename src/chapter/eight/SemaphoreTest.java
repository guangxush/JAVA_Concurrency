package chapter.eight;

import chapter.four.Interrupted;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by gshan on 2018/10/2
 */
public class SemaphoreTest {
    private final static int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for(int i=0;i<THREAD_COUNT;i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        s.acquire();
                        System.out.println("save data");
                        s.release();
                    }catch (InterruptedException e){

                    }
                }
            });
        }
        threadPool.shutdown();
    }
}
