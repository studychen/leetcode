package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Modify some letters in the new password according to the following rule:
 * change 'a' to 'b', 'b' to 'c', and so on, with 'z' changing to 'a'.
 *
 * If, after modification, the old password is a subsequence of the new password,
 * return "YES"; otherwise, return "NO".
 */
public class OldAndNewPasswordPolicyChecker {

    public static void main(String[] args) {
        List<String> newPasswords1 = Arrays.asList("aaccbbee", "aab");
        List<String> oldPasswords1 = Arrays.asList("bdbf", "aee");
        System.out.println(checkSimilarPasswords(newPasswords1, oldPasswords1));

        List<String> newPasswords2 = Arrays.asList("aaaa", "bzz");
        List<String> oldPasswords2 = Arrays.asList("bcd", "az");
        System.out.println(checkSimilarPasswords(newPasswords2, oldPasswords2));

        List<String> newPasswords3 = Arrays.asList("baacbab","accdb","baacba");
        List<String> oldPasswords3 = Arrays.asList("abdbc","ach","abb");
        System.out.println(checkSimilarPasswords(newPasswords3, oldPasswords3));
    }

    private static char transform(char c) {
        if (c == 'z') {
            return 'a';
        }
        return (char) (c + 1);
    }

    private static boolean isSubString(String newStr, String oldStr) {
        if (oldStr.length() > newStr.length()) {
            return false;
        }

        int pOld = 0;
        int pNew = 0;
        while (pNew < newStr.length() && pOld < oldStr.length()) {
            if (newStr.charAt(pNew) == oldStr.charAt(pOld)
                    || transform(newStr.charAt(pNew)) == oldStr.charAt(pOld)) {
                pOld++;
            }
            pNew++;
        }
        return pOld == oldStr.length();
    }

    private static List<String> checkSimilarPasswords(List<String> newPasswords, List<String> oldPasswords) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < newPasswords.size(); i++) {
            if (isSubString(newPasswords.get(i), oldPasswords.get(i))) {
                ans.add("YES");
            } else {
                ans.add("NO");
            }
        }
        return ans;
    }

}
