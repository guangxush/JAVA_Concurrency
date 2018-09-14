package chapter.three;

/**
 * Created by gshan on 2018/9/14
 */
public class SafeLazyInitialization {
    private static SafeLazyInitialization instance;

    public synchronized static SafeLazyInitialization getInstance(){
        if(instance!=null)
            instance  = new SafeLazyInitialization();
        return instance;
    }
}
