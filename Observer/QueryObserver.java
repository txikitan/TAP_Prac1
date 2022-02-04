/**---------------------------------------------------
- TAP JavaDataFrame: Class that implements
    the definition of the main Observer method to
     log the operations intercepted by the
     dynamic proxy under a specified label

    @auhtor Gabriel Garcia
/----------------------------------------------------*/
import java.util.logging.Logger;

public class QueryObserver extends Observer {
    String label;

    /**
     * Custom constructor to generate a observer that only logs operations intercepted under a certain label
     * @param label of the specific column to query
     */
    public QueryObserver(String label){
        this.label = label;
    }

    /**
     * Main observer method that prints in screen the logged operation only if has been executed over the specified label
     * in the constructor
     * @param methodName name of the method executed
     * @param args arguments of the execution to query the log
     */
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
