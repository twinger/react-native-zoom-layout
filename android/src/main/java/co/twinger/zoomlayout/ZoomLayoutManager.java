package vn.vinhomes.vincity;

import android.util.Log;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.otaliastudios.zoom.ZoomApi;
import com.otaliastudios.zoom.ZoomLayout;

import java.util.Map;

import javax.annotation.Nullable;

public class ZoomLayoutManager extends ViewGroupManager<ZoomLayout> {

    public static final String REACT_CLASS = "RCTZoomLayout";
    private ZoomLayout zoomLayout;

    public static final int COMMAND_ZOOM_TO = 1;
    public static final int COMMAND_MOVE_TO = 2;
    public static final int COMMAND_ZOOM_BY = 3;
    public static final int COMMAND_ZOOM_IN = 4;
    public static final int COMMAND_ZOOM_OUT = 5;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    ZoomLayoutManager(ReactApplicationContext context) {
    }

    @Override
    protected ZoomLayout createViewInstance(ThemedReactContext reactContext) {
        zoomLayout = new ZoomLayout(reactContext);
        return zoomLayout;
    }

    @ReactProp(name = "minZoom")
    public void setMinZoom(ZoomLayout view, float minZoom) {
        view.setMinZoom(minZoom, ZoomApi.TYPE_ZOOM);
    }

    @ReactProp(name = "maxZoom")
    public void setMaxZoom(ZoomLayout view, float maxZoom) {
        view.setMaxZoom(maxZoom, ZoomApi.TYPE_ZOOM);
    }

    @ReactProp(name = "overPinchable")
    public void setOverPinchable(ZoomLayout view, boolean overPinchable) {
        view.setOverPinchable(overPinchable);
    }

    @ReactProp(name = "zoomEnabled")
    public void setZoomEnabled(ZoomLayout view, boolean zoomEnabled) {
        view.setZoomEnabled(zoomEnabled);
    }

    @ReactProp(name = "rotate")
    public void setRotate(ZoomLayout view, float rotate) {
        Log.d("Rotate", rotate + "");
        view.setRotation(rotate);
    }

    @Override
    public Map<String, Integer> getCommandsMap() {
        Log.d("React", " View manager getCommandsMap:");
        return MapBuilder.of(
                "getZoom",
                COMMAND_ZOOM_TO,
                "moveTo",
                COMMAND_MOVE_TO);
    }

    @Override
    public void receiveCommand(ZoomLayout root, int commandId, @Nullable ReadableArray args) {
        super.receiveCommand(root, commandId, args);
        Assertions.assertNotNull(root);
        Assertions.assertNotNull(args);
        switch (commandId) {
            case COMMAND_ZOOM_TO: {
                float zoom = (float) args.getDouble(0);
                boolean animated = args.getBoolean(1);
                root.zoomTo(zoom, animated);
                return;
            }
            case COMMAND_MOVE_TO: {
                float zoom = (float) args.getDouble(0);
                float x = (float) args.getDouble(1);
                float y = (float) args.getDouble(2);
                boolean animated = args.getBoolean(3);
                root.moveTo(root.getZoom(), x, y, animated);
                return;
            }
            case COMMAND_ZOOM_BY: {
                float zoom = (float) args.getDouble(0);
                boolean animated = args.getBoolean(1);
                root.zoomBy(zoom, animated);
                return;
            }
            case COMMAND_ZOOM_IN: {
                root.zoomIn();
                return;
            }
            case COMMAND_ZOOM_OUT: {
                root.zoomOut();
                return;
            }
            default:
                throw new IllegalArgumentException(String.format(
                        "Unsupported command %d received by %s.",
                        commandId,
                        getClass().getSimpleName()));
        }

    }


    @Nullable
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onZoom",
                MapBuilder.of("registrationName", "onZoom"),
                "onPan",
                MapBuilder.of("registrationName", "onPan")
        );
    }
}