package chapter.four;

import java.security.AccessControlContext;

public class ThreadDemo {
    private void init(ThreadGroup g, Runnable target, String name, long stackSize, AccessControlContext acc){
        /**
        if(name==null){
            throw new NullPointerException("name cannot be null");
        }
        //当前线程就是该线程的父线程
        Thread parent = Thread.currentThread();
        this.group = g;
        //将daemon、priority属性设置为父线程对应的属性
        this.daemon = parent.isDaemon();
        this.priority = parent.getPriority();
        this.name = name.toCharArray();
        this.target = target;
        setPriority(priority);
        //将父线程的InheritableThreadLocals复制过来
        if(parent.inheritableThreadLocals!=null){
            this.inheritableThreadLocals=ThreadLocal.createInheritableMap(parent.inheritableThreadLocals);
        }
        //分配一个线程id
        tid = nextThreadID();
         **/
    }
}
