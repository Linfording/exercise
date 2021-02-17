package sort;

/**
 * //给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * // 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * // 示例 1：
 * // 输入：nums1 = [1,3], nums2 = [2]
 * //输出：2.00000
 * //解释：合并数组 = [1,2,3] ，中位数 2
 * // 示例 2：
 * // 输入：nums1 = [1,2], nums2 = [3,4]
 * //输出：2.50000
 * //解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * // 示例 3：
 * // 输入：nums1 = [0,0], nums2 = [0,0]
 * //输出：0.00000
 * // 示例 4：
 * // 输入：nums1 = [], nums2 = [1]
 * //输出：1.00000
 * // 示例 5：
 * // 输入：nums1 = [2], nums2 = []
 * //输出：2.00000
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        System.out.println(solution.findMedianSortedArrays(nums1,nums2));;
    }

    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if(nums1.length ==0 && nums2.length == 0) return 0d;
            int os = nums1.length > 0 ? 0 : -1;
            int ts = nums2.length > 0 ? 0 : -1;
            final int oe = nums1.length - 1;
            final int te = nums2.length - 1;
            int[] tmp = new int[(nums1.length + nums2.length) / 2 + 1];
            for (int i = 0; i < tmp.length; i++) {
                if (os == -1) {
                    tmp[i] = nums2[ts++];
                } else if (ts == -1) {
                    tmp[i] = nums1[os++];
                } else {
                    if (os <= oe && ts <= te) {
                        if (nums1[os] < nums2[ts]) {
                            tmp[i] = nums1[os++];
                        } else {
                            tmp[i] = nums2[ts++];
                        }
                    } else if (os > oe) {
                        tmp[i] = nums2[ts++];
                    } else if (ts > te) {
                        tmp[i] = nums1[os++];
                    }
                }
            }
            if ((nums1.length + nums2.length) % 2 > 0) {
//                奇数
                return tmp[tmp.length - 1];
            } else {
                return (tmp[tmp.length - 2] + tmp[tmp.length - 1]) / 2.0;
            }
        }
    }

}
