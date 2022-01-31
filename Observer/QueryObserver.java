/*---------------------------------------------------
- TAP JavaDataFrame: Class that implements
    the definition of the main Observer method to
     log the operations intercepted by the
     dynamic proxy under a specific label

    Gabriel Garcia
/----------------------------------------------------*/
public class QueryObserver extends Observer {
    String label;

    public QueryObserver(DynamicProxy subject, String label){
        this.label = label;
        super.subject = subject;
    }
    @Override
    public void update() {

    }

}
