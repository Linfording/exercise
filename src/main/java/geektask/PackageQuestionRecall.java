package geektask;

import utils.JsonUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author dinglingfeng
 * @version 2021/2/14
 * @since JDK1.7
 * <p>
 * 我们有一个背包，背包总的承载重量是 Wkg。
 * 现在我们有 n 个物品，每个物品的重量不等，并且不可分割。
 * 我们现在期望选择几件物品，装载到背包中。
 * 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
 */
public class PackageQuestionRecall {

    public static void main(String[] args) {
        int totalWeigh = 100;
        int[] items = {10, 30, 40, 80, 40, 80, 10};
        final PackageQuestionRecall packageQuestion = new PackageQuestionRecall();
        packageQuestion.f(0, 0, items, items.length, 100, new ArrayList<>());
        System.out.println(JsonUtils.toJsonString(packageQuestion.effectItemsMap.get(packageQuestion.maxW)));
        System.out.println(packageQuestion.maxW);
    }

    public Map<Integer, List<List<Integer>>> effectItemsMap = new HashMap();
    public int maxW = Integer.MIN_VALUE; //存储背包中物品总重量的最大值

    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
// w背包重量；items表示每个物品的重量；n表示物品个数
// 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
//    相当于：第一次进的时候 全部不放，然后从第n个开始放，跳回n-1的栈时，又会从n-1的放，进入n的不放和放
// f(0, 0, a, 10, 100)
    public void f(int i, int cw, int[] items, int n, int w, List<Integer> effectItems) {
//        这里要每次进来创建个新副本，否则引用传递，会递归出脏数据
        effectItems = effectItems.stream().collect(Collectors.toList());
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            if (cw >= maxW && cw != 0) {
                maxW = cw;
                List<List<Integer>> integers = effectItemsMap.get(maxW);
                if (integers == null) {
                    integers = new ArrayList<>();
                    effectItemsMap.put(maxW, integers);
                }
                integers.add(effectItems);
            }
            return;
        }
//        不放
        f(i + 1, cw, items, n, w, effectItems);
//        放
        if (cw + items[i] <= w) {
            effectItems.add(items[i]);
            f(i + 1, cw + items[i], items, n, w, effectItems);
        }
    }
}
