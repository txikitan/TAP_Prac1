/*---------------------------------------------------
- TAP JavaDataFrame: Visitor interface that implements
    the visitor pattern to do perform the max of the
    values under a determinate label in a DataFrame
    Gabriel Garcia
/----------------------------------------------------*/
import java.util.List;

public class MaxVisitor implements DataFrameVisitor {
    private int dfCounter = 0;

    public void visit(DataFrame dataFrame, String label) {
        List<String> listToMax = dataFrame.df.get(label);
        int max = Integer.parseInt(listToMax.get(0));
        for(String value : listToMax) {
            if(!(value.matches("^[ A-Za-z]+$")) && Integer.parseInt(value) > max) max = Integer.parseInt(value); // if the value is numeric...
        }
        dfCounter++;
        System.out.println("Max (label="+label+") of numeric values in this Df"+dfCounter+" = " + max + ";");
    }
}
