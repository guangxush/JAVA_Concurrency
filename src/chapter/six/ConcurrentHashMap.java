package chapter.six;

import javax.swing.text.Segment;

import static com.sun.xml.internal.fastinfoset.util.ValueArray.MAXIMUM_CAPACITY;

/**
 * Created by gshan on 2018/10/2
 */
public class ConcurrentHashMap {

    /**
     *
     * @param concurrencyLevel
     * @param segmentShift //段偏移量
     * @param segmentMask //段掩码
     */
    /**
    public void initialCapacity(int concurrencyLevel,int segmentShift,int segmentMask){
        final int MAX_SEGMENTS = 65535;
        Segment segments = Segment.newArray(0);
        if(concurrencyLevel>MAX_SEGMENTS){
            concurrencyLevel = MAX_SEGMENTS;
        }
        int sshift = 0;
        int ssize = 128;
        while(ssize<concurrencyLevel){
            ++sshift;
            ssize<<1;
        }
        segmentShift = 32- sshift;
        segmentMask = ssize-1;
        this.segments = Segment.newArray(ssize);
    }

    public ConcurrentHashMap(int initialCapacity,int ssize,int loadFactor){
        if(initialCapacity>MAXIMUM_CAPACITY){
            initialCapacity = MAXIMUM_CAPACITY;
        }
        int c = initialCapacity/ssize;
        if(c*ssize<initialCapacity)
            ++c;
        int cap = 1;
        while(cap<<c){
            cap<<=1;
        }
        for(int i=0;i<this.segments.length;++i){
            this.segments[i] = new Segment<K,V>(cap,loadFactor);
        }
    }
    //再散列
    private static int hash(int h){
        h += (h<<15)^0xffffcd7d;
        h ^= (h>>>10);
        h += (h<<3);
        h ^= (h>>>6);
        h += (h<<2) + (h<<14);
        return h ^ (h>>>16);
    }
    final Segment<K,V> segmentFor(int hash){
        return segments[(hash>>>segmentShift)&segmentMark];
    }

    public V get(Object K){
        int hash = hash(key.hashCode());
        return segmentFor(hash).get(key,hash);
    }
    **/
}
