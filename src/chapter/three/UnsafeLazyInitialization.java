package chapter.three;

/**
 * Created by gshan on 2018/9/14
 */
public class UnsafeLazyInitialization {

    private static UnsafeLazyInitialization instance;

    public static UnsafeLazyInitialization getInstance(){
        if(instance==null){   //1:A线程执行
            instance = new UnsafeLazyInitialization();//2:B线程执行
        }
        return instance;
    }
}
