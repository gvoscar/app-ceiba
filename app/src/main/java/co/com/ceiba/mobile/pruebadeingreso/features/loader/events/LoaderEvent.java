package co.com.ceiba.mobile.pruebadeingreso.features.loader.events;

public class LoaderEvent {

    public static final int DOWNLOADING = 100;
    public static final int LOADED = 200;

    private int eventType;
    private String message;

    public LoaderEvent() {
    }

    public LoaderEvent(int eventType) {
        this.eventType = eventType;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
