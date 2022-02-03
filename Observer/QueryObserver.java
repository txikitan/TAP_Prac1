/*---------------------------------------------------
- TAP JavaDataFrame: Class that implements
    the definition of the main Observer method to
     log the operations intercepted by the
     dynamic proxy under a specified label

    Gabriel Garcia
/----------------------------------------------------*/
import java.util.logging.Logger;

public class QueryObserver extends Observer {
    String label;
    public QueryObserver(String label){
        this.label = label;
    }
    @Override
    public void update(String methodName, Object[] args) {
        boolean match = false;
        for(Object arg : args){ // if one of the arguments matches the label
            if(arg.toString().equals(this.label)) {
                match = true;
                break;
            }
        }
        if(match) {
            Logger logger
                    = Logger.getLogger(
                    DataFrame.class.getName());
            logger.info("Operation " + methodName + " executed under label "+label);
        }

    }
}
