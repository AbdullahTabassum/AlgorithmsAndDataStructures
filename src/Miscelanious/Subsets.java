package Miscelanious;

import java.util.*;

public class Subsets {

    /// 3 ways,
    /// one with recursion
        // either include the current element or not, then recur
        // need to specify current element param: i
        // when i > length - 1, then we print the subset
        // so we need a way to track the current subset
            // when do we create a new subset
            // after we return from a recursive call, we remove the last added item
            // when we finish i, we create a copy of the list and it to the total lists List<List<Integer>>
    /// one with a queue
    /// one with bits
    public static void subsets(int[] nums) {
        //subsetsRecuson(new ArrayList<List<Integer>>(), nums, 0, new ArrayList<Integer>());
        subsetsWithQueues(nums);
    }

    private static void subsetsRecuson(List<List<Integer>> lists, int[] nums, int index, List<Integer> current) {
        if(index >= nums.length) {
            List<Integer> finalList = new ArrayList<Integer>();
            for(Integer i: current) {
                finalList.add(i);
                System.out.print(i + ",");
            }
            System.out.println("");
            // add the list to the total lists
            lists.add(finalList);
            return;
        }

        // for the current item, we can either add it, or not add it
        current.add(0, nums[index]);
        index = index + 1;
        subsetsRecuson(lists, nums, index, current);
        current.remove(0);
        subsetsRecuson(lists, nums, index, current);
    }

    private static void subsetsWithQueues(int[] nums) {
        // init queue with and empty set i.e. list
        // for each set in the queue, we will create 2 new sets
            // one set will contain the i'th element of nums, and the other set will not contain the i'th element of nums
        Queue<List<Integer>> lists = new LinkedList<>();
        // init with empty set
        lists.add(new ArrayList<Integer>());

        for(int i = 0; i < nums.length; i++) {
            // for each item/set/list in the queue, we will create 2 new items
            // one with nums[i] and one without
            Queue<List<Integer>> temp = new LinkedList<>();
            while(!lists.isEmpty()) {
                List<Integer> tempList = lists.remove();
                List<Integer> setWith = new ArrayList<>();
                List<Integer> setWithout = new ArrayList<>();

                setWith.addAll(tempList);
                setWith.add(nums[i]);
                setWithout.addAll(tempList);
                temp.add(setWith);
                temp.add(setWithout);
            }
            lists = temp;
        }

        // print the list
        Iterator<List<Integer>> iter = lists.iterator();
        while(iter.hasNext()) {
            List<Integer> list = iter.next();
            for(Integer i: list) {
                System.out.print(i + ",");
            }
            System.out.println("");
        }

    }

    private static void subsetsWithBits(int[] nums) {

    }
}
