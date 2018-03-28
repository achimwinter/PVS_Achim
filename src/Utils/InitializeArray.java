package Utils;

import java.util.concurrent.ThreadLocalRandom;

public class InitializeArray {

    public static int[] initializeArray(int length){
        int[] data = new int[length];

        for(int i = 0; i < length; ++i) {
            data[i] = ThreadLocalRandom.current().nextInt(0, 150);
        }

        return data;


    }
}
