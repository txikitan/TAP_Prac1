import com.opencsv.exceptions.CsvException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        try {
            AbstractDataFrameFactory CSVFactory = new CSVDataFrameFactory();
            AbstractDataFrameFactory JSONFactory = new JSONDataFrameFactory();
            AbstractDataFrameFactory TXTFactory = new TXTDataFrameFactory();
            DataFrame CSVdf = CSVFactory.createDataFrame("cities.csv");
            DataFrame JSONdf = JSONFactory.createDataFrame("cities.json");
            DataFrame TXTdf = TXTFactory.createDataFrame("cities.txt");

            Predicate<String> biggerThan = p ->{
              if(p.matches("[0-9]+")) return Integer.parseInt(p)>45;
              return false;
            };
            LinkedHashMap<String, List<String>>queryedMap= CSVdf.query(biggerThan);
            System.out.println("Fin main");
        } catch (IOException | ParseException | NoSuchFieldException | IllegalAccessException | CsvException e) {
            e.printStackTrace();
        }

    }

    static class testComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
        }
    }
}
