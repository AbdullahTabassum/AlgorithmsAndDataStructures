package Sorting;

import java.util.List;
/*
Quick Sort in its general form is an in-place sort (i.e. it doesn’t require any extra storage) whereas merge sort requires O(N) extra storage, N denoting the array size which may be quite expensive. Allocating and de-allocating the extra space used for merge sort increases the running time of the algorithm. Comparing average complexity we find that both type of sorts have O(NlogN) average complexity but the constants differ. For arrays, merge sort loses due to the use of extra O(N) storage space.
Most practical implementations of Quick Sort use randomized version. The randomized version has expected time complexity of O(nLogn). The worst case is possible in randomized version also, but worst case doesn’t occur for a particular pattern (like sorted array) and randomized Quick Sort works well in practice.

Quick Sort is also a cache friendly sorting algorithm as it has good locality of reference when used for arrays.

Quick Sort is also tail recursive, therefore tail call optimizations is done.

In case of linked lists the case is different mainly due to difference in memory allocation of arrays and linked lists. Unlike arrays, linked list nodes may not be adjacent in memory. Unlike array, in linked list, we can insert items in the middle in O(1) extra space and O(1) time. Therefore merge operation of merge sort can be implemented without extra space for linked lists.

In arrays, we can do random access as elements are continuous in memory. Let us say we have an integer (4-byte) array A and let the address of A[0] be x then to access A[i], we can directly access the memory at (x + i*4). Unlike arrays, we can not do random access in linked list. Quick Sort requires a lot of this kind of access. In linked list to access i’th index, we have to travel each and every node from the head to i’th node as we don’t have continuous block of memory. Therefore, the overhead increases for quick sort. Merge sort accesses data sequentially and the need of random access is low.
 */
public class QuickSort {
    // choose a pivot, i.e. the last item in the
    // call a method, partition, that will inplace partition a portion of the list
        // i.e. it will take in a list, the beginning index of the portion and the last index
        // it will place all items smaller than the pivot to the left of it vice versa
        // it will return the final index of pivot
    // recur to the left and right side of the pivot
    public void quickSortHelper(int[] list, int low, int high){

        int newPivot = partition(list, low, high);

        quickSortHelper(list, low, newPivot);
        quickSortHelper(list, newPivot + 1, high);
    }

    private int partition(int[] arr, int low, int high) {

        // take the last element as the pivot
        int pivot = arr[high];

        // we start 1 before the lowest element
        // this is because we want to use the current subarray as the partition array
        // we will go through the original subarray, and everytime we see a number that is less
        // than the pivot we will put it at the end of out our parition and grow our partition (increment i)
        // if an element is greater than the pivot we don't increase i (end of partition)
        // if an element is less than the pivot, we swap it with the item after the end of the partition
        // then increase the partition
        // i is basically a pointer to the last index of our partition
        int i = low - 1;
        for(int j = low; j < high - 1; j++) {
            if(arr[j] < pivot) {
                // make one more space in the partition
                i++;
                // swap whatever is at j with whatever is at the new i (something that was not in partition)
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // we will keep going through the loop until we hit the second last element
        // the last element is where the pivot originally was
        // i will now be the position where everything after it is greater than pivot
        // so swap arr[i+1] with arr[high] i.e. pivot
        int temp = arr[i+1];
        arr[i+1] = pivot;
        arr[high] = temp;

        // now return the pivot's final position
        return i+1;
    }

}
