package stack;

/**
 * @author dinglingfeng
 * @version 2020/7/22
 * @since JDK1.7
 *
 * leetcode 20
 * /给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * //
 * // 有效字符串需满足：
 * // 左括号必须用相同类型的右括号闭合。
 * // 左括号必须以正确的顺序闭合。
 * //
 * // 注意空字符串可被认为是有效字符串。
 * // 示例 1:  输入: "()"       输出: true
 * // 示例 2:  输入: "()[]{}"   输出: true
 * // 示例 3:  输入: "(]"       输出: false
 * // 示例 4:  输入: "([)]"     输出: false
 * // 示例 5:  输入: "{[]}"     输出: true
 */
public class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        System.out.println(solution.isValid(""));
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("({)"));
        System.out.println(solution.isValid("(})"));
        System.out.println(solution.isValid("([]{})"));
        System.out.println(solution.isValid("[({})]"));
        System.out.println(solution.isValid("["));
        System.out.println(solution.isValid("[["));
        System.out.println(solution.isValid("[[]"));
        System.out.println(solution.isValid("]"));
        System.out.println(solution.isValid("]]"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            if(s.trim().isEmpty()){
                return true;
            }
            char[] theses = new char[s.length()/2+2];
            int offset = 1;
            for(int i =0;i<s.length();i++){
                final char curVal = s.charAt(i);
                if(curVal == '(' || curVal == '{' || curVal =='[') {
                    theses[offset] = curVal;
                    offset++;
                }else{
                    offset--;
                    switch(curVal){
                        case ')':if(theses[offset]!='('){return false;}break;
                        case '}':if(theses[offset]!='{'){return false;}break;
                        case ']':if(theses[offset]!='['){return false;}break;
                        default:return false;
                    }
                }
            }
            return offset==1;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
