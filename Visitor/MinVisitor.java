/**---------------------------------------------------
- TAP JavaDataFrame: Visitor interface that implements
    the visitor pattern to do perform the min of the
    values under a determinate label in a DataFrame
    @author Gabriel Garcia
/----------------------------------------------------*/
import java.util.List;

public class MinVisitor implements DataFrameVisitor{
    private int dfCounter = 0;

    /**
     * Visit method to perform the min of the values under the label in the dataframe
     * @param dataFrame Dataframe to visit (calculate)
     * @param label label to calculate min
     */
    public void visit(DataFrame dataFrame, String label) {
        List<String> listToMax = dataFrame.df.get(label);
        int min = Integer.parseInt(listToMax.get(0));
        for(String value : listToMax) {
            if(!(value.matches("^[ A-Za-z]+$")) && Integer.parseInt(value) < min) min = Integer.parseInt(value);
        }
        dfCounter++;
        System.out.println("Min (label="+label+") of numeric values in this Df"+dfCounter+" = " + min + ";");
    }
}
