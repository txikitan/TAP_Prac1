/*---------------------------------------------------
- TAP JavaDataFrame: Visitor interface that implements
    the visitor pattern to do perform the sum of the
    values under a determinate label in a DataFrame
    Gabriel Garcia
/----------------------------------------------------*/
import java.util.List;

public class SumVisitor implements DataFrameVisitor {
    private int dfCounter = 0;
    public void visit(DataFrame dataFrame, String label){
        List<String> listToSum = dataFrame.df.get(label);
        int sum = 0;
        for(String value : listToSum){
            if(!(value.matches("^[ A-Za-z]+$"))) sum = sum + Integer.parseInt(value);
        }
        dfCounter++;
        System.out.println("Sum (label="+label+") of numeric values in Df"+dfCounter+" = " + sum + ";");
    }
}
