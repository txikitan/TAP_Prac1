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

public class Directory extends DataFrame implements IDataFrame {
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
        super.columns = super.columns + child.columns;
        super.rows = super.rows + child.rows;
    }

    /*Removes a dataframe from the directory*/
    public void removeChild(DataFrame child) {
        children.remove(child);
        super.columns = super.columns - child.columns;
        super.rows = super.rows - child.rows;
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
    /*Iterator that will iterate over each inner dataframe of the directory through columns following the same idea of the simple Df iterator*/
    public Iterator<List<String>> iterator() {
        return new Iterator<List<String>>() {
            int i = 0;
            Iterator<List<String>> childIterator = children.get(i).iterator();  // will iterate over every dataframe of the directory
            @Override
            public boolean hasNext() {
                return childIterator.hasNext() || i != children.size()-1; // will have next if we are not at the end of the last column of the last directory's dataframe
            }

            @Override
            public List<String> next() {
                if (!(childIterator.hasNext())) { // if we are at the end of the current dataframe of the dir
                    i++;
                    childIterator = children.get(i).iterator(); // we jump to the next dataframe of the directory
                    return childIterator.next();
                }
                return childIterator.next(); // otherwise, we will still be iterating over the inner dataframe of the directory
            }
        };
    }

    /*Returns all the values corresponding to the row and the label of all dataframes in the directory*/
    public String at(int row, String label) {
        StringBuilder dirAt = new StringBuilder();
        for(DataFrame child : children) {
            if(child.at(row,label)!=null) {
                dirAt.append(child.at(row, label));
                if(child instanceof Directory) dirAt.append("->Directory;");
                else dirAt.append("->Df;");
            }
        }
        return dirAt.toString();
    }

    /*Returns all the values corresponding to the numeric values of row and label of all dataframes in the directory*/
    public String iat(int row, int label) {
        StringBuilder dirIat = new StringBuilder();
        for(DataFrame child : children) {
            if(child.iat(row,label)!=null) {
                dirIat.append(child.iat(row, label));
                if(child instanceof Directory) dirIat.append("->Directory;");
                else dirIat.append("->Df;");
            }
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
        List<String> sortedDirList = new ArrayList<>(); // full final list
        for(DataFrame child : children) { // we will sort all the content of the directory
            if(child instanceof Directory) sortedDirList.add("Directory;");
            else sortedDirList.add("Df;");
            if(child.sort(label,comparator)!=null) {
                sortedDirList = Stream.of(sortedDirList, child.sort(label, comparator)) // we start merging the sorted list and the full final list opening a stream
                        .flatMap(Collection::stream)    // we use a flatmap because we are merging two lists
                        .collect(Collectors.toList()); // collect the values to the full final list
            }
        }
        return sortedDirList;
    }

    /* Returns a merged hash map with the queries columns filtered */
    public LinkedHashMap<String,List<String>> query(String label, Predicate<String> predicate) {
        LinkedHashMap <String,List<String>> queryDirMap = new LinkedHashMap<>(); // final merged map
        for(DataFrame child : children) {   // we will query all the dataframes of the directory
            queryDirMap = Stream.of(child.query(label,predicate), queryDirMap) // open a stream to merge the queried map and the final merged map
                    .flatMap(map -> map.entrySet().stream())         // we open a flatmap because we are working with maps that contain lists as values
                    .collect(Collectors.toMap(  // collect the values to map them
                            Map.Entry::getKey,
                            e -> new ArrayList<>(e.getValue()),
                            (left, right) -> {left.addAll(right); return left;} // if there are collisions, just concatenate the lists over the same key (merge)
                    ,LinkedHashMap::new)); // the type of map which we want to have all the queries merged
        }
        return queryDirMap;
    }

    /*Visitor pattern accept method that executes accept over each dataframe of the directory*/
    public void accept(DataFrameVisitor v, String label){
        for (DataFrame child : children ) {
            child.accept(v,label);
        }
    }


}
