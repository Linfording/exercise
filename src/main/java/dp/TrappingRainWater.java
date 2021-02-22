package dp;

/**
 * //给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * // 示例 1：
 * //输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * //输出：6
 * //解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，
 * 可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * // 示例 2：
 * //输入：height = [4,2,0,3,2,5]
 * //输出：9
 * // 提示：
 * // n == height.length
 * // 0 <= n <= 3 * 104
 * // 0 <= height[i] <= 105
 * // Related Topics 栈 数组 双指针 动态规划
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new TrappingRainWater().new Solution();
        int[] height = new int[]{0,1,0,1};
        System.out.println(solution.trap(height));
        int[] height1 = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(solution.trap(height1));
        int[] height2 = new int[]{4,2,0,3,2,5};
        System.out.println(solution.trap(height2));
        int[] height3 = new int[]{4,2,0,3,2};
        System.out.println(solution.trap(height3));
    }

    class Solution {
        int leftMax = -1;
        int reightMax = -1, reightIndex = -1;

        public int trap(int[] height) {
            int length = height.length;
            if (length <= 2) {
                return 0;
            }
            int trap = 0;
            countReighMax(height, 2, height.length - 1);
            for (int i = 1; i < length - 1; i++) {
                leftMax = Math.max(leftMax, height[i - 1]);
                if (i + 1 > reightIndex) {
                    countReighMax(height, i + 1, length - 1);
                }
                trap += Math.max(Math.min(leftMax, reightMax) - height[i], 0);
            }
            return trap;
        }

        private void countReighMax(int[] height, int startIndex, int endIndex) {
            int max = -1;
            int maxIndex = -1;
            for (int i = startIndex; i <= endIndex; i++) {
                if (max < height[i]) {
                    max = height[i];
                    maxIndex = i;
                }
            }
            reightMax = max;
            reightIndex = maxIndex;
        }

    }
}
