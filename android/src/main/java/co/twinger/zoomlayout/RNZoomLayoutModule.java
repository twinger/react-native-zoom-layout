package co.twinger.zoomlayout;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class RNZoomLayoutModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNZoomLayoutModule(ReactApplicationContext reactContext, ZoomLayoutManager zoomLayoutManager) {
        super(reactContext);
        this.reactContext = reactContext;

    }

    @Override
    public String getName() {
        return "RNZoomLayout";
    }



}