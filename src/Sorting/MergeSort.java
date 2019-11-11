package Sorting;
/*

Merge Sort is useful for sorting linked lists in O(nLogn) time.
In the case of linked lists, the case is different mainly due to
the difference in memory allocation of arrays and linked lists.
Unlike arrays, linked list nodes may not be adjacent in memory.
Unlike an array, in the linked list, we can insert items in the
middle in O(1) extra space and O(1) time. Therefore merge operation
of merge sort can be implemented without extra space for linked lists.
In arrays, we can do random access as elements are contiguous in memory. Let us say we have an integer (4-byte) array A and let the address of A[0] be x then to access A[i], we can directly access the memory at (x + i*4). Unlike arrays, we can not do random access in the linked list. Quick Sort requires a lot of this kind of access. In linked list to access i’th index, we have to travel each and every node from the head to i’th node as we don’t have a continuous block of memory. Therefore, the overhead increases for quicksort. Merge sort accesses data sequentially and the need of random access is low.

Inversion Count Problem
 */
public class MergeSort {
    // keep dividing the arr in half
    // base case is when the there is only one item, then return
    public static void mergeSort(int[] arr, int left, int right) {
        // there is only element, its automatically sorted by definition
        if(left == right)
            return;

        int middle = (left + right)/2;
        // now sort everthing on the left
        mergeSort(arr, left, middle);
        // sort everything in the right half
        mergeSort(arr, middle+1, right);

        //merge the two sorted halves
        mergeInPlace(arr, left, middle, right);
    }

    private static void mergeInPlace(int[] arr, int l, int m, int r) {
        // in place merge
        // start one pointer at l, and one at right = m +1
        // if l is less than right, increment l and don't swap anything
        // if right is less than l, then swap r and l and increment both l and r
        // keep going until you either hit m or you hit r
        int right = m+1;

    }

    private static void merge(int[] arr, int l, int m, int r) {
        // TODO: need to understand how to get these bounds easily
        int sizeL = m - l + 1;
        int sizeR = r -m;
        int[] left = new int[sizeL];
        int[] right = new int[sizeR];

        //copy items in left half into array
        for(int i = 0; i < sizeL; i++) {
            left[i] = arr[l + i];
        }
        for(int i = 0; i < sizeR; i++) {
            right[i] = arr[m+1+i];
        }

        // now copy the items into the original array one by one
        int leftI = 0, rightI = 0, orig = l;
        while(leftI < sizeL && rightI < sizeR) {
            if(left[leftI] < right[rightI]) {
                //int temp = arr[l];
                arr[orig] = left[leftI];
                leftI++;
            } else {
                arr[orig] = right[rightI];
                rightI++;
            }
            orig++;
        }

        // now that we copied each half into the array,
        // one of the halves needs to be fully copied into the tail
        if(leftI < sizeL) {
            while(leftI < sizeL)
                arr[orig] = left[leftI];
            leftI++;
            orig++;
        }

        if(rightI < sizeR) {
            while(rightI < sizeR)
                arr[orig] = left[rightI];
            rightI++;
            orig++;
        }
    }
}
