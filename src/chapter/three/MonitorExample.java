package chapter.three;

/**
 * Created by gshan on 2018/9/9
 */
public class MonitorExample {

    int a = 0;

    public synchronized void writer(){
        a++;
    }

    public synchronized void reader(){
        int i = a;
    }
}
