package chapter.six;

/**
 * Created by gshan on 2018/10/2
 */
public class ConcurrentLinkedQueue<E> {
    /**
    //入队操作
    //1.定位出尾节点
    //2.CAS算法将入队节点设置为尾节点，如果不成功则重试
    public boolean offer(E e){
        if(e==null) throw new NullPointerException();
        //入队前创建一个入队节点
        Node<E> e = new Node<>(e);
        retry;
        //死循环，入队不成功，反复入队
        for(;;){
            //创建一个指向tail节点的引用
            Node<E> t =tail;
            //p用来表示队列的尾节点，默认情况下等于tail节点
            Node<E> p = t;
            for(int hops=0;;hops++){
                //获得p节点的下一个节点
                Node<E> next = succ(p);
                //next节点不为空，说明p不是尾节点，需要更新p后将他只想next节点
                if(next!=null){
                    //循环了两次及以上，并且当前节点不是尾节点
                    if(hops>HOPS&&t!=tail)
                        continue;retry;
                    p = next;
                }
                //如果p是尾节点，设置p节点的next节点为入队节点
                else if(p.casNext(null,n)){

                     // 如果tail节点有大于等于1的next节点，则将入队节点设置为tail,更新失败了也没关系，
                     //因为失败了表示有其他线程成功更新了tail节点

                    if (hops >= HOPS) {
                        casTail(t,n);//更新tail节点，允许失败
                        return  true;
                    }
                    //p有next节点，表示p的next节点为尾节点，重新设置p节点
                    else{
                        p = succ(p);
                    }
                }
            }
        }
    }
    //定位尾节点
    final Node<E> succ(Node<E> p){
        Node<E> next = p.getNext();
        return p==next?head:next;
    }
    //队列出节点
    public E poll(){
        Node<E> h = head;
        //p表示头结点，需要出队的节点
        Node<E> p = h;
        for(int hops=0;;hops++){
            //获取p节点的元素
            E item = p.getItem();
            //如果p节点的元素不为空，使用CAS设置p节点的引用元素为null
            //如果成功返回p节点元素
            if(item!=null&&p.casItem(item,null)){
                if(hops>=HOPS){
                    //将p节点的下一个节点设置为head
                    Node<E> q = p.getNext();
                    updateHead(h,(q!=null)?q:p);
                }
                return item;
            }
            //如果头结点的元素为空或者头结点发生了变化，说明头结点已经被另外一个进程修改了那么获取头结点的下一个节点
            Node<E> next = succ(p);
            //如果头结点的下一个节点也为空，说明队列已经空了
            if(next==null){
                //更新头结点
                updateHead(h,p);
                break;
            }
            //如果下一节点元素不为空，那么将头结点的下一个节点设置为头结点
            p = next;
        }
        return null;
    }**/
}
