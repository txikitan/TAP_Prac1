/*---------------------------------------------------
- TAP JavaDataFrame: Main class to show the features
    Gabriel Garcia
/----------------------------------------------------*/

import com.opencsv.exceptions.CsvException;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        try{

            System.out.println("Hello, this is a guided test for the library (Not JUNIT) to show that all implemented features work.");
            System.out.println("This test will work with the example cities dataframes files in CSV, txt and Json, these will be equivalent when imported");

            System.out.println("Testing factories....:");
            AbstractDataFrameFactory CSVFactory = new CSVDataFrameFactory();
            AbstractDataFrameFactory JSONFactory = new JSONDataFrameFactory();
            AbstractDataFrameFactory TXTFactory = new TXTDataFrameFactory();
            DataFrame CSVdf = CSVFactory.createDataFrame("cities.csv");
            DataFrame JSONdf = JSONFactory.createDataFrame("cities.json");
            DataFrame TXTdf = TXTFactory.createDataFrame("cities.txt");
            if(CSVdf == null || JSONdf == null || TXTdf == null) System.out.println("Error generating DataFrames with factories");
            else System.out.println("Successfully created dataframes using factories");

            /*Predicate for testing queries*/
            Predicate<String> biggerThan = p ->{
                if(p.matches("[0-9]+")) return Integer.parseInt(p)>45;
                return false;
            };
            System.out.println("Testing individual api methods over the generated dataframes");
            assert CSVdf != null;
            String resultAt = CSVdf.at(0,"LatD");
            System.out.println(resultAt);
            assert TXTdf != null;
            String resultIat = TXTdf.iat(0,0);
            System.out.println(resultIat);
            if(!resultAt.equals("41") || !resultIat.equals("41")) System.out.println("Error in methods at/iat");
            List<String> sortedList = CSVdf.sort("LatD",new testComparator());
            System.out.println("Printing sorted list of label LatD in ascending order...");
            for(String str : sortedList) System.out.println(str);
            System.out.println("Testing query for elements bigger than 45 under label LatD...");
            LinkedHashMap<String, List<String>> queryMap= TXTdf.query("LatD",biggerThan);
            List<String> queryList = queryMap.get("LatD");
            System.out.println("Printing query column: ");
            for(String str : queryList) System.out.println(str);
            assert JSONdf != null;
            System.out.println("Labels of the dataframe: "+JSONdf.columns());
            System.out.println("Number of items in the dataframe: "+ JSONdf.size());
            System.out.println("Iterating over the dataframe through columns...");
            int i = 0;
            for(List<String> df : CSVdf) {
                System.out.println("Column: "+ i);
                i++;
            }

            System.out.println("Testing the composite: Dir [CSVdf] ->Sub dir[TXTdf] -> Sub dir 2[JSONdf]");
            Directory directory = new Directory("Main dir");
            Directory subdirectory = new Directory("SubDir");
            Directory subdirectory2 = new Directory("SubDir2");
            subdirectory.addChild(new TXTDataFrame("cities.txt"));
            subdirectory2.addChild((new JSONDataFrame("cities.json")));
            subdirectory.addChild(subdirectory2);
            directory.addChild(new CSVDataFrame("cities.csv"));
            directory.addChild(subdirectory);
            System.out.println("Result of at method for all files (Should be 41)");
            System.out.println(directory.at(0,"LatD"));
            System.out.println("Result of at method iat for all files (Should be 41,41 and random because json does not respect an order");
            System.out.println(directory.iat(0,0));
            List<String> sortedListComposite = directory.sort("LatD",new testComparator());
            System.out.println("Printing sorted list of label LatD in ascending order in the directory...");
            for(String str : sortedListComposite) System.out.println(str);
            directory.query("LatD",biggerThan);
            System.out.println("Testing query for elements bigger than 45 under label LatD in the composite...");
            LinkedHashMap<String,List<String>> queryedDir = directory.query("LatD",biggerThan);
            List<String> queryDir = queryedDir.get("LatD");
            System.out.println("Printing query and merged dataframe: ");
            for(String str : queryDir) System.out.println(str);
            System.out.println("Labels of the directory: "+directory.columns());
            System.out.println("Number of items in the directory: "+ directory.size());
            System.out.println("Iterating over the directory's dataframes through columns...");
            int j = 0;
            for(List<String> df : directory) {
                System.out.println("Column: "+ j);
                j++;
            }
            directory.removeChild(subdirectory);
            if(directory.size() == 128) System.out.println("Successfully removed a subdirectory");
            else System.out.println("Error removing child!");

            System.out.println("Testing the visitor pattern in the composite: will show max, min, sum and average of column label LatD");
            DataFrameVisitor sumVisitor = new SumVisitor();
            DataFrameVisitor minVisitor = new MinVisitor();
            DataFrameVisitor maxVisitor = new MaxVisitor();
            DataFrameVisitor averageVisitor = new AverageVisitor();
            directory.accept(sumVisitor,"LatD");
            directory.accept(minVisitor,"LatD");
            directory.accept(maxVisitor,"LatD");
            directory.accept(averageVisitor,"LatD");

            System.out.println("Creating an extra csv-based dataframe with a dynamic proxy interceptor and a LogObserver subscribed to it");
            System.out.println("This will use the interface IDataFrame that we defined to be able to do the casting");
            List<Observer> observers= new ArrayList<>();
            observers.add(new LogObserver());
            observers.add(new QueryObserver("LonD"));
            IDataFrame df = (IDataFrame) DynamicProxy.newInstance(new CSVDataFrame("cities.csv"),observers);
            System.out.println("Testing operations At and Iat to be logged properly and intercepted too");
            System.out.println(df.at(7,"LonD"));
            System.out.println(df.at(7,"NS") + "Should not be logged"); // This should not be logged
            System.out.println(df.iat(0,4));
            System.out.println("End of the test demonstration, bye!");

        } catch (IOException | ParseException | NoSuchFieldException | IllegalAccessException | CsvException e) {
            e.printStackTrace();
        }
    }

    /*Comparators used for sorting methods*/
    static class testComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
        }
    }
}
