/**---------------------------------------------------
- TAP JavaDataFrame: Factory instance to generate CSV
        based DataFrames
    @author Gabriel Garcia
/----------------------------------------------------*/
import com.opencsv.exceptions.CsvException;
import java.io.IOException;


public class CSVDataFrameFactory implements AbstractDataFrameFactory {

    /**
     * Factory method to generate CSV Dataframes
     * @param filename name of the file to be imported
     * @return instance of CSVDataframe
     * @throws IOException  exception during the file read
     * @throws CsvException exception during the parse of the csv
     */
    @Override
    public DataFrame createDataFrame(String filename) throws IOException, CsvException {
        return new CSVDataFrame(filename);
    }
}
