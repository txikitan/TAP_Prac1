import java.util.logging.Logger;

/*---------------------------------------------------
- TAP JavaDataFrame: Class that implements
    the definition of the main Observer method to
     log the operations intercepted by the
     dynamic proxy

    Gabriel Garcia
/----------------------------------------------------*/
public class LogObserver extends Observer{

    public LogObserver(DynamicProxy subject) {
        this.subject = subject;
    }
    @Override
    public void update() {
        Logger logger
                = Logger.getLogger(
                DataFrame.class.getName());
        logger.info("Operation executed");

    }
}
