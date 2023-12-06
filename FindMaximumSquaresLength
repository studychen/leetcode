package test;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unordered array of integers, find the maximum length of a sequence of perfect squares.
 * For example, for the array [2, 8, 9, 16, 4, 3],
 * the output should be 3,
 * corresponding to the perfect square sequence 2, 4, 16.
 * If input is [2, 3, 5], the result is
 * `NOTE` similar to LC 128
 */
public class FindMaximumSquaresLength {

    public static void main(String[] args) {
        FindMaximumSquaresLength ans = new FindMaximumSquaresLength();
        System.out.println(ans.findMaxLength(new int[]{2, 8, 9, 16, 4, 3}));
        System.out.println(ans.findMaxLength(new int[]{2, 8, 256, 257, 255, 9, 32, 16, 4, 3}));
        System.out.println(ans.findMaxLength(new int[]{2, 3, 5}));
    }

    int findMaxLength(int[] arr) {
        Set<Long> set = new HashSet<>();
        for (int a : arr) {
            set.add((long) a);
        }

        int max = 0;
        for (long num : set) {
            if (num != 2) {
                if (!isPerfectSquare(num)) {
                    continue;
                }
                if (set.contains((long) Math.sqrt(num))) {
                    continue;
                }
            }
            int curr = 1;
            while (set.contains(num * num)) {
                num *= num;
                curr++;
            }
            max = Math.max(max, curr);
        }

        return max;
    }

    boolean isPerfectSquare(long num) {
        long sqrt = (long) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
