package 一百分;

import java.util.Scanner;

/**
 * 思路：
 * 遍历数组
 */
public class 座位调整 {
    public static void main(String[] args) {
        String[] line = new Scanner(System.in).nextLine().split(",");
        int nums = line.length;
        int[] seats = new int[nums];
        for (int i = 0; i < nums; i++) {
            seats[i] = Integer.parseInt(line[i]);
        }

        int count = 0;
        for (int i = 0; i < nums; i++) {
            if (seats[i] != 0) continue;
            if ((i == 0 && seats[i + 1] == 0) || (i == nums - 1 && seats[i - 1] == 0) || (seats[i - 1] == 0 && seats[i + 1] == 0)) {
                seats[i] = 1;
                count++;
                i++;
            }

        }
        System.out.println(count);
    }
}
