package chapter.seven;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gshan on 2018/10/2
 */
public class AtomicIntegerTest {
    static AtomicInteger ai = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
    }
    /**
     * 实现原理
     * public final int getAndIncrement(){
     *     for(;;){
     *         int current = get();
     *         int next = current +1;
     *         if(compareAndSet(current, next)){
     *             return current;
     *         }
     *     }
     * }
     */
}
