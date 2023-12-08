package test;

import java.util.*;

public class PriorityProcessesExecution {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(6, 6, 6, 1, 2, 2);
        List<Integer> result = getPrioritiesAfterExecution(new ArrayList<>(list));
        System.out.println(result);

        List<Integer> list2 = Arrays.asList(6, 3, 6, 6, 1, 2, 2);
        List<Integer> result2 = getPrioritiesAfterExecution(new ArrayList<>(list2));
        System.out.println(result2);

    }

    public static List<Integer> getPrioritiesAfterExecution(List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();

        int i = 0;
        while (i < list.size()) {
            if (map.containsKey(list.get(i))) {
                while (true) {
                    int newVal = list.get(i);
                    int reducedVal = newVal / 2;
                    list.set(map.get(newVal), Integer.MAX_VALUE);
                    map.remove(newVal);
                    list.set(i, reducedVal);
                    if (map.containsKey(reducedVal)) {
                        continue;
                    } else {
                        map.put(reducedVal, i);
                        break;
                    }
                }
            } else {
                map.put(list.get(i), i);
            }
            i = i + 1;
        }

        List<Integer> newlist = new ArrayList<>();
        for (Integer x : list) {
            if (x != Integer.MAX_VALUE) {
                newlist.add(x);
            }
        }
        return newlist;
    }

}
