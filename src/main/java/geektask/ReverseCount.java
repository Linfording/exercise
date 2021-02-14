package geektask;

import base.MethodRockonTime;

/**
 * @author dinglingfeng
 * @version 2021/2/14
 * @since JDK1.7
 */
public class ReverseCount extends MethodRockonTime {
    //    假设我们有 n 个数据，我们期望数据从小到大排列，
//    那完全有序的数据的有序度就是 n(n-1)/2，逆序度等于 0；
//    相反，倒序排列的数据的有序度就是 0，逆序度是 n(n-1)/2。
//    除了这两种极端情况外，我们通过计算有序对或者逆序对的个数，
//    来表示数据的有序度或逆序度。
//    我现在的问题是，如何编程求出一组数据的有序对个数或者逆序对个数呢？
//    例如：2,4,3,1,5,6 逆序对数： 4
//    （2,1) (4,3) (4,1) (3,1)
    @Override
    public void coreMethod() {
        int[] array = {2, 4, 3, 1, 5, 6};
        int reverseGroup = countReverseGroup(array);
        System.out.println(reverseGroup);
    }

    int reverseGroup = 0;

    private int countReverseGroup(int[] array) {
        mySort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            if(i != array.length -1) {
                System.out.print(array[i]);
            }else{
                System.out.println(array[i]);
            }
        }
        return reverseGroup;
    }

    private void mySort(int[] array, int low, int heigh) {
        if (low >= heigh) {
            return;
        }
        int midIndex = low + (heigh - low) / 2;
        mySort(array, low, midIndex);
        mySort(array, midIndex + 1, heigh);
        merge(array, low, midIndex, midIndex + 1, heigh);
    }

    private void merge(int[] array, int low, int mid, int midNext, int high) {
        final int[] tmpArray = new int[array.length];
        int tmpIndex = 0;
        int i = low, j = midNext;
        while (i <= mid && j <= high) {
            if (array[i] <= array[j]) {
                tmpArray[tmpIndex++] = array[i++];
            } else {
                reverseGroup += mid - i + 1;
                tmpArray[tmpIndex++] = array[j++];
            }
        }

        int start = i, end = mid;
        if (j <= high) {
            start = j;
            end = high;
        }

        while (start <= end) {
            tmpArray[tmpIndex++] = array[start++];
        }

        for (int z = 0; z <= high - low; z++) {
            array[low + z] = tmpArray[z];
        }
    }

    public static void main(String[] args) {
        new ReverseCount().run();
    }
}
