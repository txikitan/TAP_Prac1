/*---------------------------------------------------
- TAP JavaDataFrame: Child class of abstract DataFrame
        class to create the dataframe from a json file

    Gabriel Garcia
/----------------------------------------------------*/
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JSONDataFrame extends DataFrame {

    /*Reads a json file and converts it into a DataFrame*/
    @SuppressWarnings("unchecked")
    public JSONDataFrame(String filename) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader(filename);
        Object fileContent = jsonParser.parse(reader);     // First we parse the json file to an object -> WARNING; DOES NOT RESPECT ORDER AS IT IS A DICTIONARY
        JSONArray jsonArrayContent = (JSONArray) fileContent;   // convert it to an array
        Object objLabels = jsonArrayContent.get(0);             //capture the first block
        Set<String> labels = ((JSONObject)objLabels).keySet();  // get all the keys (labels)
        String[] labelsArray = labels.toArray(new String[0]);  // convert them to an array of strings
        super.columns = labelsArray.length;
        List<String> col;       // the list for each dataframe entry
        for (String s : labelsArray) {  // we will iterate for every dataframe entry (label)
            col = new ArrayList<>();
            for (Object values : jsonArrayContent) {    // for each entry, we navigate over all the json blocks
                JSONObject entry = (JSONObject) values;
                col.add(entry.get(s).toString());       // we add the desired value assigned to the label (key) to our List
            }
            super.df.put(s, col);   // we add the list and the label from the previous array of strings to the dataframe structure
            super.rows = col.size();
        }
    }
}
