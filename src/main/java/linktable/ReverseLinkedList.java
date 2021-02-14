package linktable;

/**
 * @author dinglingfeng
 * @version 2020/7/20
 * @since JDK1.7
 *
 * LeetCode 206
 * //反转一个单链表。
 * //
 * // 示例:
 * //
 * // 输入: 1->2->3->4->5->NULL
 * //输出: 5->4->3->2->1->NULL
 * //
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList().new Solution();
        ListNode head = new ListNode(1);
        ListNode cur = head;
        int length = 8;
        for (int i = 2; i <= length; i++) {
            ListNode node = new ListNode(i);
            cur.next = node;
            cur = node;
        }
        ListNode result = solution.reverseList(head);
        ListNode soutCur = result;
        for (int i = 0; i < length; i++) {
            System.out.println(soutCur.val);
            soutCur = soutCur.next;
        }
    }

//leetcode submit region begin(Prohibit modification and deletion)

//   mine 递归
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    /*class Solution {
        ListNode firstNode;

        public ListNode reverseList(ListNode head) {
            final ListNode listNode = iterableList(head);
            listNode.next = null;
            return firstNode;
        }

        public ListNode iterableList(ListNode head) {
            if (head.next != null) {
                ListNode listNode = iterableList(head.next);
                listNode.next = head;
                return head;
            } else {
                firstNode = head;
                return head;
            }
        }
    }*/
//       网上 迭代解法
/*    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            while(cur != null){
                ListNode temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            return pre;
        }
    }*/
//        inter 递归
   class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode p = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return p;
        }
    }


    //leetcode submit region end(Prohibit modification and deletion)
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

//    思路：
//    1.迭代，双指针，n和n-1，将n指向n-1一直遍历到结尾
//    2.递归，递归寻到最深节点n，将N返回，
//    每一层迭代是n-1,n-2,n-3....1,将每层的指针反指（n-1.next(n).next -> n-1)
}
