/*---------------------------------------------------
- TAP JavaDataFrame: Abstract class that provides
    a simple api to access the data in the DataFrame

    Gabriel Garcia
/----------------------------------------------------*/
import java.util.*;

public abstract class DataFrame implements Iterable<List<String>> {


    /*Counters for the number of columns and rows of the dataframe*/
    protected int columns, rows;
    /*The DataFrame will be stored in a LinkedHashMap with the label and all the column values stored in a list<string> */
    protected LinkedHashMap<String, List<String>> df = new LinkedHashMap<>();

    /*Making the dataframe iterable through columns*/
    public Iterator<List<String>> iterator() {
        return new Iterator<>(){
            Iterator<String> it = df.keySet().iterator();
          public boolean hasNext(){
              return it.hasNext();
          }
          /*For the method next we return the corresponding column */
          public List<String> next(){
              String key = it.next();
              it.next();
              return df.get(key);
          }
        };
    }

    /*Returns a specific item by text key*/
    public String at(int row, String col) {
        return this.df.get(col).get(row);
    }

    /*Returns a specific item by number indexes*/
    public String iat(int row,int col) {
        int i=0;
        String value = "NF";
        for(List<String> columna : this){
            if(i==col-1) {
                value = columna.get(row);
            }
        }
        return value;
    }

    /*Returns number of columns*/
    public int columns() {
        return this.columns;
    }

    /*Returns number of rows*/
    public int rows() {
        return this.rows;
    }

    /*public List<String> sort(String col, Comparator comparator){
        List<String> sortedList = this.df.get(col);

    }*/


}
