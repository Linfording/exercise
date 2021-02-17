package hash;

import java.util.HashSet;
import java.util.Set;

/**
 * //给定两个数组，编写一个函数来计算它们的交集。
 * // 示例 1：
 * // 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * //输出：[2]
 * // 示例 2：
 * // 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * //输出：[9,4]
 * // 说明：
 * // 输出结果中的每个元素一定是唯一的。
 * // 我们可以不考虑输出结果的顺序。
 * // Related Topics 排序 哈希表 双指针 二分查找
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        Solution solution = new IntersectionOfTwoArrays().new Solution();

    }

    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> integerSet = new HashSet<>();
            for (int i = 0; i < nums1.length; i++) {
                integerSet.add(nums1[i]);
            }
            Set<Integer> integerSet2 = new HashSet<>();
            for (int i = 0; i < nums2.length; i++) {
                if (integerSet.contains(nums2[i])) {
                    integerSet2.add(nums2[i]);
                }
            }
            int[] result = new int[integerSet2.size()];
            int index = 0;
            for(Integer integer : integerSet2){
                result[index] = integer;
                index++;
            }
            return result;
        }
    }
}
