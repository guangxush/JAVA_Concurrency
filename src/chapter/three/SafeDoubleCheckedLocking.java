package chapter.three;

/**
 * Created by gshan on 2018/9/14
 */
public class SafeDoubleCheckedLocking {
    private volatile static SafeDoubleCheckedLocking instance;

    public static SafeDoubleCheckedLocking getInstance(){
        if(instance == null){
            synchronized (SafeDoubleCheckedLocking.class){
                if(instance == null){
                    instance = new SafeDoubleCheckedLocking();//instance为volatile
                }
            }
        }
        return instance;
    }
}
