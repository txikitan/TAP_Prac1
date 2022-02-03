/*---------------------------------------------------
- TAP JavaDataFrame: Visitor interface that implements
    the visitor pattern to do perform the average of the
    values under a determinate label in a DataFrame
    Gabriel Garcia
/----------------------------------------------------*/
import java.util.List;

public class AverageVisitor implements DataFrameVisitor{
    private int dfCounter = 0;

    public void visit(DataFrame dataFrame, String label) {
        List<String> listToAverage = dataFrame.df.get(label);
        int sum = 0;
        for(String value : listToAverage) {
            if(!(value.matches("^[ A-Za-z]+$"))) sum = sum + Integer.parseInt(value);
        }
        dfCounter++;
        float average = (float) sum / (float) listToAverage.size(); // Just applying the classic average formula
        System.out.println("Average value (label="+label+") of numeric values in this Df"+dfCounter+" = " + average + ";");
    }
}
