package test;

import java.util.HashMap;
import java.util.Map;

/**
 * The problem involves multiple processes executed from left to right.
 * Upon the completion of a process, other processes with the same original execution time
 * will have their execution times reduced to ceil(time/2).
 * Processes that become the same after the ceil operation is not considered cohesive.
 * The task is to determine the total time required for the execution of all processes.
 * Example, input: [5, 5, 3, 6, 5, 3]
 * time 5, [3,3,6,3,3]
 * time 5+3, [3,6,2,3]
 * time 5+3+3, [6,2,2]
 * time 5+3+3+6, [2,2]
 * time 5+3+3+6+2, [2]
 * time 5+3+3+6+2+2 = 21, []
 */
public class ProcessCohesiveCeilProcessesTime {

    public static void main(String[] args) {
        ProcessCohesiveCeilProcessesTime job = new ProcessCohesiveCeilProcessesTime();
        System.out.println(job.handle(new int[]{5, 5, 3, 6, 5, 3}) + " expect: " + 21);
        System.out.println(job.handle(new int[]{4, 3, 3, 3}) + " expect: " + 10);
        System.out.println(job.handle(new int[]{5, 8, 4, 4, 8, 2}) + " expect: " + 25);
    }

    int handle(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int num : nums) {
            if (map.containsKey(num)) {
                int temp = (int) Math.ceil(map.get(num) / 2.0);
                map.put(num, temp);
                sum += temp;
            } else {
                map.put(num, num);
                sum += num;
            }
        }

        return sum;
    }
}
