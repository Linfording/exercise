package stack;

import java.util.Stack;

/**
 * @author dinglingfeng
 * @version 2020/7/23
 * @since JDK1.7
 * <p>
 * 155
 * //设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * //
 * // push(x) —— 将元素 x 推入栈中。
 * // pop() —— 删除栈顶的元素。
 * // top() —— 获取栈顶元素。
 * // getMin() —— 检索栈中的最小元素。
 * //
 * // 示例:
 * //
 * // 输入：
 * //["MinStack","push","push","push","getMin","pop","top","getMin"]
 * //[[],[-2],[0],[-3],[],[],[],[]]
 * //
 * //输出：
 * //[null,null,null,null,-3,null,0,-2]
 * //
 * //解释：
 * //MinStack minStack = new MinStack();
 * //minStack.push(-2);
 * //minStack.push(0);
 * //minStack.push(-3);
 * //minStack.getMin();   --> 返回 -3.
 * //minStack.pop();
 * //minStack.top();      --> 返回 0.
 * //minStack.getMin();   --> 返回 -2.
 */
public class MinStackCode {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        System.out.println(minStack.getMin());
//        minStack.pop();
//        System.out.println(minStack.top());
//        System.out.println(minStack.getMin());

//        minStack.push(2);
//        minStack.push(0);
//        minStack.push(3);
//        minStack.push(0);
//        System.out.println(minStack.getMin());
//        minStack.pop();
//        System.out.println(minStack.getMin());
//        minStack.pop();
//        System.out.println(minStack.getMin());
//        minStack.pop();
//        System.out.println(minStack.getMin());

        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        minStack.push(2147483647);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.push(-2147483648);
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /* 双链栈*/
    /*static class MinStack {
        ListSortNode minNode;//最小值链表
        ListNode stackHead;//栈链表

        class ListNode {
            int var;
            ListNode next;

            public ListNode() {
            }

            public ListNode(int var) {

                this.var = var;
            }
        }

        class ListSortNode {
            int var;
            int count;
            ListSortNode next;

            public ListSortNode() {
            }

            public ListSortNode(int var) {
                this.var = var;
                count = 1;
            }
        }

        public MinStack() {
            stackHead = new ListNode();
            minNode = new ListSortNode();
        }

        public void push(int x) {
            ListNode newNode = new ListNode(x);
            ListSortNode newSortNode = new ListSortNode(x);
            if (stackHead.next == null) {
                stackHead.next = newNode;
                minNode.next = newSortNode;
            } else {
                newNode.next = stackHead.next;
                stackHead.next = newNode;
                ListSortNode p = minNode;
                while (p.next != null) {
                    if (p.next.var > x) {
                        //                        p.next比新增节点大，新增节点放在p.next节点之前
                        newSortNode.next = p.next;
                        p.next = newSortNode;
                        break;
                    } else if (p.next.var == x) {
                        //                        p.next和新增节点值一样，p.next.count++;
                        p.next.count++;
                        break;
                    } else {
                        //                        下一个节点比新增节点小，继续遍历
                        p = p.next;
                    }
                }
                if (p.next == null) {
                    //                    遍历到最后一个都还没有找到newNode的位置，newNode就放在最后一位
                    p.next = newSortNode;
                }
            }
        }

        public void pop() {
            //            出栈
            if (stackHead.next != null) {
                //            不为空栈才出栈  1.刷新最小值链表数据，2.出栈栈顶结点
                minDataFlash(stackHead.next.var);
                stackHead.next = stackHead.next.next;
            }
        }

        public int top() {
            //            返回栈顶结点值
            return stackHead.next == null ? null : stackHead.next.var;
        }

        private void minDataFlash(int x) {
            ListSortNode p = minNode;
            while (p.next != null) {
                if (p.next.var < x) {
                    //                    下个结点值比删除结点值小，继续遍历
                    p = p.next;
                } else if (p.next.var == x) {
                    //                    需要删除的结点，1.count > 1 --,  count ==1 删除结点
                    if (p.next.count > 1) {
                        p.next.count--;
                    } else if (p.next.count == 1) {
                        p.next = p.next.next;
                    }
                    break;
                }
            }
        }

        public int getMin() {
            return minNode.next == null ? null : minNode.next.var;
        }
    }*/

//    栈+最小值变量
    /*static class MinStack {
        ListNode stackHead;//栈链表
        Integer minVal;

        class ListNode {
            int val;
            ListNode next;

            public ListNode() {
            }

            public ListNode(int val) {
                this.val = val;
            }
        }

        public MinStack() {
            stackHead = new ListNode();
        }

        public void push(int x) {
            ListNode newNode = new ListNode(x);
            if (stackHead.next == null) {
                stackHead.next = newNode;
                minVal = x;
            } else {
                newNode.next = stackHead.next;
                stackHead.next = newNode;
                if (x < minVal) {
                    minVal = x;
                }
            }
        }

        public void pop() {
            if (stackHead.next != null) {
//                有值则出栈
                final ListNode popNode = stackHead.next;
                stackHead.next = stackHead.next.next;
                if (popNode.val == minVal) {
//                    如果是最小值被出栈刷，则刷新最小值
                    flashMinVal();
                }
            }
        }

        private void flashMinVal() {
            ListNode p = stackHead.next;
            minVal = p == null ? null : p.val;
            while (p != null && p.next != null) {
                if (minVal.intValue() < p.next.val) {
//                    min比下一个小，继续遍历
                    p = p.next;
                } else {
//                    min比下一个大，把下一个结点的赋值到min
                    minVal = p.next.val;
                    p = p.next;
                }
            }
        }

        public int top() {
            return stackHead.next == null ? null : stackHead.next.val;
        }

        public int getMin() {
            return minVal;
        }
    }*/

