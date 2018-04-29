package assignment3.exercise3;

import assignment3.exercise3.SequentialSort.SequentialSort;
import utils.InitializeArray;

public class MergeSortMain {

  final static int arrayLength = 10;

  public static void main(String... args) {
    int[] array =
        InitializeArray.initializeArray(arrayLength);
    SequentialSort seqSort = new SequentialSort();
    int[] newArray = seqSort.mergeSort(array);
    for (int i : newArray) {
      System.out.println(i);
    }

  }


}
