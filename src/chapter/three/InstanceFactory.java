package chapter.three;

/**
 * Created by gshan on 2018/9/14
 */
public class InstanceFactory {
    private static class InstanceHolder{
        public static Instance instance = new Instance();
    }

    public static Instance getInstance(){
        return InstanceHolder.instance;//这里导致InstanceHolder被初始化
    }
}
