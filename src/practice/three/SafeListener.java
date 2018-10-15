package practice.three;

import javafx.event.Event;

import java.util.EventListener;

public class SafeListener {
    private final EventListener listener;

    private SafeListener(){
        listener = new EventListener() {
            public void onEvnet(Event event){
                dosomething(e);
            }
        };
    }
    public static SafeListener newInstance(EventListener source){
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }
}
