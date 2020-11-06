package co.com.ceiba.mobile.pruebadeingreso.libs.base;


public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
