package linktable;

import base.CGlibProxyFactory;

/**
 * @author dinglingfeng
 * @version 2020/7/20
 * @since JDK1.7
 *
 *
 * 判断输入字符串是否为回文字符串
 *
 * 回文字符串：是一个正读和反读都一样的字符串。
 *
 *示例：
 * 输入 abcdcba  返回true
 * 输入 abcddcba 返回true
 * 输入 abbadef 返回false
 */
public class PalindromicString {

    public static void main(String[] args) {
        PalindromicString palindromicString = (PalindromicString) new CGlibProxyFactory().createProxyInstance(new PalindromicString());
        boolean result = palindromicString.checkString("abcdcba");
        System.out.println(result);
        boolean result2 = palindromicString.isPalindrome("abcdcba");
        System.out.println(result2);
    }

    public boolean isPalindrome(String str){
        for(int i=0;i<str.length()/2;i++){
            if(str.charAt(i)!=str.charAt(str.length()-1-i)){
                return false;
            }
        }
        return true;
    }

    boolean checkString(String str) {
//        1.构建链表
        Node first = new Node();
        Node point = first;
        for (int i = 0; i < str.length(); i++) {
            Node newElement = new Node(str.charAt(i));
//            point指向节点的后继指向新节点, 新节点的指针指向point节点
            point.next = newElement;
            newElement.pre = point;
//            移动point到新节点上
            point = newElement;
        }
//        2.快慢指针找中位
        Node[] elements = findMiddleNode(first);
        Node pre = null;
        Node next = null;
        if (elements.length == 1) {
//            奇数中位，从中位开始向两边遍历
            pre = elements[0];
            next = elements[0];
        } else {
//            偶数中位
            pre = elements[0];
            next = elements[1];
            if (pre.data!=next.data) {
//                偶数中位先判断两个偶数中位是否相等
                return false;
            }
        }
//        遍历是否两边相等
        while (next.next != null) {
            pre = pre.pre;
            next = next.next;
            if (pre.data!=next.data) {
                return false;
            }
        }
//        到尾都没有出现不相等返回true
        return true;
    }

    private Node[] findMiddleNode(Node first) {
        if (first.next == null) {
            return null;
        }
        Node fast = first;
        Node slow = first;
        while (fast.next != null) {
            if (fast.next.next != null) {
//            未遍历到尾，继续遍历
                fast = fast.next.next;
                slow = slow.next;
            } else {
//             奇数尾,slow.next 为中间节点
                slow = slow.next;
                return new Node[]{slow};
            }
        }
//        偶数尾,slow、slow.next为中间节点
        return new Node[]{slow, slow.next};
    }

    class Node {
        Node next;
        Node pre;
        char data;

        public Node(char data) {
            this.data = data;
        }

        public Node() { }
    }
}
