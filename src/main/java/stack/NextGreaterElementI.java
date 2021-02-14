package stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author dinglingfeng
 * @version 2020/7/27
 * @since JDK1.7
 * <p>
 * leetcode 496
 * //给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个
 * //比其大的值。
 * // nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * //
 * // 示例 1:  输入: nums1 = [4,1,2], nums2 = [1,3,4,2].  输出: [-1,3,-1]
 * //解释:
 * //    对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 * //    对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 * //    对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 * //
 * // 示例 2:  输入: nums1 = [2,4], nums2 = [1,2,3,4].  输出: [3,-1]
 * //解释:
 * //    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 * //    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * //
 * // 提示：
 * // nums1和nums2中所有元素是唯一的。
 * // nums1和nums2 的数组大小都不超过1000。
 */
public class NextGreaterElementI {
    public static void main(String[] args) {
        Solution solution = new NextGreaterElementI().new Solution();
        soutIntArray(solution.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}));
        soutIntArray(solution.nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4}));
        soutIntArray(solution.nextGreaterElement(new int[]{2}, new int[]{2}));
        soutIntArray(solution.nextGreaterElement(new int[]{1, 3, 5, 2, 4}, new int[]{6, 5, 4, 3, 2, 1, 7}));
    }

    private static void soutIntArray(int[] nextGreaterElement) {
        for (int i : nextGreaterElement) {
            System.out.print(i + ",");
        }
        System.out.println();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            if (nums1.length == 0 || nums2.length == 0) {
                return new int[]{};
            }
            HashMap<Integer, Integer> map = new HashMap();
            Stack<Integer> stack = new Stack();
            stack.push(nums2[0]);
            for (int i = 1; i < nums2.length; i++) {
                final int curData = nums2[i];
                if (curData < stack.peek().intValue()) {
//                    当前小于栈内元素，直接入栈
                    stack.push(curData);
                } else {
//                    当前大于栈内元素，出栈且入map
                    do {
                        map.put(stack.pop(), curData);
                    } while (!stack.isEmpty() && curData > stack.peek());
                    stack.push(curData);
                }
            }
            while (!stack.isEmpty()) {
                map.put(stack.pop(), -1);
            }
            int[] result = new int[nums1.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = map.get(nums1[i]);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
