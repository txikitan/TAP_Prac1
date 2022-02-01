/*---------------------------------------------------
- TAP JavaDataFrame: Interface that all dataframe types
        will implement to be able to cast when using the
        dynamic proxy pattern

    Gabriel Garcia
/----------------------------------------------------*/
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;

public interface IDataFrame {
    String at(int row, String label);
    String iat(int row, int col);
    int columns();
    int size();
    List<String> sort(String label, Comparator<String> comparator);
    LinkedHashMap<String, List<String>> query(String label, Predicate<String> predicate);
}
