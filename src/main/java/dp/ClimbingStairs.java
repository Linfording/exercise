package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * //假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * // 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * // 注意：给定 n 是一个正整数。
 * // 示例 1：
 * // 输入： 2
 * //输出： 2
 * //解释： 有两种方法可以爬到楼顶。
 * //1.  1 阶 + 1 阶
 * //2.  2 阶
 * // 示例 2：
 * // 输入： 3
 * //输出： 3
 * //解释： 有三种方法可以爬到楼顶。
 * //1.  1 阶 + 1 阶 + 1 阶
 * //2.  1 阶 + 2 阶
 * //3.  2 阶 + 1 阶
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new ClimbingStairs().new Solution();
        final int i = solution.climbStairs(10);
        System.out.println(i);
    }

    class Solution {
        Map<Integer, Integer> cache = new HashMap<>();

        public int climbStairs(int n) {
            if (n == 2) return 2;
            if (n == 1) return 1;
            Integer n1 = cache.get(n - 1);
            if (n1 == null) {
                n1 = climbStairs(n - 1);
                cache.put(n - 1, n1);
            }
            Integer n2 = cache.get(n - 2);
            if (n2 == null) {
                n2 = climbStairs(n - 2);
                cache.put(n - 2, n2);
            }
            return n1 + n2;
        }
    }
}
