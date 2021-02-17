package sort;

import utils.JsonUtils;

/**
 * @author dinglingfeng
 * @version 2021/2/17
 * @since JDK1.7
 */
public class MergerSort {
    public static void main(String[] args) {
        final MergerSort sort = new MergerSort();
        int[] nums = {3, 6, 1, 8, 7, 9, 12, 45, 2, 4};
        final int[] sortNums = sort.sort(nums);
        System.out.println(JsonUtils.toJsonString(sortNums));
    }

    public int[] sort(int[] nums) {
        mergerSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergerSort(int[] nums, int low, int heigh) {
        int point = low + (heigh - low) / 2;
        if (low == point) {
            if (nums[low] > nums[heigh]) {
                int tmp = nums[heigh];
                nums[heigh] = nums[low];
                nums[low] = tmp;
            }
            return;
        }
        mergerSort(nums, low, point);
        mergerSort(nums, point + 1, heigh);
        mergerArray(nums, low, point, point + 1, heigh);
    }

    private void mergerArray(int[] nums, int low, int lowEnd, int heighStart, int heigh) {
        int ls = low, hs = heighStart;
        int[] tmp = new int[heigh - low + 1];
        for (int i = 0; i < tmp.length; i++) {
            if (ls <= lowEnd && hs <= heigh) {
                if (nums[ls] < nums[hs]) {
                    tmp[i] = nums[ls++];
                } else {
                    tmp[i] = nums[hs++];
                }
            } else if (ls > lowEnd) {
                tmp[i] = nums[hs++];
            } else if(hs > heigh){
                tmp[i] = nums[ls++];
            }
        }
        for (int i = 0; i < tmp.length; i++) {
            nums[low++] = tmp[i];
        }
    }
}
