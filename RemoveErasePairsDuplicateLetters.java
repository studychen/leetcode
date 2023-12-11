package test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given a string S. In one move you can erase from S a pair of identical letters. Find the shortest possible string that can be created this way. If there are many such strings, choose the alphabetically (lexicographically) smallest one. Note that there is no limit to the number of moves.
 *
 * Write a function:
 *
 * class Solution { public String solution(String S); }
 *
 * that, given a string S of length N, returns the shortest string (or the first alphabetically, in the case of a draw) created by erasing pairs of identical letters from S.
 *
 * Examples:
 *
 *     For S = "CBCAAXA" you can make, for example, two moves:
 *
 * first erase a pair of letters "C": "CBCAAXA" ➝ "BAAXA";
 * then erase a pair of letters "A": "BAAXA" ➝ "BAX".
 * Thus the string "BAX" is created. There is no way to create a shorter string. The other string of length 3 that can be created is "BXA", but "BAX" is the first alphabetically. The function should return "BAX".
 *
 *     For S = "ZYXZYZY", two moves can be made:
 *
 * first erase a pair of letters "Y": "ZYXZYZY" ➝ "ZXZYZ";
 * then erase a pair of letters "Z": "ZXZYZ" ➝ "XYZ".
 * The other strings of length 3 that can be created are "ZYX", "YXZ", "XZY" and "ZXY", but "XYZ" is alphabetically the first, so the function should return "XYZ".
 *
 *     For S = "ABCBACDDAA" all five pairs of identical letters can be erased. The function should return "" (empty string).
 *
 *     For S = "AKFKFMOGKFB" the function should return "AFKMOGB".
 */
public class RemoveErasePairsDuplicateLetters {

    public static void main(String[] args) {
        RemoveErasePairsDuplicateLetters j = new RemoveErasePairsDuplicateLetters();
        System.out.println(j.removeDuplicateLetters("CBCAAXA"));
        System.out.println(j.removeDuplicateLetters("ZYXZYZY"));
        System.out.println(j.removeDuplicateLetters("ABCBACDDAA"));
        System.out.println(j.removeDuplicateLetters("AKFKFMOGKFB"));

//        print(bestPairElim('CBCAAXA')) # "BAX"
//        print(bestPairElim('ZYXZYZY')) # "XYZ"
//        print(bestPairElim('ABCBACDDAA'))  # ""
//        print(bestPairElim('AKFKFMOGKFB')) # "AFKMOGB"
    }

    public String removeDuplicateLetters(String s) {
        int[] lastPos = new int[26];
        int[] cnt = new int[26];
        Arrays.fill(lastPos, -1);
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            lastPos[cs[i] - 'A'] = i;
            cnt[cs[i] - 'A']++;
        }

        boolean[] in = new boolean[26];
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (cnt[c-'A'] % 2 == 0) {
                continue;
            }

            if (!in[c-'A']) {
                while (!stack.isEmpty() && stack.peek() > c && lastPos[stack.peek()-'A'] > i) {
                    in[stack.peek()-'A'] = false;
                    stack.pop();
                }

                stack.push(c);
                in[c - 'A'] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

}
