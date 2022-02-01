/*---------------------------------------------------
- TAP JavaDataFrame: Class that provides the implementation
        of a dynamic proxy that intercepts operations
        executed by the dataframes
    Gabriel Garcia
/----------------------------------------------------*/
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class DynamicProxy implements InvocationHandler {
    private Object target;
    private List<Observer> observers; // List of observers that we can attach to the dynamic proxy to refine his interceptions

    /*Dynamic proxy methods*/
    public static Object newInstance(Object target, List<Observer> observers){
        Class<?> targetClass = target.getClass();
        Class[] interfaces = targetClass.getInterfaces();

        return Proxy. newProxyInstance(targetClass.getClassLoader(),
                interfaces, new DynamicProxy(target, observers));
    }

    /*We will only be able to attach observers when we instantiate*/
    private DynamicProxy(Object target,List<Observer> observers) {
        this.target = target;
        this.observers = observers;
    }


    public Object invoke(Object proxy, Method method, Object[] args) {
        Object invocationResult = null;
        try
        {
            System.out.println("Before method " + method.getName());
            invocationResult = method.invoke(this.target, args);
            System.out.println("After method " + method.getName());
        }
        catch(InvocationTargetException ite)
        {
            throw ite.getTargetException();
        }
        catch(Exception e)
        {
            System.err.println("Invocation of " + method.getName() + " failed");
        }
        finally{
            notifyAllObservers(method.getName());
            return invocationResult;
        }
    }


    /*Observer Methods */

    public void notifyAllObservers(String methodName){
        for (Observer observer : observers) {
            observer.update(methodName);
        }
    }

}
