/*---------------------------------------------------
- TAP JavaDataFrame: Directory class that applies the
        composite pattern to have directories of dataframes,
        and even directories of subdirectories.
    Gabriel Garcia
/----------------------------------------------------*/

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Directory extends DataFrame {
    private String name;    // name of the dataframe
    private List<DataFrame> children;

    /*Constructor*/
    public Directory(String name) {
        this.name = name;
        children = new LinkedList<>();
    }
    /*Adds a dataframe to the directory*/
    public void addChild(DataFrame child) {
        children.add(child);
    }

    /*Removes a dataframe from the directory*/
    public void removeChild(DataFrame child) {
        children.remove(child);
    }

    /*Returns the size of the full directory*/
    public int size(){
        int result = 0;
        for(DataFrame child : children) {
            result = result + child.size();
        }
        return result;
    }

    /*The Directory has to perform the same operations over its inner dataFrames*/
    @Override
    public Iterator<List<String>> iterator() { // to iterate over each dataframe in the directory
        return new Iterator<>() {
            int i = 0;
            Iterator<List<String>> childIterator = children.get(i).iterator();
            @Override
            public boolean hasNext() {
                return childIterator.hasNext() || i != children.size();
            }

            @Override
            public List<String> next() {
                if (!(childIterator.hasNext())) {
                    i++;
                    childIterator = children.get(i).iterator();
                    return childIterator.next();
                }
                return childIterator.next();
            }
        };
    }

    /*Returns all the values corresponding to the row and the label of all dataframes in the directory*/
    public String at(int row, String label) {
        StringBuilder dirAt = new StringBuilder();
        for(DataFrame child : children) {
            dirAt.append(child.at(row, label)).append(",");
        }
        return dirAt.toString();
    }

    /*Returns all the values corresponding to the numeric values of row and label of all dataframes in the directory*/
    public String iat(int row, int label) {
        StringBuilder dirIat = new StringBuilder();
        for(DataFrame child : children) {
            dirIat.append(child.iat(row, label)).append(",");
        }
        return dirIat.toString();
    }

    /*Returns the sum of all the columns of all the dataframes of the directory*/
    public int columns(){
        int result = 0;
        for(DataFrame child : children) {
            result = result + child.columns();
        }
        return result;
    }

    /*Returns the name of the dataframe*/
    public String getName() {
        return name;
    }

    /*Returns a merged list with all the sorted columns of all the dataframes in the directory*/
    public List<String> sort(String label, Comparator<String> comparator) {
        List<String> sortedDirList = new ArrayList<>();
        int i=1;
        for(DataFrame child : children) {
            sortedDirList.add("DataFrame"+i);
            sortedDirList.addAll(child.sort(label,comparator));
        }
        return sortedDirList;
    }

    /* Returns a merged hash map with the queries columns filtered */
    public LinkedHashMap<String,List<String>> query(String label, Predicate<String> predicate) {
        LinkedHashMap <String,List<String>> queryDirMap = new LinkedHashMap<>();
        int i = 0;
        for(DataFrame child : children) {
            queryDirMap = Stream.of(children.get(i).query(label,predicate), queryDirMap)
                    .flatMap(map -> map.entrySet().stream())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> new ArrayList<>(e.getValue()),
                            (left, right) -> {left.addAll(right); return left;}
                    ,LinkedHashMap::new));
        }
        return queryDirMap;
    }

}
