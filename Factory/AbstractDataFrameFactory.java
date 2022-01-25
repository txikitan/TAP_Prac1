/*---------------------------------------------------
- TAP JavaDataFrame: Interface to create factories for
       generating each dataframe type (polymorphism)
    Gabriel Garcia
/----------------------------------------------------*/
import com.opencsv.exceptions.CsvException;
import org.json.simple.parser.ParseException;
import java.io.IOException;

public interface AbstractDataFrameFactory {

    DataFrame createDataFrame(String filename) throws IOException, CsvException, ParseException, NoSuchFieldException, IllegalAccessException;

}
