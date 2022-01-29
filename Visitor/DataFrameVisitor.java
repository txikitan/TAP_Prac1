/*---------------------------------------------------
- TAP JavaDataFrame: Visitor interface that defines the
    visitor method to do operations over DataFrames without
    modifying de actual Dataframe classes
    Gabriel Garcia
/----------------------------------------------------*/
public interface DataFrameVisitor {
    void visit(DataFrame df,String label);
}
