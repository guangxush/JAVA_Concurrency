package practice.five;

import java.util.Vector;

/**
 * bad
 */
public class UnsafeVectorHelpers {

    public static Object getLast(Vector list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    public static void deleteLast(Vector list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }
}
