package chapter.three;

/**
 * Created by gshan on 2018/9/9
 */
public class ReorderExample {

    int a = 0;
    boolean flag = false;

    public void writer(){
        a = 1;
        flag = false;
    }

    public void reader(){
        if(flag){
            int i = a*a;
        }
    }
}
