package src;

import java.util.ArrayList;
public class Utils {

  // determines whether two ArrayLists are equal regardless of order of elements 
  public <T> boolean equalsWithoutOrder(ArrayList<T> first, ArrayList<T> second) {
    return first.size() == second.size() 
      && first.containsAll(second)
      && second.containsAll(first);
  }
}