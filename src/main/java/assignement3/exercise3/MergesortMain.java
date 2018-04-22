package assignement3.exercise3;

import utils.InitializeArray;

import java.util.Arrays;

public class MergesortMain {


    private final static int arrayLength = 10;

    public static void main(final String... args) throws Exception {
        int[] array = InitializeArray.initializeArray(arrayLength);
        mergeSort(array);
        System.out.println(array[0]);
        for (int i = 1; i < arrayLength; i++){
            System.out.println(array[i]);

        }




    }
    public static void mergeSort(int[] array) {
        if (array.length > 2) {
            int[] firstHalf = Arrays.copyOf(array, array.length / 2);
            mergeSort(firstHalf);

            int[] secondHalf = Arrays.copyOfRange(array, array.length - (array.length / 2) , array.length);
            mergeSort(secondHalf);

            merge(firstHalf, secondHalf, array);
        }

    }

    public static void merge(int[] firstHalf, int[] secondHalf, int[] temp) {
        int indexFirstHalf = 0;
        int indexSecondHalf = 0;
        int indexTemp = 0;


        while (indexFirstHalf < firstHalf.length && indexSecondHalf < secondHalf.length) {
            if (firstHalf[indexFirstHalf] < secondHalf[indexSecondHalf])
                temp[indexTemp++] = firstHalf[indexFirstHalf++];
            else
                temp[indexTemp++] = secondHalf[indexSecondHalf++];
        }

        while (indexFirstHalf < firstHalf.length)
            temp[indexTemp++] = firstHalf[indexFirstHalf++];

        while (indexSecondHalf < secondHalf.length)
            temp[indexTemp++] = secondHalf[indexSecondHalf++];
    }
}
