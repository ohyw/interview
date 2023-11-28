package 一百分;

import java.util.Scanner;

/**
 * 思路：
 * 1.输入时就统计行列0的个数
 */
public class 矩阵稀疏扫描 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int cum = scanner.nextInt();

        int[] row_zeros = new int[row];
        int[] cum_zeros = new int[cum];
        int[][] matrix = new int[row][cum];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cum; j++) {
                int in = scanner.nextInt();
                matrix[i][j] = in;
                if (in == 0) {
                    row_zeros[i]++;
                    cum_zeros[j]++;
                }
            }
        }
        int count_row_zeros = 0;
        for (int i : row_zeros) {
            if (i >= row_zeros.length / 2) count_row_zeros++;
        }
        int count_cum_zeros = 0;
        for (int i : cum_zeros) {
            if (i >= cum_zeros.length / 2) count_cum_zeros++;
        }
        System.out.println(count_row_zeros);
        System.out.println(count_cum_zeros);
    }
}
