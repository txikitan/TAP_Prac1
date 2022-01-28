/*---------------------------------------------------
- TAP JavaDataFrame: Directory class that applies the
        composite pattern to have directories of dataframes,
        and even directories of subdirectories.
    Gabriel Garcia
/----------------------------------------------------*/

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
    public String at(int index, int row, String label) {
        return this.children.get(index).at(row,label);
    }



}
