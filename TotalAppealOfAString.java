package test;

import java.util.*;

/**
 * The appeal of a string is the number of distinct characters found in the string.
 *
 * For example, the appeal of "abbca" is 3 because it has 3 distinct characters: 'a', 'b', and 'c'.
 * Given a string s, return the total appeal of all of its substrings.
 *
 * A substring is a contiguous sequence of characters within a string.
 */
public class TotalAppealOfAString {

    public static void main(String[] args) {
        System.out.println(new TotalAppealOfAString().appealSum("abbca") + " correct: 28");
        System.out.println(new TotalAppealOfAString().appealSum("code") + " correct: 20");
    }

    public long appealSum(String s) {
        long total = 0;
        long currSum = 0;
        Map<Character, Integer> map = new HashMap<>();

        char[] cs = s.toCharArray();

        for (int i = 0; i < cs.length; i++) {
            long temp = i + 1L - map.getOrDefault(cs[i], 0);
            currSum = currSum + temp;
            total = total + currSum;

            map.put(cs[i], i+1);
        }

        return total;
    }

}
