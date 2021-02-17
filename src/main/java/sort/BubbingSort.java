package sort;

import utils.JsonUtils;

/**
 * @author dinglingfeng
 * @version 2021/2/17
 * @since JDK1.7
 */
public class BubbingSort {
    public static void main(String[] args) {
        final BubbingSort bubbingSort = new BubbingSort();
        int[] nums = {3, 6, 1, 8, 7, 9, 12, 45, 2, 4};
        int[] nums2 = {3, 6, 1, 8, 7, 9, 12, 45, 2, 4};
        final int[] sort = bubbingSort.sort(nums);
        final int[] sort2 = bubbingSort.bubbleSort(nums2,nums2.length);
        System.out.println(JsonUtils.toJsonString(sort));
        System.out.println(JsonUtils.toJsonString(sort2));
    }

    public int[] sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j+1]) { // 交换
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        return nums;
    }

    public int[] bubbleSort(int[] a, int n) {
        if (n <= 1) return a;

        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] > a[j+1]) { // 交换
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;  // 表示有数据交换
                }
            }
            if (!flag) break;  // 没有数据交换，提前退出
        }
        return a;
    }
}
