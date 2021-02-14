package stack;

import java.util.Stack;

/**
 * @author dinglingfeng
 * @version 2020/7/27
 * @since JDK1.7
 * <p>
 * leetcode 682
 * //你现在是棒球比赛记录员。
 * //给定一个字符串列表，每个字符串可以是以下四种类型之一：
 * //1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
 * //2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
 * //3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
 * //4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
 * //
 * //每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。
 * //你需要返回你在所有回合中得分的总和。
 * //
 * // 示例 1:  输入: ["5","2","C","D","+"]  输出: 30
 * //解释:
 * //第1轮：你可以得到5分。总和是：5。
 * //第2轮：你可以得到2分。总和是：7。
 * //操作1：第2轮的数据无效。总和是：5。
 * //第3轮：你可以得到10分（第2轮的数据已被删除）。总数是：15。
 * //第4轮：你可以得到5 + 10 = 15分。总数是：30。
 * //
 * // 示例 2:  输入: ["5","-2","4","C","D","9","+","+"]  输出: 27
 * //解释:
 * //第1轮：你可以得到5分。总和是：5。
 * //第2轮：你可以得到-2分。总数是：3。
 * //第3轮：你可以得到4分。总和是：7。
 * //操作1：第3轮的数据无效。总数是：3。
 * //第4轮：你可以得到-4分（第三轮的数据已被删除）。总和是：-1。
 * //第5轮：你可以得到9分。总数是：8。
 * //第6轮：你可以得到-4 + 9 = 5分。总数是13。
 * //第7轮：你可以得到9 + 5 = 14分。总数是27。
 * //
 * // 注意：
 * //
 * // 输入列表的大小将介于1和1000之间。
 * // 列表中的每个整数都将介于-30000和30000之间。
 */
public class BaseballGame {
    public static void main(String[] args) {
        Solution solution = new BaseballGame().new Solution();
        System.out.println(solution.calPoints(new String[]{"5", "2", "C", "D", "+"}));
        System.out.println(solution.calPoints(new String[]{"5", "-2", "4", "C", "D", "9", "+", "+"}));
        System.out.println(solution.calPoints(new String[]{"5"}));
        System.out.println(solution.calPoints(new String[]{"C"}));
        System.out.println(solution.calPoints(new String[]{"D"}));
        System.out.println(solution.calPoints(new String[]{"+"}));
        System.out.println(solution.calPoints(new String[]{"+", "+", "-1"}));
        System.out.println(solution.calPoints(new String[]{}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
//    自实现栈
/*
    class Solution {
        public int calPoints(String[] ops) {
            LinkStack linkStack = new LinkStack();
            for (int i = 0; i < ops.length; i++) {
                final String curSource = ops[i];
                if (curSource.equals("+")) {
                    //2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
                    final Node topNode = linkStack.top();
                    Integer source=0;
                    if(linkStack.size > 1){
//                        有两个以上节点
                        source = topNode.pre.data+topNode.data;
                    }else if(linkStack.size == 1){
//                        只有一个节点
                        source = topNode.data;
                    }
                    linkStack.push(source);
                } else if (curSource.equals("C")) {
                    // 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
                    linkStack.pop();
                } else if (curSource.equals("D")) {
                    // 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
                    final Node topNode = linkStack.top();
                    Integer source = topNode == null ? 0 : topNode.data*2;
                    linkStack.push(source);
                } else {
//                     整数，直接计分
                    linkStack.push(Integer.valueOf(curSource));
                }
            }
            int sum=0;
            while(linkStack.top() != null){
                sum += linkStack.pop().data;
            }
            return sum;
        }

        class LinkStack {
            Node head;
            Node last;
            int size;

            public LinkStack() {
                size = 0;
                head = new Node();
                last = head;
            }

            public void push(Integer data) {
                final Node newNode = new Node(data);
                last.next = newNode;
                newNode.pre = last;
                last = newNode;
                size++;
            }

            public Node pop() {
                Node result = null;
                if (size > 0) {
//                    有节点，需要出栈node
                    result = last;
                    last = last.pre;
                    last.next = null;
                    size--;
                }
                return result;
            }

            public Node top() {
//                只返回栈顶元素，并不出栈
                return size == 0 ? null : last;
            }

            public int size() {
                return size;
            }
        }

        class Node {
            Node next;
            Node pre;
            Integer data;

            public Node(Integer data) {
                this.data = data;
            }

            public Node() {
            }
        }
    }
*/
    class Solution {
        public int calPoints(String[] ops) {
            LinkStack linkStack = new LinkStack();
            for (int i = 0; i < ops.length; i++) {
                final String curSource = ops[i];
                if (curSource.equals("+")) {
                    //2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
                    final Node topNode = linkStack.top();
                    Integer source=0;
                    if(linkStack.size > 1){
//                        有两个以上节点
                        source = topNode.next.data+topNode.data;
                    }else if(linkStack.size == 1){
//                        只有一个节点
                        source = topNode.data;
                    }
                    linkStack.push(source);
                } else if (curSource.equals("C")) {
                    // 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
                    linkStack.pop();
                } else if (curSource.equals("D")) {
                    // 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
                    final Node topNode = linkStack.top();
                    Integer source = topNode == null ? 0 : topNode.data*2;
                    linkStack.push(source);
                } else {
//                     整数，直接计分
                    linkStack.push(Integer.valueOf(curSource));
                }
            }
            int sum=0;
            while(linkStack.top() != null){
                sum += linkStack.pop().data;
            }
            return sum;
        }

        class LinkStack {
            Node head;
            int size;

            public LinkStack() {
                size = 0;
                head = new Node();
            }

            public void push(Integer data) {
                final Node newNode = new Node(data);
                newNode.next = head.next;
                head.next = newNode;
                size++;
            }

            public Node pop() {
                Node result = null;
                if (size > 0) {
//                    有节点，需要出栈node
                    result = head.next;
                    head.next = head.next.next;
                    size--;
                }
                return result;
            }

            public Node top() {
//                只返回栈顶元素，并不出栈
                return size == 0 ? null : head.next;
            }

            public int size() {
                return size;
            }
        }

        class Node {
            Node next;
            Integer data;

            public Node(Integer data) {
                this.data = data;
            }

            public Node() {
            }
        }
    }

   /* class Solution {
        public int calPoints(String[] ops) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < ops.length; i++) {
                final String curSource = ops[i];
                if (curSource.equals("+")) {
                    //2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
                    stack.push(stack.peek()+stack.get(stack.size()-2));
                } else if (curSource.equals("C")) {
                    // 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
                    stack.pop();
                } else if (curSource.equals("D")) {
                    // 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
                    stack.push(stack.peek() * 2);
                } else {
//                     整数，直接计分
                    stack.push(Integer.valueOf(curSource));
                }
            }
            int sum = 0;
            while (stack.size() != 0) {
                sum += stack.pop();
            }
            return sum;
        }
    }*/

//leetcode submit region end(Prohibit modification and deletion)

}
