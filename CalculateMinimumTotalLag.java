package test;

import java.util.PriorityQueue;

/**
 * Given two lists center and destination, find the Minimum Total Lag
 */
public class CalculateMinimumTotalLag {

    public static void main(String[] args) {
        CalculateMinimumTotalLag job = new CalculateMinimumTotalLag();
        System.out.println(job.minimumTotalLag(new int[] {3,10,12}, new int[] {1,8,17}));
    }
    public int minimumTotalLag(int[] center, int[] destination) {
        PriorityQueue<Integer> centerQueue = new PriorityQueue<>();
        PriorityQueue<Integer> destinationQueue = new PriorityQueue<>();

        // Insert positions into Priority Queues
        for (int i = 0; i < center.length; i++) {
            centerQueue.offer(center[i]);
            destinationQueue.offer(destination[i]);
        }

        int total = 0;
        // Calculate total lag
        while (!centerQueue.isEmpty() && !destinationQueue.isEmpty()) {
            int centerPos = centerQueue.poll();
            int destinationPos = destinationQueue.poll();
            total += Math.abs(destinationPos - centerPos);
        }

        return total;
    }
}
