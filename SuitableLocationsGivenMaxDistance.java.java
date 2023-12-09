package test;

import java.util.*;

/**
 * The problem is to find a transportation center within a given array of
 * logistics centers such that the distance from this center to every
 * logistics center in the array is less than or equal to a specified
 * target distance. The array represents the locations of logistics centers along a line,
 * and the goal is to determine the number of suitable transportation centers
 * meeting the distance criterion. The provided solution uses a binary search and
 * two pointers to efficiently compute the count of such transportation centers,
 * accounting for both even and odd-sized arrays.
 *
 * Example: [-2,0,1] , maxDis = 5
 * Output: 4 possibilities,  -2,dis=5; -1,dis=4; 0,dis=3; 1,dis=4
 *
 * Note: sometimes distance need divide by 2
 */
public class SuitableLocationsGivenMaxDistance {

    public static void main(String[] args) {
        System.out.println("=== Odd Start To find rules ===");
        int[] deliveryCenters2 = {-13, -9, -5, -2, 0, 7, 11, 15, 17};
        for (int j = 3; j >= -16; j--) {
            System.out.println(calculateDis(deliveryCenters2, j) + " | " + j);
        }
        for (int j = 0; j <= 17; j++) {
            System.out.println(calculateDis(deliveryCenters2, j) + " | " + j);
        }
        System.out.println("=== Odd End ===");


        System.out.println("=== Even Start To find rules ===");
        int[] deliveryCenters22 = {-13, -9, -5, -2, 0, 3, 7, 11, 15, 17};
        for (int j = 3; j >= -16; j--) {
            System.out.println(calculateDis(deliveryCenters22, j) + " | " + j);
        }
        for (int j = 0; j <= 17; j++) {
            System.out.println(calculateDis(deliveryCenters22, j) + " | " + j);
        }
        System.out.println("=== Even End ===");

        System.out.println("=== Odd Start ===");
        for (int t = 15; t < 60; t++) {
            int[] array = new int[]{-5, -2, 1, 1, 1, 3, 7};
            System.out.println(getN(array, t));
            System.out.println(getN2(array, t));
            System.out.println(treeMap);
        }
        System.out.println("=== Odd End ===");

        System.out.println("=== Even Start ===");
        for (int t = 25; t < 80; t++) {
            int[] array = new int[]{-5, -2, -2, -2, 1, 1, 3, 3, 5, 7};
            System.out.println(getN(array, t));
            System.out.println(getN2(array, t));
            System.out.println(treeMap);
        }
        System.out.println("=== Even End ===");

        System.out.println("=== Example Start ===");
        for (int t = 2; t < 9; t++) {
            int[] array = new int[]{-2, 1, 0};
            System.out.println(getN(array, t));
            System.out.println(getN2(array, t));
            System.out.println(treeMap);
        }
        System.out.println("=== Example End ===");
    }

    static int target;
    static int n;
    static int[] cs;

    /**
     * way 2
     */
    public static int getN2(int[] centers, int distance) {
        target = distance;
        n = centers.length;
        cs = centers;
        Arrays.sort(cs);

        // 0 1 2 3 4
        int mid = n / 2;
        int minDis = calculateDis(cs, centers[mid]);

        if (n % 2 == 1) {
            int leftCnt = getOneSideChances(minDis, -1, mid, cs[mid], -1);
            int rightCnt = getOneSideChances(minDis, 1, mid, cs[mid], -1);
            return Math.max(0, leftCnt + rightCnt - 1);
        } else {
            int leftCnt = getOneSideChances(minDis, -1, mid - 1, cs[mid - 1], 0);
            int rightCnt = getOneSideChances(minDis, 1, mid, cs[mid], 0);
            int betweenLeftAndRight = cs[mid] - cs[mid - 1];
            return Math.max(0, leftCnt + rightCnt - 1 + betweenLeftAndRight);
        }
    }

    private static int getOneSideChances(int total, int plusV, int idx, int curr, int step) {
        // -5,-2,0,1,3
        int cnt = 0;
        while (total <= target) {
            cnt++;
            while (idx >= 0 && idx < n && curr == cs[idx]) {
                idx += plusV;
                step += 2;
            }
            curr += plusV;
            total += step;
        }
        return cnt;
    }

    static int ans;

    static TreeMap<Integer, Integer> treeMap;

    /**
     * way 1
     */
    public static int getN(int[] centers, int distance) {
        ans = 0;
        treeMap = new TreeMap<>();
        int min = Arrays.stream(centers).min().getAsInt();
        int max = Arrays.stream(centers).max().getAsInt();
        int l = min - distance;
        int r = distance + max;
        find(l, r, centers, distance);

        return ans;
    }

    private static void find(int l, int r, int[] centers, int distance) {
        if (l <= r) {
            int mid = l + (r - l) / 2;
            int m = calculateDis(centers, mid);
            if (m <= distance) {
                ans++;
                treeMap.put(mid, m);
            }
            find(l, mid - 1, centers, distance);
            find(mid + 1, r, centers, distance);
        }
    }

    private static int calculateDis(int[] cs, int d) {
        int ans = 0;
        for (int c : cs) {
            ans += Math.abs(c - d);
        }
        return ans;
    }

}
