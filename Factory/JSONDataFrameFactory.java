/**---------------------------------------------------
- TAP JavaDataFrame: Factory instance to generate JSON
        based DataFrames
    @author Gabriel Garcia
/----------------------------------------------------*/
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class JSONDataFrameFactory implements AbstractDataFrameFactory {

    /**
     * Factory method to generate JSON Dataframes
     * @param filename name of the file to import
     * @throws IOException exception during the file read
     * @throws ParseException exception during the parsing of the json file
     */
    @Override
    public DataFrame createDataFrame(String filename) throws IOException, ParseException {
        return new JSONDataFrame(filename);
    }
}
