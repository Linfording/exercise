package dp;

import javax.annotation.Resource;

/**
 * //给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * // 示例 1：
 * //输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * //输出：6
 * //解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 * // 示例 2：
 * //输入：nums = [1]
 * //输出：1
 * // 提示：
 * // 1 <= nums.length <= 3 * 104
 * // -105 <= nums[i] <= 105
 * // 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * // Related Topics 数组 分治算法 动态规划
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        final Solution solution = new MaximumSubarray().new Solution();
//        int[] params = {-1,-2};
        int[] params = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution.maxSubArray(params));
    }

    //输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
    //输出：6
    class Solution {
        /*      public int maxSubArray(int[] nums) {
                  int[] fnum=new int[nums.length];
                  fnum[0]=nums[0];
                  int result=fnum[0];
                  for (int i = 1; i < fnum.length; i++) {
                      fnum[i]=Math.max(fnum[i-1]+nums[i],nums[i]);
                      if (fnum[i]> result){
                          result=fnum[i];
                      }
                  }
                  return result;
              }*/
       /* public int maxSubArray(int[] nums) {
            int maxValue = Integer.MIN_VALUE;
            if (nums.length == 1) {
                return nums[0];
            }
            for (int i = 0; i < nums.length; i++) {
                int[] current = new int[i+1];
                int currentCount = 0;
                for (int j = i; j >= 0; j--) {
                    if(j == i ){
                        current[j] = nums[j];
                        currentCount = nums[j];
                    }else{
                        current[j] = nums[j];
                        currentCount = nums[j] + currentCount;
                    }
                    if(maxValue < currentCount){
                        maxValue = currentCount;
                    }
                }
            }
            return maxValue;
        }*/
        public int maxSubArray(int[] nums) {
            int pre = 0, max = nums[0];
            for (int i = 1; i < nums.length; i++) {
                pre = Math.max(pre + nums[i], nums[i]);
                max = Math.max(max, pre);
            }
            return max;
        }

    }
}
