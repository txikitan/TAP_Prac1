# TAP_Prac1
Gabriel Garcia Rodriguez (gabriel.garcia@estudiants.urv.cat)
Design patterns implemented:
  Factory
  Composite
  Visitor
  Dynamic Proxy and Observer to it
  
  NOTES:
  -Project has 3 test files(cities), one in each supported format which should be equal when imported.
  -Demonstration main implemented and also single JUNIT tests for each feature.
  -The query method returns a new LinkedList with the queryed column already filtered, it applies a filter with a predicate while mapping the values using streams, same      with the composite query
  -Sort method in the composite returns a list with the concrete columns of each dataframe sorted and separated by a text identifyer between each sorted list.
  -Due to the nature of a Dynamic Proxy, we can't attach observers directly to it, so we will pass a list of observers when calling the newInstance method.
  
  ALL THIS SPECIAL FEATURES ARE COMMENTED IN THE CODE TOO.
