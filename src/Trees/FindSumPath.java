package Trees;

import java.util.HashSet;
import java.util.Set;

public class FindSumPath {

    private static Set<Integer> accumulatedSumOnPath = new HashSet<>();

    public static boolean hasSum(TreeNode tn, int sumToFind, int currentSum) {
        //add the current total sum to the accumulatedSum
        if(tn == null)
            return false;

        accumulatedSumOnPath.add(currentSum + tn.value);

        // check if with the current node we get any valid path equaling the sum
        if(accumulatedSumOnPath.add(currentSum + tn.value - sumToFind)) {
            return true;
        }

        boolean left = hasSum(tn.left, sumToFind, currentSum + tn.value);
        boolean right = hasSum(tn.right, sumToFind, currentSum + tn.value);

        //remove the current total sum from the accumulatedSum
        accumulatedSumOnPath.remove(currentSum + tn.value);
        return left || right;
    }
}
