package assignment3.exercise3.SequentialSort;

import java.util.Arrays;

public class SequentialSort {

  public int[] mergeSort(int[] array) {
    if (array.length <= 2) {
      return array;
    } else {
      int[] firstHalf = Arrays.copyOfRange(array, 0, array.length / 2);
      firstHalf = mergeSort(firstHalf);

      int[] secondHalf = Arrays
          .copyOfRange(array, array.length - (array.length / 2), array.length -1);
      secondHalf = mergeSort(secondHalf);

      return merge(firstHalf, secondHalf);
    }

  }

  private int[] merge(int[] firstHalf, int[] secondHalf) {
    int firstHalfIndex = 0;
    int secondHalfIndex = 0;
    int newArrayIndex = 0;

    int[] newArray = new int[firstHalf.length + secondHalf.length];

    while (firstHalfIndex < firstHalf.length && secondHalfIndex < secondHalf.length) {
      if (firstHalf[firstHalfIndex] <= secondHalf[secondHalfIndex]) {
        newArray[newArrayIndex] = firstHalf[firstHalfIndex];
        firstHalfIndex++;
        newArrayIndex++;
      } else if (secondHalf[secondHalfIndex] <= firstHalf[firstHalfIndex]) {
        newArray[newArrayIndex] = secondHalf[secondHalfIndex];
        secondHalfIndex++;
        newArrayIndex++;
      }
    }

    while (firstHalfIndex < firstHalf.length) {
      newArray[newArrayIndex] = firstHalf[firstHalfIndex];
      firstHalfIndex++;
      newArrayIndex++;
    }

    while (secondHalfIndex < secondHalf.length) {
      newArray[newArrayIndex] = secondHalf[secondHalfIndex];
      secondHalfIndex++;
      newArrayIndex++;
    }

    return newArray;
  }

}
