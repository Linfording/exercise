package stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author dinglingfeng
 * @version 2020/7/24
 * @since JDK1.7
 * <p>
 * <p>
 * leetcode 844
 * //给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * //
 * // 注意：如果对空文本输入退格字符，文本继续为空。
 * //
 * // 示例 1： 输入：S = "ab#c", T = "ad#c"  输出：true
 * //解释：S 和 T 都会变成 “ac”。
 * //
 * // 示例 2： 输入：S = "ab##", T = "c#d#"  输出：true
 * //解释：S 和 T 都会变成 “”。
 * //
 * // 示例 3： 输入：S = "a##c", T = "#a#c"  输出：true
 * //解释：S 和 T 都会变成 “c”。
 * //
 * // 示例 4： 输入：S = "a#c", T = "b"    输出：false
 * //解释：S 会变成 “c”，但 T 仍然是 “b”。
 * //
 * // 提示：
 * // 1 <= S.length <= 200
 * // 1 <= T.length <= 200
 * // S 和 T 只含有小写字母以及字符 '#'。
 * //
 * // 进阶：
 * // 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
 */
public class BackspaceStringCompare {
    public static void main(String[] args) {
        Solution solution = new BackspaceStringCompare().new Solution();
        System.out.println(solution.backspaceCompare("ab#c", "ad#c") == true);
        System.out.println(solution.backspaceCompare("ab##", "c#d#") == true);
        System.out.println(solution.backspaceCompare("a##c", "#a#c") == true);
        System.out.println(solution.backspaceCompare("a#c", "b") == false);
        System.out.println(solution.backspaceCompare("", "") == true);
        System.out.println(solution.backspaceCompare("#", "s#") == true);
        System.out.println(solution.backspaceCompare("abcd", "bbcd") == false);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
//    栈
    class Solution {
        public boolean backspaceCompare(String S, String T) {
            return handlerStr(S).toString().equals(handlerStr(T).toString());
        }

        private Stack handlerStr(String str) {
            Stack tStack = new Stack();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if(c == '#'){
                    if(!tStack.isEmpty()) {
                        tStack.pop();
                    }
                }else{
                    tStack.add(c);
                }
            }
            return tStack;
        }
    }

//    双指针
   /* class Solution {
        public boolean backspaceCompare(String S, String T) {
            return handlerStr(S).equals(handlerStr(T));
        }

        private String handlerStr(String str) {
            final char[] resultChar = new char[str.length()];
            int charOffset = 0;
            for (int i = 0; i < str.length(); i++) {
                final char curChar = str.charAt(i);
                if (curChar == '#') {
                    if (charOffset != 0) {
                        charOffset--;
                    }
                } else {
                    resultChar[charOffset] = curChar;
                    charOffset++;
                }
            }
            final String resStr = String.valueOf(resultChar,0,charOffset);
            return resStr;
        }
    }*/
//leetcode submit region end(Prohibit modification and deletion)

}
