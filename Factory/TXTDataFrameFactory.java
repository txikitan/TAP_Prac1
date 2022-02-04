/*---------------------------------------------------
- TAP JavaDataFrame: Factory instance to generate TXT
        based DataFrames
    Gabriel Garcia
/----------------------------------------------------*/
import java.io.IOException;

public class TXTDataFrameFactory implements AbstractDataFrameFactory {

    /**
     * Factory method to generate JSON Dataframes
     * @param filename name of the file to import
     * @throws IOException exception during the file read
     */
    @Override
    public DataFrame createDataFrame(String filename) throws IOException {
        return new TXTDataFrame(filename);
    }
}
