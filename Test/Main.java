import com.opencsv.exceptions.CsvException;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            AbstractDataFrameFactory CSVFactory = new CSVDataFrameFactory();
            AbstractDataFrameFactory JSONFactory = new JSONDataFrameFactory();
            AbstractDataFrameFactory TXTFactory = new TXTDataFrameFactory();
            DataFrame CSVdf = CSVFactory.createDataFrame("cities.csv");
            DataFrame JSONdf = JSONFactory.createDataFrame("cities.json");
            DataFrame TXTdf = TXTFactory.createDataFrame("cities.txt");

        } catch (IOException | ParseException | NoSuchFieldException | IllegalAccessException | CsvException e) {
            e.printStackTrace();
        }

    }
}
