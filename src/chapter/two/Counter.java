package chapter.two;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gshan on 2018/9/9
 */
public class Counter {
     private AtomicInteger automicI = new AtomicInteger(0);
     private int i = 0;
     public static void main(String[] args){
         final Counter cas = new Counter();
         List<Thread> ts = new ArrayList<>(600);
         long start = System.currentTimeMillis();
         for(int j=0;j<100;j++){
             Thread t = new Thread(new Runnable() {
                 @Override
                 public void run() {
                     for(int i=0;i<10000;i++){
                         cas.count();
                         cas.safeCount();
                     }
                 }
             });
             ts.add(t);
         }
         for(Thread t :ts){
             t.start();
         }
         //等待所有线程执行完成
         for(Thread t:ts){
             try{
                 t.join();
             }catch (InterruptedException e){
                 e.printStackTrace();
             }
         }
         System.out.println(cas.i);
         System.out.println(cas.automicI.get());
         System.out.println(System.currentTimeMillis()-start);
     }

    /**
     * 使用CAS实现线程安全的计数器
     */
     private void safeCount(){
         for(;;){
             int i = automicI.get();
             boolean suc = automicI.compareAndSet(i,++i);
             if(suc){
                 break;
             }
         }
     }
    /**
     * 非线程安全的计数器
     */
    public void count(){
         i++;
     }
}
