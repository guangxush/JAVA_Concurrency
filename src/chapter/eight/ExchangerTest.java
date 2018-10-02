package chapter.eight;

import org.omg.CORBA.INITIALIZE;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gshan on 2018/10/2
 */
public class ExchangerTest {
    private static final Exchanger<String> exgr = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String args[]){
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "data a";
                    exgr.exchange(A);
                } catch (InterruptedException e) {

                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    String B = "data B";
                    String A = exgr.exchange("B");
                    System.out.println("A is equal B? "+A.equals(B)+" A:"+A+" B:"+B);
                }catch (InterruptedException e){

                }
            }
        });
        threadPool.shutdown();
    }
}
