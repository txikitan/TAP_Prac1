/**---------------------------------------------------
- TAP JavaDataFrame: JUnit test class that provides
    different testing method for each pattern applied
    @author Gabriel Garcia
/----------------------------------------------------*/

import com.opencsv.exceptions.CsvException;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Test {

    Predicate<String> biggerThan = p ->{
        if(p.matches("[0-9]+")) return Integer.parseInt(p)>45;
        return false;
    };

    @org.junit.Test
    @DisplayName("Factory pattern test")
    public void factoryTest() throws IOException, ParseException, NoSuchFieldException, CsvException, IllegalAccessException {
        AbstractDataFrameFactory CSVFactory = new CSVDataFrameFactory();
        AbstractDataFrameFactory JSONFactory = new JSONDataFrameFactory();
        AbstractDataFrameFactory TXTFactory = new TXTDataFrameFactory();
        DataFrame CSVdf = CSVFactory.createDataFrame("cities.csv");
        Assert.assertNotNull(CSVdf);
        DataFrame JSONdf = JSONFactory.createDataFrame("cities.json");
        Assert.assertNotNull(JSONdf);
        DataFrame TXTdf = TXTFactory.createDataFrame("cities.txt");
        Assert.assertNotNull(TXTdf);
    }

    @org.junit.Test
    @DisplayName("API methods test")
    public void APITest() throws IOException, CsvException {
        DataFrame df = new CSVDataFrame("cities.csv");
        Assert.assertEquals(df.at(1,"LatM"),"52");
        Assert.assertEquals(df.iat(1,1),"52");
        Assert.assertEquals(df.size(),128);
        Assert.assertEquals(df.columns(),10);
        df.sort("LatD", Comparator.comparingInt(Integer::parseInt));
        df.query("LatD",biggerThan);
        for(List<String> col : df);
    }

    @org.junit.Test
    public void compositeTest() throws IOException, CsvException, ParseException {
        Directory directory = new Directory("Main dir");
        Directory subdirectory = new Directory("SubDir");
        Directory subdirectory2 = new Directory("SubDir2");
        subdirectory.addChild(new TXTDataFrame("cities.txt"));
        subdirectory2.addChild((new JSONDataFrame("cities.json")));
        subdirectory.addChild(subdirectory2);
        directory.addChild(new CSVDataFrame("cities.csv"));
        directory.addChild(subdirectory);
        directory.at(1,"LatM");
        directory.iat(1,1);
        Assert.assertEquals(directory.size(),384);
        Assert.assertEquals(directory.columns(),30);
        directory.sort("LatD",Comparator.comparingInt(Integer::parseInt));
        directory.query("LatD",biggerThan);
        for(List<String> col : directory);
        directory.removeChild(subdirectory);
        Assert.assertEquals(directory.size(),128);
    }

    @org.junit.Test
    public void visitorTest() throws IOException, CsvException, ParseException {
        Directory directory = new Directory("Main dir");
        Directory subdirectory = new Directory("SubDir");
        Directory subdirectory2 = new Directory("SubDir2");
        subdirectory.addChild(new TXTDataFrame("cities.txt"));
        subdirectory2.addChild((new JSONDataFrame("cities.json")));
        subdirectory.addChild(subdirectory2);
        directory.addChild(new CSVDataFrame("cities.csv"));
        directory.addChild(subdirectory);
        DataFrameVisitor sumVisitor = new SumVisitor();
        DataFrameVisitor minVisitor = new MinVisitor();
        DataFrameVisitor maxVisitor = new MaxVisitor();
        DataFrameVisitor averageVisitor = new AverageVisitor();
        directory.accept(sumVisitor,"NS");
        directory.accept(minVisitor,"NS");
        directory.accept(maxVisitor,"NS");
        directory.accept(averageVisitor,"NS");
    }

    @org.junit.Test
    public void proxyObserverTest() throws IOException, CsvException {
        List<Observer> observers= new ArrayList<>();
        observers.add(new LogObserver());
        observers.add(new QueryObserver("LonD"));
        IDataFrame df = (IDataFrame) DynamicProxy.newInstance(new CSVDataFrame("cities.csv"),observers);
        df.at(7,"LonD");
        df.at(7,"NS"); // This should not be logged
        df.iat(0,4);
    }
}
