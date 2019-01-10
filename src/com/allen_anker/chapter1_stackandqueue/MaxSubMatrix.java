package com.allen_anker.chapter1_stackandqueue;

import java.util.Stack;

public class MaxSubMatrix {
    /**
     * Given a matrix contains only 0 and 1, return the number of 1s in a sub-matrix whose elements are all 1.
     *
     * @param matrix
     * @return the number of elements(all 1) in the sub-matrix
     */
    public int getMaxSubMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int cols = matrix[0].length;
        int[] heights = matrix[0];
        int maxArea = getMaxRectAreaInHistogram(heights);
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < cols; j++) {
                heights[j] = matrix[i][j] == 1 ? heights[j] + 1 : 0;
            }
            int currArea = getMaxRectAreaInHistogram(heights);
            maxArea = maxArea > currArea ? maxArea : currArea;
        }

        return maxArea;
    }

    private int getMaxRectAreaInHistogram(int[] heights) {
        if (heights == null || heights.length == 0) {
            throw new IllegalArgumentException("Invalid parameter");
        }

        // stack stores the indexes of heights, from bottom to top with increasing height's values
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int currArea = (i - k - 1) * heights[j];
                maxArea = maxArea > currArea ? maxArea : currArea;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int currArea = (heights.length - k - 1) * heights[j];
            maxArea = maxArea > currArea ? maxArea : currArea;
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        System.out.println(new MaxSubMatrix().getMaxSubMatrix(matrix));
    }
}
