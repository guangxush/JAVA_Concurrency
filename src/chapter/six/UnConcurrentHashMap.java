package chapter.six;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by gshan on 2018/10/2
 */
public class UnConcurrentHashMap {
    //多线程环境下执行以下代码会造成死循环
    public static void main(String[] args) throws InterruptedException{
        final HashMap<String, String> map = new HashMap<>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0;i<10000;i++){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(),"");
                        }
                    },"ftf"+i).start();
                }
            }
        },"tft");
        t.start();
        t.join();
    }
    //HashTable效率低下，使用Synchronized来保证线程安全，但是只能有一个进程访问元素，效率低下

    //ConcurrentHashMap采用锁分段的技术有效提升并发访问效率
}
