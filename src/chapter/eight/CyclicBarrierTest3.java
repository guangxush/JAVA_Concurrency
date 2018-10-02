package chapter.eight;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by gshan on 2018/10/2
 */
public class CyclicBarrierTest3 {
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    c.await();
                }catch (Exception e){

                }
            }
        });
        thread.start();
        thread.interrupt();
        try{
            c.await();
        }catch (Exception e){
            System.out.println(c.isBroken());
        }
    }
}
