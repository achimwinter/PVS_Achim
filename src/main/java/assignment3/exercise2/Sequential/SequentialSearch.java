package assignment3.exercise2.Sequential;

public class SequentialSearch {

    private final int[] array;
    private final int searchingNumber;

    public SequentialSearch(int[] array, int searchingNumber){
        this.searchingNumber = searchingNumber;
        this.array = array;
    }

    public int searchForElement(){
        for (int i = 0; i < array.length; i++){
            if(array[i] == searchingNumber)
                return i;
        }
        return -1;
    }


}
