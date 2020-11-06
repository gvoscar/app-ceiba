package co.com.ceiba.mobile.pruebadeingreso.libs.eventbus;


public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
