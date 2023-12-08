package test;

import java.util.*;

/**
 * LC 2195. Append K Integers With Minimal Sum
 *
 * You are given an integer array nums and an integer k. Append k unique positive integers that
 * do not appear in nums to nums such that the resulting total sum is minimum.
 *
 * Return the sum of the k integers appended to nums.
 *
 * Example 1:
 *
 * Input: nums = [1,4,25,10,25], k = 2
 * Output: 5
 * Explanation: The two unique positive integers that do not appear in nums which we append are 2 and 3.
 * The resulting sum of nums is 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70, which is the minimum.
 * The sum of the two integers appended is 2 + 3 = 5, so we return 5.
 */
public class AppendKIntegersWithMinimalSum {

    public static void main(String[] args) {
        AppendKIntegersWithMinimalSum job = new AppendKIntegersWithMinimalSum();
        System.out.println(job.minimalKSum(new int[]{5, 6}, 6) + " correct: 25");
        System.out.println(job.minimalKSum(new int[]{1,4,25,10,25}, 2) + " correct: 5");
    }

    public long minimalKSum(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        long sum = (long) k * (k + 1) / 2;
        int next = k + 1;
        for (int num: set) {
            if (num <= k) {
                while (set.contains(next)) {
                    next++;
                }
                sum = sum - num + next;
                next++;
            }

        }

        return sum;
    }
}
