import java.util.List;

public class AverageVisitor implements DataFrameVisitor{
    private int dfCounter = 0;

    public void visit(DataFrame dataFrame, String label) {
        List<String> listToAverage = dataFrame.df.get(label);
        int sum = 0;
        for(String value : listToAverage) {
            if(value.matches("^[ A-Za-z]+$")) sum = sum + Integer.parseInt(value);
        }
        dfCounter++;
        long average = (long) sum / (long) listToAverage.size();
        System.out.println("Average value (label="+label+") of numeric values in this Df"+dfCounter+" = " + average + ";");
    }
}
