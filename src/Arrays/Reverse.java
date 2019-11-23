package Arrays;

public class Reverse {

    public static void reverse(int[] arr) {
        // keep swapping the last and first element till you get to the middle
        // test with odd sized array and even sized also
        int i = 0, j = arr.length - 1;
        while(i < j) {
            // swap
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
            i++;
            j--;
        }
    }
}
