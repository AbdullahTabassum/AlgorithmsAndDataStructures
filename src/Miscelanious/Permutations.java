package Miscelanious;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
