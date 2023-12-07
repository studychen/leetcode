package test;

import java.util.*;

/**
 * LC 2104
 * You are given an integer array nums. The range of a subarray of nums is the difference
 * between the largest and smallest element in the subarray.
 * <p>
 * Return the sum of all subarray ranges of nums.
 * <p>
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */
public class CalculateSubArrayRanges {

    public static void main(String[] args) {
        CalculateSubArrayRanges job = new CalculateSubArrayRanges();
        System.out.println(job.subArrayRanges(new int[]{1, 3, 3}) + " correct: 4");
        System.out.println(job.subArrayRanges(new int[]{1, 2, 3}) + " correct: 4");
    }

    int n;

    public long subArrayRanges(int[] nums) {
        n = nums.length;
        long[] mins = getMinLeftAndRightBoundary(nums);
        long[] maxs = getMaxLeftAndRightBoundary(nums);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (long) nums[i] * (maxs[i] - mins[i]);
        }

        return ans;
    }

    long[] getMinLeftAndRightBoundary(int[] ns) {
        int[] left = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && ns[stack.peek()] >= ns[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && ns[stack.peek()] > ns[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (long) (i - left[i]) * (right[i] - i);
        }

        return ans;
    }

    long[] getMaxLeftAndRightBoundary(int[] ns) {
        int[] left = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && ns[stack.peek()] <= ns[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && ns[stack.peek()] < ns[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (long) (i - left[i]) * (right[i] - i);
        }

        return ans;
    }
}
