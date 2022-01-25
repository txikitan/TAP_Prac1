/*---------------------------------------------------
- TAP JavaDataFrame: Factory instance to generate JSON
        based DataFrames
    Gabriel Garcia
/----------------------------------------------------*/
import org.json.simple.parser.ParseException;
import java.io.IOException;

public class JSONDataFrameFactory implements AbstractDataFrameFactory {

    @Override
    public DataFrame createDataFrame(String filename) throws IOException, ParseException {
        return new JSONDataFrame(filename);
    }
}
