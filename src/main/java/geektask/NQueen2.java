package geektask;

import utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dinglingfeng
 * @version 2021/2/14
 * @since JDK1.7
 */
public class NQueen2 {

    /**
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * 示例 1：
     * 输入：n = 4
     * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * .Q..
     * ...Q
     * Q...
     * ..Q.
     * 解释：如上图所示，4 皇后问题存在两个不同的解法。
     * 示例 2：
     * 输入：n = 1
     * 输出：[["Q"]]
     * 提示：
     * 1 <= n <= 9
     * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
     */
    public static void main(String[] args) {
        Solution solution = new NQueen2().new Solution();
        System.out.println(solution.totalNQueens(5));
        System.out.println(solution.totalNQueens(4));
        System.out.println(solution.totalNQueens(2));
//        final List<List<String>> lists2 = solution.solveNQueens(2);
//        System.out.println(JsonUtils.toJsonString(lists2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int totalNQueens(int n) {
            List result = new ArrayList();
            char[][] chessBoard = new char[n][n];
            solveChess(result, chessBoard, 0);
            return result.size();
        }

        private void solveChess(List result, char[][] chessBoard, int row) {
            if (row == chessBoard.length) {
                result.add(1);
                return;
            }
            for (int col = 0; col < chessBoard.length; col++) {
//            1. 初始化
                initLine(chessBoard, row, col);
//            2. check
                if (checkLine(chessBoard, row, col)) {
//            3. 通过的话，继续下一行
                    solveChess(result, chessBoard, row + 1);
                }
            }
        }

        private boolean checkLine(char[][] chessBoard, int lineIndex, int columnIndex) {
            for (int i = 1; i <= lineIndex; i++) {
//                0. 确认校验行
                int checkLine = lineIndex - i;
//                1. 列、斜 冲不冲突
                if (chessBoard[checkLine][columnIndex] == 'Q') {
                    return false;
                }
                if (columnIndex - i >= 0 && (chessBoard[checkLine][columnIndex - i] == 'Q')) {
                    return false;
                }
                if (columnIndex + i < chessBoard.length && (chessBoard[checkLine][columnIndex + i] == 'Q')) {
                    return false;
                }
            }
            return true;
        }

        //        初始化
        private void initLine(char[][] chessBoard, int lineIndex, int columnIndex) {
            chessBoard[lineIndex][columnIndex] = 'Q';
            for (int i = 0; i < chessBoard.length; i++) {
                if (i != columnIndex) {
                    chessBoard[lineIndex][i] = '.';
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
