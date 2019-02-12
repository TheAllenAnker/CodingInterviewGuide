package com.allen_anker.chapter4_recursion_and_dp;

public class MinPathInMatrix {
    public static int minPathSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        int rows = matrix.length, cols = matrix[0].length;
        int more = rows > cols ? rows : cols;
        int less = rows > cols ? cols : rows;
        int[] res = new int[less];
        boolean rowsMore = more == rows;
        res[0] = matrix[0][0];
        for (int i = 1; i < less; i++) {
            res[i] = res[i - 1] + (rowsMore ? matrix[0][i] : matrix[i][0]);
        }
        for (int i = 1; i < more; i++) {
            res[0] = res[0] + (rowsMore ? matrix[i][0] : matrix[0][i]);
            for (int j = 1; j < less; j++) {
                res[j] = Math.min(res[j - 1], res[j]) + (rowsMore ? matrix[i][j] : matrix[j][i]);
            }
        }

        return res[less - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };
        System.out.println(minPathSum(matrix));
    }
}
