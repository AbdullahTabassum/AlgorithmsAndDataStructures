package Sorting;

public class BinarySearch {
    public static boolean search(int[] arr, int item, int l, int r) {
        if(l == r && arr[l] != item)
            return false;

        // start in the middle
        int middle = (l+r)/2;

        // if the middle is bigger than the value we are looking for, look to left side
        // other wise look to right
        if(arr[middle] == item)
            return true;
        else if(arr[middle] < item)
            return search(arr, item, middle+1, r);
        else
            return search(arr, item, l, middle);
    }
}
