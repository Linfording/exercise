package sort;

import utils.JsonUtils;

/**
 * @author dinglingfeng
 * @version 2021/2/17
 * @since JDK1.7
 */
public class QuickSort {

    public static void main(String[] args) {
        final QuickSort quickSort = new QuickSort();
        int[] nums = {3, 6, 1, 8, 7, 9, 12, 45, 2, 4};
        final int[] sort = quickSort.sort(nums);
        System.out.println(JsonUtils.toJsonString(sort));;
    }

    public int[] sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int s, int e) {
        if (s >= e) return;
        int q = partition(nums, s, e);
        quickSort(nums, s, q - 1);
        quickSort(nums, q + 1, e);
    }

    private int partition(int[] nums, int s, int e) {
        int pivot = nums[e];
        int i = s;
        for (int j = s; j <= e; j++) {
            if(nums[j] < pivot){
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
                i++;
            }
        }
        int tmp = nums[e];
        nums[e] = nums[i];
        nums[i] = tmp;
        return i;
    }
}
