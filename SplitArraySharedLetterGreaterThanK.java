package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and a number k.
 * The task is to make a split at some position in the string, creating a prefix and a suffix.
 * The question is to determine how many such splits result in a number of shared letters
 * between the prefix and suffix greater than k.
 */
public class SplitArraySharedLetterGreaterThanK {

    public static void main(String[] args) {
        // abbcac. It has [ab, bcac] [abbc, ac] two potential split
        SplitArraySharedLetterGreaterThanK job = new SplitArraySharedLetterGreaterThanK();
        System.out.println(job.findNumWaysToSplit("abbcac", 1)+ " expect: 2");
        System.out.println(job.findNumWaysToSplit("adbccdbada", 2)+ " expect: 4");
        System.out.println(job.findNumWaysToSplit("aabaa", 1)+ " expect: 0");
    }

    public int findNumWaysToSplit(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] cs = s.toCharArray();
        for (char c: cs) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        Set<Character> set = new HashSet<>();
        int i = 0;
        int shared = 0;
        int ans = 0;
        while (i < cs.length) {
            if (!set.contains(cs[i])) {
                set.add(cs[i]);
                shared++;
            }

            map.put(cs[i], map.get(cs[i]) - 1);
            if (map.get(cs[i]) == 0) {
                shared--;
            }

            if (shared > k) {
                ans++;
            }
            i++;
        }
        return ans;
    }
}

