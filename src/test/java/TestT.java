/**
 * @author dinglingfeng
 * @version 2021/2/17
 * @since JDK1.7
 */
public class TestT {
    public static void main(String[] args) {
//        int[] items = {2, 2, 4, 6, 3};// 物品的重量
//        int[] values = {3, 4, 8, 9, 6}; // 物品的价值
        int[] items = {2, 2};// 物品的重量
        int[] values = {3, 4}; // 物品的价值
        final int knapsack = new TestT().knapsack(items, values, 2, 9);
        System.out.println(knapsack);
    }


    //    weight:物品重量,n:物品个数,w:背包可承载重量
    public int knapsack(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w + 1]; // 默认值false
        for (int i = 0; i < n; ++i) { // 初始化states
            for (int j = 0; j < w+1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;  // 第一行的数据要特殊处理,可以利用哨兵优化
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第i个物品放入背包
                if (states[i - 1][j] >= 0) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) {//把第i个物品放入背包
//                if (states[i - 1][j] > 0) states[i][j + weight[i]] = states[i - 1][j] + value[i];
                if (states[i-1][j] >= 0) {
                    int v = states[i-1][j] + value[i];
                    if (v > states[i][j+weight[i]]) {
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n - 1][i] > 0) return states[n - 1][i];
        }
        return 0;
    }


    public static int knapsack3(int[] weight, int[] value, int n, int w) {
        int[][] states = new int[n][w+1];
        for (int i = 0; i < n; ++i) { // 初始化states
            for (int j = 0; j < w+1; ++j) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        if (weight[0] <= w) {
            states[0][weight[0]] = value[0];
        }
        for (int i = 1; i < n; ++i) { //动态规划，状态转移
            for (int j = 0; j <= w; ++j) { // 不选择第i个物品
                if (states[i-1][j] >= 0) states[i][j] = states[i-1][j];
            }
            for (int j = 0; j <= w-weight[i]; ++j) { // 选择第i个物品
                if (states[i-1][j] >= 0) {
                    int v = states[i-1][j] + value[i];
                    if (v > states[i][j+weight[i]]) {
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j) {
            if (states[n-1][j] > maxvalue) maxvalue = states[n-1][j];
        }
        return maxvalue;
    }
}
