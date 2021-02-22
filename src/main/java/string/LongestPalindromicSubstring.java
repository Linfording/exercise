package string;

import java.util.HashMap;
import java.util.Map;

/**
 * [5]最长回文子串
 * //给你一个字符串 s，找到 s 中最长的回文子串。
 * // 示例 1：
 * //
 * //输入：s = "babad"
 * //输出："bab"
 * //解释："aba" 同样是符合题意的答案。
 * // 示例 2：
 * //输入：s = "cbbd"
 * //输出："bb"
 * // 示例 3：
 * //输入：s = "a"
 * //输出："a"
 * // 示例 4：
 * //输入：s = "ac"
 * //输出："a"
 * // 提示：
 * // 1 <= s.length <= 1000
 * // s 仅由数字和英文字母（大写和/或小写）组成
 * // Related Topics 字符串 动态规划
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
//        String s1 = "cbbd";
//        System.out.println(solution.longestPalindrome(s1));
//        String s2 = "bbbb";
//        System.out.println(solution.longestPalindrome(s2));
        String s3 = "babad";
        System.out.println(solution.longestPalindrome(s3));
//        String s4 = "ac";
//        System.out.println(solution.longestPalindrome(s4));
//        String s5 = "aacabdkacaa";
//        System.out.println(solution.longestPalindrome(s5));
    }

    //输入：s = "babad"
    //输出："bab"
    /*class Solution {
        public String longestPalindrome(String s) {
            if (s.length() <= 1) {
                return s;
            }
            final char[] chars = s.toCharArray();
            int[] indexs = new int[]{0, 0};
            int length = 1;
            for (int i = 0; i < chars.length; i++) {
                for (int j = 1; j < chars.length; j++) {
                    final int cl = j - i + 1;
                    if (cl < length) {
//                        比已有回文串长度小的就别试了
                        continue;
                    }
                    boolean isPalindromic = checkPalindromicString(chars, i, j);
                    if (!isPalindromic) {
                        continue;
                    }
                    if (cl > length) {
                        indexs[0] = i;
                        indexs[1] = j;
                        length = cl;
                    }
                }
            }
            return s.substring(indexs[0],indexs[1]+1);
        }


        private boolean checkPalindromicString(char[] chars, int start, int end) {
            while (start < end) {
                if (chars[start] != chars[end]) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }*/
    /* 网上动态规划-状态转移表法*/
    class Solution {
        public String longestPalindrome(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            String ans = "";
            for (int l = 0; l < n; ++l) {
                for (int i = 0; i + l < n; ++i) {
                    int j = i + l;
                    if (l == 0) {
                        dp[i][j] = true;
                    } else if (l == 1) {
                        dp[i][j] = (s.charAt(i) == s.charAt(j));
                    } else {
                        dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                    }
                    if (dp[i][j] && l + 1 > ans.length()) {
                        ans = s.substring(i, i + l + 1);
                    }
                }
            }
            return ans;
        }
    }
}
