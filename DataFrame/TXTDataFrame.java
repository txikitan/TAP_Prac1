/*---------------------------------------------------
- TAP JavaDataFrame: Child class of abstract DataFrame
        class to create the dataframe from a txt file

    Gabriel Garcia
/----------------------------------------------------*/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class TXTDataFrame extends DataFrame implements IDataFrame {

    /*Reads a txt file and converts it into a DataFrame*/
    public TXTDataFrame(String filename) throws IOException {
        Path path = Paths.get(filename);
        List<String> fullFile = Files.readAllLines(path); // read full file split by lines
        String[] labels = fullFile.get(0).replaceAll("\"","").split(","); // capture the labels
        super.columns = labels.length;
        List<String> col;   // the list that will be the value for each label (key) of the dataframe
        int j;
        for(int i=0;i<labels.length;i++) { // start iterating while we have labels to import to the df
            col = new ArrayList<>();
            j=1;
            while(j<fullFile.size()){ // for each label we navigate over every row of the txt (despite the first)
                String value = fullFile.get(j).replaceAll("\"","").split(",")[i]; //replace any space if the field is numeric
                if(!(value.matches("^[ A-Za-z]+$"))) value = value.replaceAll("\\s", "");// we split the corresponding line and add the label column to the value list
                col.add(value);
                j++;
            }
            super.df.put(labels[i],col); // we put the label from the array and the values list that we have just obtained navigating through the column
            super.rows = col.size();
        }
    }

}
