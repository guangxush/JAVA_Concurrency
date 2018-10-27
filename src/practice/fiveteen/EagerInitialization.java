package practice.fiveteen;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getResource() {
        return resource;
    }

    static class Resource {
    }
}
