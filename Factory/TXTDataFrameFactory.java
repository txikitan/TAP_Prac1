/*---------------------------------------------------
- TAP JavaDataFrame: Factory instance to generate TXT
        based DataFrames
    Gabriel Garcia
/----------------------------------------------------*/
import java.io.IOException;

public class TXTDataFrameFactory implements AbstractDataFrameFactory {

    @Override
    public DataFrame createDataFrame(String filename) throws IOException {
        return new TXTDataFrame(filename);
    }
}
