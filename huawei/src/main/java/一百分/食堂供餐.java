package 一百分;

import java.util.Scanner;

/**
 * 思路：
 * 单位时间供餐数量最小为0，最大为差值，找合适值，二分法
 */
public class 食堂供餐 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int[] P = new int[N];

        int total = 0;
        for (int i = 0; i < N; i++) {
            P[i] = scanner.nextInt();
            total += P[i];
        }

        int lt = 0;
        int rt = total - M;
        while (lt < rt) {
            int mid = (lt + rt) / 2;
            if (check(mid, P, M)) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }
        System.out.println(lt);
    }

    private static boolean check(int speed, int[] P, int total) {
        for (int i = 0; i < P.length; i++) {
            if (total < P[i]) return false;
            total = total - P[i] + speed;
        }
        return true;
    }
}
