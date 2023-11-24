package 一百分;

import java.util.Scanner;

public class 停车场需要打开多少监视器 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = input.nextInt();
        int n = input.nextInt();
        int[][] park = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                park[i][j] = input.nextInt();
            }
        }

        System.out.println(new Monitor().count(park, m, n));
    }
}

class Monitor {
    public int count(int[][] park, int m, int n) {
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (park[i][j] == 1) {
                    count++;
                    continue;
                }

                boolean flag = false;
                //上
                if (i - 1 >= 0 && park[i-1][j] == 1) flag = true;
                //左
                if (j - 1 >= 0 && park[i][j-1] == 1) flag = true;
                //下
                if (i + 1 < m && park[i+1][j] == 1) flag = true;
                //右
                if (j + 1 < n && park[i][j+1] == 1) flag = true;

                if (flag) count++;
            }
        }
        return count;
    }
}