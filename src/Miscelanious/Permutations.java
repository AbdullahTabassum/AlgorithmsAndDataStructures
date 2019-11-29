package Miscelanious;

import java.util.*;

public class Permutations {

    public static void permute(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        permuteHelper(numSet, nums, new ArrayList<Integer>());
    }

    private static void permuteHelper(Set<Integer> set, int[] nums, List<Integer> current) {
        if(set.size() == nums.length) {
            // print out the current set and return
            for(Integer i: current) {
                System.out.print(i + ",");
            }
            System.out.println("");
            return;
        }
        // each element in the set, should have the chance to be place at each index
        for(int i = 0; i < nums.length; i++) {
            if(!set.contains(nums[i])) {
                current.add(0,nums[i]);
                set.add(nums[i]);
                permuteHelper(set, nums, current);
                current.remove(0);
                set.remove(nums[i]);
            }
        }
    }

    // this method is better because it is in-place
    // the way that this works is, iterating over the positions and swapping positions with every index after the current position
    // every recurse will have look at a new position. Each recurse will swap with all elements after it.
    // why don't we look at the elements before the position? i.e. each time we recurse, we iterate over less elements
    // why? don't we need to put each number at each position? why aren't we considering numbers prior for current position?
    // this is because (consider the last position). Each recurse iterates till the last position.
    // this means each item at some point gets swapped with the last position.
    // this means that each number already shows up in the last position
    class Solution {
        public void backtrack(int n,
                              ArrayList<Integer> nums,
                              List<List<Integer>> output,
                              int first) {
            // if all integers are used up
            if (first == n)
                output.add(new ArrayList<Integer>(nums));
            for (int i = first; i < n; i++) {
                // place i-th integer first
                // in the current permutation
                Collections.swap(nums, first, i);
                // use next integers to complete the permutations
                backtrack(n, nums, output, first + 1);
                // backtrack
                Collections.swap(nums, first, i);
            }
        }

        public List<List<Integer>> permute(int[] nums) {
            // init output list
            List<List<Integer>> output = new LinkedList();

            // convert nums into list since the output is a list of lists
            ArrayList<Integer> nums_lst = new ArrayList<Integer>();
            for (int num : nums)
                nums_lst.add(num);

            int n = nums.length;
            backtrack(n, nums_lst, output, 0);
            return output;
        }
    }
}
