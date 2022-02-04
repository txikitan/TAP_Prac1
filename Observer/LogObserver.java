/**---------------------------------------------------
- TAP JavaDataFrame: Class that implements
    the definition of the main Observer method to
     log the operations intercepted by the
     dynamic proxy

    @author Gabriel Garcia
/----------------------------------------------------*/
import java.util.logging.Logger;


public class LogObserver extends Observer{

    /**
     * Update method to log operations intercepted by the proxy
     * @param methodName name of the method to log
     * @param args arguments of the execution to query the log (queryObserver)
     */
    @Override
    public void update(String methodName,Object[] args) {
        Logger logger
                = Logger.getLogger(
                DataFrame.class.getName());
        logger.info("Operation "+ methodName + " executed");

    }
}
