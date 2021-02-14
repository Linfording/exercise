package stack;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author dinglingfeng
 * @version 2020/7/28
 * @since JDK1.7
 * <p>
 * leetCode 224
 * //实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * // 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格 。
 * //
 * // 示例 1:   输入: "1 + 1"  输出: 2
 * // 示例 2:   输入: " 2-1 + 2 "  输出: 3
 * // 示例 3:   输入: "(1+(4+5+2)-3)+(6+8)"  输出: 24
 * // 说明：
 * // 你可以假设所给定的表达式都是有效的。
 * // 请不要使用内置的库函数 eval。
 */
public class BasicCalculator {
    public static void main(String[] args) {
        Solution solution = new BasicCalculator().new Solution();
        System.out.println("1+1=" + solution.calculate("1 + 1"));
        System.out.println(" 22-1 + 2 =" + solution.calculate(" 22-1 + 2 "));
        System.out.println("(1+(4+5+2)-3)+(6+8)=" + solution.calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println("231245=" + solution.calculate("231245"));
        System.out.println("(7)-(0)+(4)=" + solution.calculate("(7)-(0)+(4)"));
        System.out.println("(7-2)+(3+1)-(2-(4-4)+3)=" + solution.calculate("(7-2)+(3+1)-(2-(4-4)+3)"));
//  （7-2）+（3+1）-（2-（4-4）+3)
//        7-2+3+1-2-
    }
    //leetcode submit region begin(Prohibit modification and deletion)
        /*
            执行耗时:37 ms,击败了31.74% 的Java用户
			内存消耗:42.5 MB,击败了12.50% 的Java用户*/
        class Solution {
        public int calculate(String s) {
            StringBuilder stringBuilder = new StringBuilder();
            Stack<Character> mathChar = new Stack<>();
            Stack<Integer> intStack = new Stack<>();
            final char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                final char curChar = chars[i];
                if (curChar != ' ') {
                    if (Character.isDigit(curChar)) {
//                        数字
                        stringBuilder.append(curChar);
                    } else {
                        if (stringBuilder.length() > 0) {
                            pushDigit(stringBuilder, mathChar, intStack);
                        }
//                        运算字符，(都直接入栈，)需要一直匹配到(,+-需要往前判断运算符
                        if (curChar == '(') {
                            mathChar.push(curChar);
                        } else if (curChar == '-' || curChar == '+') {
                            if (mathChar.size() > 0 && (mathChar.peek() == '-' || mathChar.peek() == '+')) {
                                int num1 = intStack.pop();
                                int num2 = intStack.pop();
                                intStack.push(mathChar.pop() == '+' ? num1 + num2 : num2 - num1);
                            }
                            mathChar.push(curChar);
                        } else {
                            while (mathChar.peek() != '(') {
                                int num1 = intStack.pop();
                                int num2 = intStack.pop();
                                intStack.push(mathChar.pop() == '+' ? num1 + num2 : num2 - num1);
                            }
                            mathChar.pop();
                        }
                    }
                }
            }
            if (stringBuilder.length() > 0) {
                pushDigit(stringBuilder, mathChar, intStack);
            }
            if (!mathChar.isEmpty()) {
                int num1 = intStack.pop();
                int num2 = intStack.pop();
                intStack.push(mathChar.pop() == '+' ? num1 + num2 : num2 - num1);
            }
            return intStack.peek();
        }

        private void pushDigit(StringBuilder stringBuilder, Stack<Character> mathChar, Stack<Integer> intStack) {
            Integer curData = Integer.parseInt(stringBuilder.toString());
            if (!mathChar.isEmpty()) {
                switch (mathChar.pop()) {
//                            遇到(push，遇到+、-则直接出栈运算符，计算后推回数字栈
                    case '(':
                        intStack.push(curData);
                        mathChar.push('(');
                        break;
                    case '+':
                        intStack.push(curData + intStack.pop());
                        break;
                    case '-':
                        intStack.push(intStack.pop() - curData);
                        break;
                }
            } else {
                intStack.push(curData);
            }
            stringBuilder.delete(0, stringBuilder.length());
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
