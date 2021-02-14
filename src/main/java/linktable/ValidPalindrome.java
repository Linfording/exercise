package linktable;

/**
 * @author dinglingfeng
 * @version 2020/7/21
 * @since JDK1.7
 * 125
//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
//
// 说明：本题中，我们将空字符串定义为有效的回文串。
//
// 示例 1:
// 输入: "A man, a plan, a canal: Panama"
//输出: true
//
// 示例 2:
// 输入: "race a car"
//输出: false
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        Solution solution = new ValidPalindrome().new Solution();
        String s = "ab";
        String s2 = "";
        String s3 = "0P";
        String s4 = "abc";
        String s5 = "race a car";
        String s6 = "A man, a plan, a canal: Panama";
        String s7 = "  ";
        String s8 = ".";
//        amanaplana c analpanama
        System.out.println(String.format("%s,%s",s,solution.isPalindrome(s)==false));
        System.out.println(String.format("%s,%s",s2,solution.isPalindrome(s2)==true));
        System.out.println(String.format("%s,%s",s3,solution.isPalindrome(s3)==false));
        System.out.println(String.format("%s,%s",s4,solution.isPalindrome(s4)==false));
        System.out.println(String.format("%s,%s",s5,solution.isPalindrome(s5)==false));
        System.out.println(String.format("%s,%s",s6,solution.isPalindrome(s6)==true));
        System.out.println(String.format("%s,%s",s7,solution.isPalindrome(s7)==true));
        System.out.println(String.format("%s,%s",s8,solution.isPalindrome(s8)==true));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /* 双向链表解法    class Solution {
        public boolean isPalindrome(String s) {
            ListNode head = createLinkList(s);
            if(head == null || head.next == null){
                return true;
            }
            ListNode first = head;
            ListNode slow = head;
            while(first != null && first.next != null){
                first = first.next.next;
                slow = slow.next;
            }
            ListNode p1;
            ListNode p2;
            if(first == null){
//                偶数,slow.pre、slow为中间节点 slow.pre和slow为对称
                p1 = slow.pre;
                p2 = slow;
            }else{
//                奇数,slow为中间节点，slow.pre和slow.next节点对称
                p1 = slow;
                p2 = slow;
            }
            while(p2 != null){
                if(p1.val == p2.val){
                    p1 = p1.pre;
                    p2 = p2.next;
                }else{
                    return false;
                }
            }
//            跑完都没有被退出，说明是对称的
            return true;
        }

        public ListNode createLinkList(String str) {
            ListNode head = new ListNode('0');
            ListNode cur = head;
            for (int i = 0; i < str.length(); i++) {
                final char curVal = str.charAt(i);
                if(Character.isDigit(curVal) || ('a' <= curVal && curVal <='z')){
                    final ListNode newNode = new ListNode(curVal);
                    cur.next = newNode;
                    newNode.pre = cur;
                    cur = newNode;
                }else if('A'<=curVal && curVal <='Z'){
                    final ListNode newNode = new ListNode((char) (curVal + 32));
                    cur.next = newNode;
                    newNode.pre = cur;
                    cur = newNode;
                }
            }
            head.pre = null;
            return head.next;
        }

        class ListNode {
            char val;
            ListNode next;
            ListNode pre;

            public ListNode(char val) {
                this.val = val;
            }
        }
    }*/
    /* 转换过滤字符串，双指针 class Solution {
        public boolean isPalindrome(String s) {
            final String str = s.toLowerCase();
            char[] chars = new char[str.length()];
            int charsOffset = 0;
            for(int i = 0; i < str.length();i++){
                final char curVal = str.charAt(i);
                if(Character.isLetterOrDigit(curVal)){
                    chars[charsOffset] = curVal;
                    charsOffset++;
                }
            }
            if(charsOffset > 0){
                charsOffset--;
                for(int i =0 ;i <= charsOffset/2; i++){
                    if(chars[i]!=chars[charsOffset-i]){
                        return false;
                    }
                }
            }
            return true;
        }
    }*/
//    源字符串上双指针
    class Solution {
        public boolean isPalindrome(String s) {
            int lp = 0;
            int rp = s.length() -1;
            while(lp < rp){
                char lval = s.charAt(lp);
                while(!Character.isLetterOrDigit(lval) && lp < rp){
                    lp++;
                    lval = s.charAt(lp);
                }
                char rval = s.charAt(rp);
                while(!Character.isLetterOrDigit(rval) && rp > lp){
                    rp--;
                    rval = s.charAt(rp);
                }
                if(Character.toLowerCase(lval) != Character.toLowerCase(rval)){
                    return false;
                }
                lp++;
                rp--;
            }
            return true;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