    /*更优双栈*/
    /*static class MinStack {
        ListNode minNode;//最小值链表
        ListNode stackHead;//栈链表

        class ListNode {
            int var;
            ListNode next;

            public ListNode() {
            }

            public ListNode(int var) {

                this.var = var;
            }
        }


        public MinStack() {
            stackHead = new ListNode();
            minNode = new ListNode();
        }

        public void push(int x) {
            ListNode newNode = new ListNode(x);
            if (stackHead.next == null) {
                stackHead.next = newNode;
                minNode.next = newNode;
            } else {
//                进栈
                newNode.next = stackHead.next;
                stackHead.next = newNode;
//                判断是否是最小的,是则进栈X，不是则再进栈一次最小值
                ListNode newMinNode;
                if(minNode.next.var <= x){
                    newMinNode = new ListNode(minNode.next.var);
                }else{
                    newMinNode = new ListNode(x);
                }
                newMinNode.next = minNode.next;
                minNode.next = newMinNode;
            }
        }

        public void pop() {
            //            出栈
            if (stackHead.next != null) {
                //            不为空栈才出栈
                stackHead.next = stackHead.next.next;
                minNode.next = minNode.next.next;
            }
        }

        public int top() {
//            返回栈顶结点值
            return stackHead.next == null ? null : stackHead.next.var;
        }

        public int getMin() {
            return minNode.next == null ? null : minNode.next.var;
        }
    }*/

    //java栈练习--性能慢
    static class MinStack {
        Stack<Integer> dataStack;
        Stack<Integer> minStack;

        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            if(minStack.isEmpty()){
                minStack.push(x);
            }else{
                if(minStack.get(minStack.size()-1).intValue() <=x ){
                    minStack.push(minStack.get(minStack.size()-1));
                }else{
                    minStack.push(x);
                }
            }
                dataStack.push(x);
        }

        public void pop() {
            if (!dataStack.isEmpty()) {
                dataStack.pop();
                minStack.pop();
            }
        }

        public int top() {
            return dataStack.isEmpty() ? Integer.parseInt(null) : dataStack.get(minStack.size()-1);
        }

        public int getMin() {
            return minStack.isEmpty() ? Integer.parseInt(null) : minStack.get(minStack.size()-1);
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    //leetcode submit region end(Prohibit modification and deletion)
}
