package 一百分;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 思路：深度优先，递归
 * 1.当前值为0跳过，不为0开始递归
 * 2.递归过后把当前值设为0，防止重复递归
 */
public class 寻找最大价值的矿堆 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<Integer, int[]> map = new HashMap<>();
        int row = 0;
        int cum = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if ("".equals(line)) break;
            int[] array = new int[line.length()];

            for (int i = 0; i < line.length(); i++) {
                array[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }
            map.put(row, array);
            if (cum < line.length()) {
                cum = line.length();
            }
            row++;
        }

        int[][] matrix = new int[row][cum];
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] src = entry.getValue();
            if (src.length == cum) {
                matrix[entry.getKey()] = entry.getValue();
            } else { // 补齐长度不够的输入
                int[] des = new int[cum];
                System.arraycopy(src, 0, des, 0, src.length);
                matrix[entry.getKey()] = des;
            }

        }

        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < cum; j++) {
                max = Math.max(max, dfs(matrix, i, j, 0));
            }
        }

        System.out.println(max);
    }

    private static int dfs(int[][] matrix, int x, int y, int max) {
        if (matrix[x][y] == 0) {
            return max;
        }
        max += matrix[x][y];
        matrix[x][y] = 0;

        //上
        if (x - 1 >= 0 && matrix[x-1][y] > 0) {
            max = dfs(matrix, x-1, y, max);
        }
        //左
        if (y - 1 >= 0 && matrix[x][y-1] > 0) {
            max = dfs(matrix, x, y-1, max);
        }
        //下
        if (x + 1 < matrix.length && matrix[x+1][y] > 0) {
            max = dfs(matrix, x+1, y, max);
        }
        //右
        if (y + 1 < matrix[0].length && matrix[x][y+1] > 0) {
            max = dfs(matrix, x, y+1, max);
        }
        return max;
    }
}
