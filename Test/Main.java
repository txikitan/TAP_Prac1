import com.opencsv.exceptions.CsvException;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;
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
            DataFrame CSVdf2 = CSVFactory.createDataFrame("cities.csv");
            Directory subdir = new Directory("Subdirectorio");
            Directory dir = new Directory("Directorio");
            subdir.addChild(CSVdf2);
            dir.addChild(CSVdf);
            dir.addChild(subdir);
            String at = dir.at(0,"LatD");
            String iat = dir.iat(0,0);
            // List<String> sort = CSVdf.sort("LatD",new testComparator());

            //String prueba = CSVdf.at(0, "XD");
            Predicate<String> biggerThan = p ->{
              if(p.matches("[0-9]+")) return Integer.parseInt(p)>45;
              return false;
            };
            //LinkedHashMap<String, List<String>>queryedMap= CSVdf.query("LatD",biggerThan);
            //LinkedHashMap<String,List<String>> queryedDir = dir.query("LatD",biggerThan);
            List<String> next= new ArrayList<>();
            for(List<String> it : dir) {
                next = it;
                System.out.println("Next");
            }

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
