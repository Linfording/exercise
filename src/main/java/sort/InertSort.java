package sort;

import utils.JsonUtils;

/**
 * @author dinglingfeng
 * @version 2021/2/17
 * @since JDK1.7
 */
public class InertSort {

    public static void main(String[] args) {
        final InertSort sort = new InertSort();
        int[] nums = {3, 6, 1, 8, 7, 9, 12, 45, 2, 4};
        final int[] sortNums = sort.sort(nums);
        System.out.println(JsonUtils.toJsonString(sortNums));
    }

    public int[] sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
//            [0,i) 有序； [i,length) 无序
            int value = nums[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (nums[j] > value){
                    nums[j+1] = nums[j];
                }else{
                    break;
                }
            }
            nums[j+1] = value;
        }
        return nums;
    }
}
