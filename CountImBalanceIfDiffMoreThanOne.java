package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array ranks of students in a school.
 * All students need to be split into groups k. Find the total
 * 'imbalance' of all groups. An imbalance of a group can be found as :
 * <p>
 * Sorting each group in the order of their ranks.
 * A group contributes to imbalance if any 2 CONSECUTIVE students in the sorted array have a rank difference of more than 1.
 * Find the total sum of imbalance of all such groups.
 * <p>
 * Note: Consider sub arrays only
 * This is the example that was given :
 * [4,1,3,2]
 * [1] imbalance = 0
 * [2] imbalance = 0
 * [3] imbalance = 0
 * [4] imbalance = 0
 * [4,1] => sorted [ 1, 4 ] => 4-1=3>1 => imbalance = 1 i.e 1 and 4 are not consecutive
 * [4,1,3] => sorted [ 1 3 4 ] => 4-3=1, 3-1=2>1 => imbalance = 1  i.e 1, 3 and 4 are not consecutive
 * [4,1,3,2] => sorted [ 1 2 3 4 ] => 4-3=1,3-2=1,2-1=1  => imbalance = 0  i.e 1,2,3 and 4 are consecutive
 * [1,3] => sorted [ 1, 3 ] => 3-1=2>1 => imbalance = 1 i.e 1 and 3 are not consecutive
 * [1,3,2] => sorted [ 1, 2, 3 ] => 3-2=1, 2-1=1 => imbalance = 0  i.e 1,2 and 3 are consecutive
 * [3,2] => sorted [ 2, 3 ] => 3-2=1 => imbalance = 0 i.e 2 and 3 are consecutive
 * Answer = 1 + 1 + 1 = 3
 */
public class CountImBalanceIfDiffMoreThanOne {

    public static void main(String[] args) {
        CountImBalanceIfDiffMoreThanOne s = new CountImBalanceIfDiffMoreThanOne();
        int[] x = new int[]{2, 1, 5, 3, 4};
        System.out.println(s.countInBalance(x));
        System.out.println(s.countInBalance2(x));

        int[] x2 = new int[]{7, 11, 9, 4, 1, 0, 9, 33, 3, 2};
        System.out.println(s.countInBalance(x2));
        System.out.println(s.countInBalance2(x2));

        int[] x3 = new int[]{4, 1, 1, 3, 3, 3, 2};
        System.out.println(s.countInBalance(x3));
        System.out.println(s.countInBalance2(x3));
    }

    int countInBalance2(int[] ns) {
        int ans = 0;
        for (int i = 0; i < ns.length; i++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < ns.length; j++) {
                min = Math.min(min, ns[j]);
                max = Math.max(max, ns[j]);
                set.add(ns[j]);
                // 1 1 3
                if (max - min - set.size() >= 0) {
                    ans++;
                }
            }
        }

        return ans;
    }

    int countInBalance(int[] ns) {
        int ans = 0;
        for (int i = 0; i < ns.length; i++) {
            // 4 1 2 3
            // 4
            // 4 1
            // 4 1 2
            for (int j = i; j < ns.length; j++) {
                int[] newNS = new int[j - i + 1];
                System.arraycopy(ns, i, newNS, 0, newNS.length);
                boolean flag = check(newNS);
                ans += flag ? 1 : 0;
            }
        }

        return ans;
    }

    boolean check(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] > 1) {
                return true;
            }
        }
        return false;
    }
}
