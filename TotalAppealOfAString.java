package test;

import java.util.*;

/**
 * LC 2262. Total Appeal of A String
 * 
 * The appeal of a string is the number of distinct characters found in the string.
 *
 * For example, the appeal of "abbca" is 3 because it has 3 distinct characters: 'a', 'b', and 'c'.
 * Given a string s, return the total appeal of all of its substrings.
 *
 * A substring is a contiguous sequence of characters within a string.
 * 
 * Example 1:
 *
 * Input: s = "abbca"
 * Output: 28
 * Explanation: The following are the substrings of "abbca":
 * - Substrings of length 1: "a", "b", "b", "c", "a" have an appeal of 1, 1, 1, 1, and 1 respectively. The sum is 5.
 * - Substrings of length 2: "ab", "bb", "bc", "ca" have an appeal of 2, 1, 2, and 2 respectively. The sum is 7.
 * - Substrings of length 3: "abb", "bbc", "bca" have an appeal of 2, 2, and 3 respectively. The sum is 7.
 * - Substrings of length 4: "abbc", "bbca" have an appeal of 3 and 3 respectively. The sum is 6.
 * - Substrings of length 5: "abbca" has an appeal of 3. The sum is 3.
 * The total sum is 5 + 7 + 7 + 6 + 3 = 28.
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
