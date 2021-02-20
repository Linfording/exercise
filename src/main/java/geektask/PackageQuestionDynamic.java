package geektask;

/**
 * @author dinglingfeng
 * @version 2021/2/15
 * @since JDK1.7
 * 我们有一个背包，背包总的承载重量是 Wkg。
 * 现在我们有 n 个物品，每个物品的重量不等，并且不可分割。
 * 我们现在期望选择几件物品，装载到背包中。
 * 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
 * 动态规划解包裹
 */
public class PackageQuestionDynamic {

    public static void main(String[] args) {
        // 物品的重量
        int[] items = {2, 2, 4, 6, 3};
        int totalWeigh = 9;
        final PackageQuestionDynamic packageQuestionDynamic = new PackageQuestionDynamic();
        final int knapsack = packageQuestionDynamic.knapsack(items, items.length, totalWeigh);
        System.out.println(knapsack);
    }

    /**
     * @return 不超重量的情况下的最多重量
     * @items: 商品重量
     * @n:物品个数
     * @w:背包总重量
     */
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] state = new boolean[n][w + 1];
        state[0][0] = true;
        if (weight[0] < w) {
            state[0][weight[0]] = true;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                if (state[i - 1][j] == true) state[i][j] = state[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; j++) {
                if (state[i - 1][j] == true) state[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; i--) {
            if (state[n - 1][i] == true) return i;
        }
        return 0;
    }


}
