package 一百分;

import java.util.Scanner;

/**
 * 思路：
 * 1. 两棵树最小距离为1，最大距离为(首尾距离)/(数的棵树-1)，比如1，3，6，7，8，11，13种3棵树，最大距离为(13-1)/(3-1)=6
 * 2. 在这个最小和最大之间找一个满足要求的值，取其中一个值，按照这个距离去种树，如果种不下表示距离大了，反之距离刚好或者小了
 * 3. 找1到n之间满足要求的值，用二分法
 */
public class 最佳植树距离 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int num = input.nextInt();
        int[] holes = new int[num];
        for (int i = 0; i < num; i++) {
            holes[i] = input.nextInt();
        }

        int tree = input.nextInt();
        System.out.println(new Problem().solve(holes, tree));
    }
}

class Problem {
    public int solve(int[] hole, int tree) {
        int min = 1;
        int max = (hole[hole.length - 1] - hole[0]) / (tree -1);
        int ans = 1;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (check(hole, tree, mid)) {
                min = mid + 1;
                ans = mid;
            } else {
                max = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[] hole, int tree, int gap) {
        int count = 1;
        int current = 0;
        for (int i = 1; i < hole.length; i++) {
            if ((hole[i] - hole[current]) >= gap) {
                count++;
                current = i;
            }
        }
        if (count <= tree) {
            return true;
        }
        return false;
    }
}