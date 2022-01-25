/*---------------------------------------------------
- TAP JavaDataFrame: Abstract class that provides
    a simple api to access the data in the DataFrame

    Gabriel Garcia
/----------------------------------------------------*/
import java.util.*;

public abstract class DataFrame /*implements Iterable*/ {


    /*The DataFrame will be stored in a LinkedHashMap with the label and all the column values stored in a list<string> */
    LinkedHashMap<String, List<String>> df = new LinkedHashMap<>();
    /*
    public Iterator iterator() {
        return df.entrySet().iterator();
    }
    public String at(int row, String col) {
    }
    public String iat(int row,int col) {

    }
    public int columns() {

    }
    public int rows() {

    }
    public String[] sort(String col, Comparator comparator){

    }*/


}
