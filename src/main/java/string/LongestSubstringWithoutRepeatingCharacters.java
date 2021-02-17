package string;

import java.util.HashMap;
import java.util.Map;

/**
 * //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * // 示例 1:
 * //输入: s = "abcabcbb"
 * //输出: 3
 * //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * // 示例 2:
 * //输入: s = "bbbbb"
 * //输出: 1
 * //解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * // 示例 3:
 * //输入: s = "pwwkew"
 * //输出: 3
 * //解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * //     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * // 示例 4:
 * //输入: s = ""
 * //输出: 0
 * // 提示：
 * // 0 <= s.length <= 5 * 104
 * // s 由英文字母、数字、符号和空格组成
 * // Related Topics 哈希表 双指针 字符串 Sliding Window
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        String s = " ";
        System.out.println(solution.lengthOfLongestSubstring(s));

    }

    class Solution {
        //输入: s = "abcabcbb"
        //输出: 3
        public int lengthOfLongestSubstring(String s) {
            int i = 0, j = 0;
            final char[] chars = s.toCharArray();
            int maxLength = 0;
            Map<Character, Integer> map = new HashMap<>();
            for (int y = 0; y < s.length(); y++) {
                final Integer index = map.get(chars[y]);
                if (index == null) {
                    j++;
                } else {
                    if(maxLength < j-i){
                        maxLength = j-i;
                    }
                    for (int z = i; z <= index; z++) {
                        map.remove(chars[z]);
                    }
                    i = index+1;
                    j++;
                }
                map.put(chars[y], y);
            }
            if(maxLength < j-i){
                maxLength = j-i;
            }
            return maxLength;
        }
    }

}
