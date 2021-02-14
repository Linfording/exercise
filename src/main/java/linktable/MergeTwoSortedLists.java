package linktable;

/**
 * @author dinglingfeng
 * @version 2020/7/21
 * @since JDK1.7
 *
 * leetcode 21
 *
 * //将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * //
 * // 示例：
 * //
 * // 输入：1->2->4, 1->3->4
 * //输出：1->1->2->3->4->4
 * //
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeTwoSortedLists().new Solution();
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l13 = new ListNode(4);
        l11.next = l12;
        l12.next = l13;
        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(4);
        l21.next = l22;
        l22.next = l23;
        solution.mergeTwoLists(l11, l21);
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode result = new ListNode(-1);
            ListNode resultP = result;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    resultP.next = l1;
                    l1 = l1.next;
                } else {
                    resultP.next = l2;
                    l2 = l2.next;
                }
                resultP = resultP.next;
            }
            resultP.next = l1 == null ? l2 : l1;
            return result.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
