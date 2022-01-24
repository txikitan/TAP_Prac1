import com.opencsv.exceptions.CsvException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            DataFrame df = new CSVDataFrame("cities.csv");


        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

    }
}
