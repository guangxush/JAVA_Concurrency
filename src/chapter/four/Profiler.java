package chapter.four;

import java.util.concurrent.TimeUnit;

public class Profiler {
    //第一次get方法调用是会进行初始化，如果set方法没有被调用，每个线程会执行一次
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        //线程变量
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };
    public static final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }
    public static final long end(){
        return System.currentTimeMillis()-TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws Exception{
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost:"+Profiler.end()+"mills");
    }
//    //等待超时模式伪代码
//    public synchronized Object get(long mills) throws InterruptedException{
//        long future = System.currentTimeMillis()+mills;
//        long remaining = mills;
//        while((result==null)&&remaining>0){
//            wait(remaining);
//            remaining = future - System.currentTimeMillis();
//        }
//        return result;
//    }
}
