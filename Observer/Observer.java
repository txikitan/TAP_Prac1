/*---------------------------------------------------
- TAP JavaDataFrame: Abstract class that provides
    the definition of the main Observer method to
    subscribe and log the operations intercepted by the
    dynamic proxy

    Gabriel Garcia
/----------------------------------------------------*/
public abstract class Observer {
    public abstract void update(String methodName);
}
