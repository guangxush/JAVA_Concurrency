package chapter.four;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            if(method.getName().equals("commit")){
                try{
                    TimeUnit.MILLISECONDS.sleep(100);
                    //休眠100ms模拟数据库连接
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
    //创建一个connection的代理，在commit时休眠100毫秒
    public static final Connection createConnection(){
        return (Connection) Proxy.newProxyInstance(ConnectionPool.class.getClassLoader(),new Class<?>[]
                {Connection.class},new ConnectionHandler());
    }
}
