package Sorting;
/*
In computer graphics it is popular for its capability to detect a very small error
(like swap of just two elements) in almost-sorted arrays and fix it with just linear
 complexity (2n). For example, it is used in a polygon filling algorithm, where bounding
 lines are sorted by their x coordinate at a specific scan line (a line parallel to x axis)
 and with incrementing y their order changes (two elements are swapped) only at
 intersections of two lines (Source: Wikipedia)
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        // compare the current value to the next value
        // if they are out of order then swap them
        // we will iterate until the second last element
        // note that every time we do one bubble sort, we don't need to check the last element the next time
        // this is because bubble sort "bubbles" up the largest element each iteration
        // so we can continually decrease the size of the array we need to check
        // In other words, we are continually adding the next highest item to the end of the array
        // in its correct position
        // this implies, after each iteration (of inner for loop), the sorted array at the end
        // increases in size
        for(int e = arr.length; e > 0; e--) {
            for (int i = 0; i < e - 1; i++) {
                if (arr[i] < arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }

    public static void bubbleSort2(int[] arr) {
        for(int subArrSize = 0; subArrSize < arr.length; subArrSize++) {
            // we start from the beginning and go up to the beginning of the sorted array
            for(int j = 0; j < (arr.length - subArrSize); j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}
