package linktable;

/**
 * @author dinglingfeng
 * @version 2020/7/21
 * @since JDK1.7
 * <p>
 * leetcode 19
 * //给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * //
 * // 示例：
 * //
 * // 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * //
 * //当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * //
 * //
 * // 说明：
 * //
 * // 给定的 n 保证是有效的。
 */
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /* 双指针解法
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if(head.next == null){
                return  null;
            }
            ListNode first = new ListNode(-1);
            first.next = head;
            ListNode p1 = first;
            ListNode p2 = first;
            for (int i = 0; i < n; i++) {
                p1 = p1.next;
            }
            while (p1.next != null) {
                p1 = p1.next;
                p2 = p2.next;
            }
            p2.next = p2.next.next;
            return first.next;
        }
    }*/
//    递归解法
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            return removeNode(head, n) == n ? head.next : head;
        }

        public int removeNode(ListNode head, int n) {
            if(head.next == null) {
                return 1;
            }
            int m = removeNode(head.next,n);
            if(m == n){
                head.next = head.next.next;
            }
            return m+1;
        }
    }


    //leetcode submit region end(Prohibit modification and deletion)
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
