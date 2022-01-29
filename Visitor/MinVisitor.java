/*---------------------------------------------------
- TAP JavaDataFrame: Visitor interface that implements
    the visitor pattern to do perform the min of the
    values under a determinate label
    Gabriel Garcia
/----------------------------------------------------*/
import java.util.List;

public class MinVisitor {
    private int dfCounter = 0;

    public void visit(DataFrame dataFrame, String label) {
        List<String> listToMax = dataFrame.df.get(label);
        int min = Integer.parseInt(listToMax.get(0));
        for(String value : listToMax) {
            if(value.matches("^[ A-Za-z]+$") && Integer.parseInt(value) < min) min = Integer.parseInt(value);
        }
        dfCounter++;
        System.out.println("Min (label="+label+") of numeric values in this Df"+dfCounter+" = " + min + ";");
    }
}
