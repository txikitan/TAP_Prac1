import com.opencsv.exceptions.CsvException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            DataFrame df = new JSONDataFrame("cities.json");
            DataFrame df2 = new CSVDataFrame("cities.csv");


        } catch (IOException | ParseException | NoSuchFieldException | IllegalAccessException | CsvException e) {
            e.printStackTrace();
        }

    }
}
