package Sorting;
// The good thing about selection sort is it never makes more than O(n) swaps and can be useful when memory write is a costly operation.
public class SelectionSort {

    public static void selectionSort(int[] arr) {
        // go through the array and pick the smallest element
        // add it to the front of the array, this will be the partition
        // the next iteration, we start from the end of the partition
        int part = 0;
        for(int p = 0; p < arr.length - 1; p++) {
            int smallest = p;
            for(int i = p + 1; i < arr.length; i++) {
                if(arr[i] < arr[smallest]) {
                    smallest = i;
                }
            }
            // swap whatever was at the smallest index with whatever is at p
            int temp = arr[p];
            arr[p] = arr[smallest];
            arr[smallest] = temp;
        }

    }
}
