/*---------------------------------------------------
- TAP JavaDataFrame: Class that implements
    the definition of the main Observer method to
     log the operations intercepted by the
     dynamic proxy

    Gabriel Garcia
/----------------------------------------------------*/
import java.util.logging.Logger;


public class LogObserver extends Observer{

    @Override
    public void update(String methodName,Object[] args) {
        Logger logger
                = Logger.getLogger(
                DataFrame.class.getName());
        logger.info("Operation "+ methodName + " executed");

    }
}
