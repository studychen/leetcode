package test;

import java.util.*;

/**
 * This problem requires finding the optimal pairs of foreground and background applications to maximize
 * the utilization of a given device's memory. Each application is identified by a unique ID and requires
 * a specific amount of memory. The device has a maximum memory capacity,
 * <p>
 * and the goal is to find pairs of applications (one foreground and one background) that use memory optimally.
 * <p>
 * Input:
 * deviceCapacity = 7
 * foregroundAppList = [[1, 2], [2, 4], [3, 6]]
 * backgroundAppList = [[1, 1]]
 * Output:
 * [[3, 1]]
 * <p>
 * Input:
 * deviceCapacity = 10
 * foregroundAppList = [[1, 3], [2, 5], [3, 7]]
 * backgroundAppList = [[1, 2], [2, 3], [3, 4], [4, 5]]
 * Output:
 * [[2, 4], [3, 2]]
 * Explanation:
 * There are two optimal pairs: [2, 4] and [3, 2], both requiring a total memory of 5, which fits the device capacity of 10.
 * <p>
 * Input:
 * deviceCapacity = 16
 * foregroundAppList = [[2, 7], [3, 14]]
 * backgroundAppList = [[2, 10], [3, 14]]
 * Output:
 * []
 * Explanation:
 * No pairs satisfy the conditions, so an empty list is returned.
 */
public class PairNumberGivenLimitation {

    public static void main(String[] args) {
        // Example 1
        int deviceCapacity1 = 7;
        List<List<Integer>> foregroundAppList1 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 4), Arrays.asList(3, 6));
        List<List<Integer>> backgroundAppList1 = Arrays.asList(Arrays.asList(1, 1));
        System.out.println(applicationPairs(deviceCapacity1, foregroundAppList1, backgroundAppList1));

        // Example 2
        int deviceCapacity2 = 10;
        List<List<Integer>> foregroundAppList2 = Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 5), Arrays.asList(3, 7));
        List<List<Integer>> backgroundAppList2 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4), Arrays.asList(4, 5));
        System.out.println(applicationPairs(deviceCapacity2, foregroundAppList2, backgroundAppList2));

        // Example 22
        int deviceCapacity22 = 10;
        List<List<Integer>> foregroundAppList22 = Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 5), Arrays.asList(4, 5), Arrays.asList(3, 7));
        List<List<Integer>> backgroundAppList22 = Arrays.asList(Arrays.asList(4, 5), Arrays.asList(6, 5), Arrays.asList(5, 5), Arrays.asList(2, 3), Arrays.asList(1, 2), Arrays.asList(3, 4));
        System.out.println(applicationPairs(deviceCapacity22, foregroundAppList22, backgroundAppList22));

        // Example 3
        int deviceCapacity3 = 16;
        List<List<Integer>> foregroundAppList3 = Arrays.asList(Arrays.asList(2, 7), Arrays.asList(3, 14));
        List<List<Integer>> backgroundAppList3 = Arrays.asList(Arrays.asList(2, 10), Arrays.asList(3, 14));
        System.out.println(applicationPairs(deviceCapacity3, foregroundAppList3, backgroundAppList3));

        // Example 4
        int deviceCapacity4 = 9;
        List<List<Integer>> foregroundAppList4 = Arrays.asList(Arrays.asList(1, 5), Arrays.asList(3, 4), Arrays.asList(2, 3), Arrays.asList(4, 1), Arrays.asList(5, 4));
        List<List<Integer>> backgroundAppList4 = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 5), Arrays.asList(2, 5), Arrays.asList(4, 6));
        System.out.println(applicationPairs(deviceCapacity4, foregroundAppList4, backgroundAppList4));
    }


    public static List<List<Integer>> applicationPairs(int target, List<List<Integer>> first, List<List<Integer>> second) {
        List<List<Integer>> result = new ArrayList<>();
        Collections.sort(first, Comparator.comparingInt(a -> a.get(1)));
        Collections.sort(second, Comparator.comparingInt(a -> a.get(1)));

        int maxMemory = 0;
        int left = 0, right = second.size() - 1;

        while (left < first.size() && right >= 0) {
            int curr = first.get(left).get(1) + second.get(right).get(1);

            if (curr > target) {
                right--; // Decrease background pointer to reduce
            } else {
                if (curr > maxMemory) {
                    maxMemory = curr;
                    result.clear(); // Clear previous pairs since we found a new maximum
                }

                if (curr == maxMemory) {
                    // the valid pair, to consider duplicate cases
                    // left not move, move right to find duplicate cases
                    result.add(Arrays.asList(first.get(left).get(0), second.get(right).get(0)));
                    int copyRight = right;
                    while (copyRight - 1 >= 0 && Objects.equals(second.get(copyRight).get(1), second.get(copyRight - 1).get(1))) {
                        result.add(Arrays.asList(first.get(left).get(0), second.get(copyRight - 1).get(0)));
                        copyRight--;
                    }
                }
                left++; // Increase foreground pointer to increase memory usage
            }
        }

        return result;
    }

}
