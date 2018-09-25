package chapter.four;

import java.util.concurrent.TimeUnit;

/**
 * Created by gshan on 2018/9/14
 */
public class SleepUtils {
    public static final void second(long seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
