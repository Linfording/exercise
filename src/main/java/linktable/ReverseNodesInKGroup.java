package linktable;

import com.sun.javafx.font.PrismFontFactory;

/**
 * //给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * // k 是一个正整数，它的值小于或等于链表的长度。
 * // 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * // 示例：
 * // 给你这个链表：1->2->3->4->5
 * // 当 k = 2 时，应当返回: 2->1->4->3->5
 * // 当 k = 3 时，应当返回: 3->2->1->4->5
 * // 说明：
 * // 你的算法只能使用常数的额外空间。
 * // 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * // Related Topics 链表
 */
public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new ReverseNodesInKGroup().new Solution();
        ListNode head = new ListNode(1);
        ListNode cur = head;
        int length = 5;
        for (int i = 2; i <= length; i++) {
            ListNode node = new ListNode(i);
            cur.next = node;
            cur = node;
        }
        ListNode result = solution.reverseKGroup(head, 2);
        ListNode soutCur = result;
        for (int i = 0; i < length; i++) {
            System.out.println(soutCur.val);
            soutCur = soutCur.next;
        }
    }

    /**
     * Definition for singly-linked list.
     */
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

    class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode p = head;
            ListNode tmpHead = head;
            ListNode sentry = new ListNode(-1,p);
            ListNode newHead = head;
            boolean isFirst = true;
            int n = k;
            while (p != null) {
                if (n > 1) {
                    n--;
                    p = p.next;
                } else {
                    if (isFirst) {
                        isFirst = false;
                        newHead = p;
                    }
                    ListNode tmpNext = p.next;
                    ListNode tmpLast = tmpHead;
                    final ListNode thisHead = reverseNode(tmpHead, p, p.next);
                    sentry.next = thisHead;
                    sentry = tmpLast;
                    tmpHead = tmpNext;
                    p = tmpNext;
                    n = k;
                }
            }
            return newHead;
        }

        private ListNode reverseNode(ListNode head, ListNode last, ListNode lastNext) {
            if (head == last) {
                return head;
            }
            ListNode p = reverseNode(head.next, last, lastNext);
            head.next.next = head;
            head.next = lastNext;
            return p;
        }
    }

}
