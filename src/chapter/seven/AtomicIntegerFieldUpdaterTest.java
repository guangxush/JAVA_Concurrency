package chapter.seven;

import java.sql.SQLOutput;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by gshan on 2018/10/2
 */
public class AtomicIntegerFieldUpdaterTest {
    //创建原子更新器，并设置需要更新的对象类和对象的属性
    private static AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

    public static void main(String[] args) {
        //设置年龄为10岁
        User conan = new User("conan",10);
        //柯南涨了一岁，但是仍然会输出旧的年龄
        System.out.println(a.getAndIncrement(conan));
        //输出柯南现在的年龄
        System.out.println(a.get(conan));
    }

    static class User{
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }
}
