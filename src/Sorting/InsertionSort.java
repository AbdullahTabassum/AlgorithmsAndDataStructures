package Sorting;
// Insertion sort is used when number of elements is small.
// It can also be useful when input array is almost sorted,
// only few elements are misplaced in complete big array.
public class InsertionSort {
    public static void insertionSort(int[] arr) {
        // we keep a partition in the array
        // it is a subarray of array in the beginning
        // keep track of the end of the partition array
        // if the newly discovered element is smaller than the last element in the partition
        // then we loop from the end of the partition till we find its place
        // while we are finding its place we are shifting each element right in the process


        // start from the second one
        for(int i = 1; i<arr.length; i++) {
            // if the current guy is smaller than the last element of the partition, then keep
            // shifting partition items ahead by one till you find a place that is not bigger
            // you will insert the current guy there

            // look at the element before
            int j = i-1;
            int value = arr[i];

            while(j>=0 && arr[j] > value) {
                arr[j+1] = arr[j];
                j--;
            }

            // now put the element one elemement after j
            arr[j+1] = value;
        }

    }
}
