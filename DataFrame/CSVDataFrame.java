/**---------------------------------------------------
- TAP JavaDataFrame: Child class of abstract DataFrame
        class to create the dataframe from a csv file

    @author Gabriel Garcia
/----------------------------------------------------*/
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataFrame extends DataFrame implements IDataFrame{

    /*Reads a csv file and converts it into a DataFrame*/

    /**
     * Constructor of the dataFrame to import CSV based dataframes
     * @param file name of the file to import
     * @throws IOException  exception during the file read
     * @throws CsvException exception during the parse of the csv
     */
    public CSVDataFrame(String file) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(file));
        List<String[]> r = reader.readAll();    // captures all rows in the csv and stores in r
        super.columns = r.get(0).length;
        List<String> col;                       // will contain the value for each entry of the linkedhashmap (column of the csv)
        int j;                                  // inner counter for rows
        for(int i=0;i<r.get(0).length;i++) {    // we iterate till we end up with all the labels
            col = new ArrayList<>();
            j = 1;                              // for each label we do all rows to complete the full column (starting after the row of labels)
            while(j<r.size()) { // we iterate till we end up with all the rows
                String value = r.get(j)[i];
                if(!(value.matches("^[ A-Za-z]+$"))) value = value.replaceAll("\\s", ""); //  replace any space if the field is numeric
                col.add(value);           // we add the actual value (i) of the row (j) to the list
                j++;
            }
            // String label = r.get(0)[i].replaceAll("\"","");
            super.df.put(r.get(0)[i],col);      // finally, add the column to the dataframe with following the pattern <label>,column>
            super.rows = col.size();
        }
    }

}

