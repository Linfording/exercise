package sort;

import utils.JsonUtils;

/**
 * @author dinglingfeng
 * @version 2021/2/17
 * @since JDK1.7
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
        final SelectSort sort = new SelectSort();
        int[] nums = {3, 6, 1, 8, 7, 9, 12, 45, 2, 4};
        final int[] sortNums = sort.sort(nums);
        System.out.println(JsonUtils.toJsonString(sortNums));
    }

    public int[] sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minValue = nums[i];
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[j] < minValue){
                    minValue = nums[j];
                    minIndex = j;
                }
            }
            if(minIndex != i){
                int tmp = nums[i];
                nums[i] = minValue;
                nums[minIndex] = tmp;
            }
        }
        return nums;
    }
}
