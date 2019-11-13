package Miscelanious;

public class MoveZeros {

    public static void move(int[] arr) {
        // count number of zeros
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 0)
                count++;
        }

        // start at first element
        for(int i = 0; i < arr.length; i++) {

            if(arr[i] != 0) {
                // push the element back till it gets to the beginning or till it gets to a valid number
                int j = i - 1;
                int curr = i;
                while(j >= 0 && arr[j] == 0) {
                    // swap with the one before
                    arr[j] = arr[curr];
                    arr[curr] = 0;
                    j--;
                    curr--;
                }
            }
        }

        return;
    }
}
