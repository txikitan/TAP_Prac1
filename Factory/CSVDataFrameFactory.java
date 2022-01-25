/*---------------------------------------------------
- TAP JavaDataFrame: Factory instance to generate CSV
        based DataFrames
    Gabriel Garcia
/----------------------------------------------------*/
import com.opencsv.exceptions.CsvException;
import java.io.IOException;


public class CSVDataFrameFactory implements AbstractDataFrameFactory {

    @Override
    public DataFrame createDataFrame(String filename) throws IOException, CsvException {
        return new CSVDataFrame(filename);
    }
}
