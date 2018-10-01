package chapter.five;

/**
 * Created by gshan on 2018/10/1
 */
public class Node {
    public static final int CANCELLED = 1;
    public static final int SIGANL = -1;
    public static final int CONDITION = -2;
    public static final int PROPAGATE = -3;
    public static final int INITIAL = 0;
    public static final Node EXCLUSIVE = new Node(1);
    public int value;
    public Node prev;
    public Node next;
    public int waitStatus;
    public Thread thread;
    public Node(int value){
        this.value = value;
    }
    public Node(){

    }
    public Node predecessor(){
        return this.prev;
    }
}
