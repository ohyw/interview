package 一百分;

import java.util.Scanner;

/**
 * 思路：
 * 遍历数组，回溯递归
 */
public class 代表团坐车 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(",");
        int[] nums = new int[line.length];
        for (int i = 0; i < line.length; i++) {
            nums[i] = Integer.parseInt(line[i]);
        }
        int target = scanner.nextInt();

        System.out.println(new TakeCar().take(nums, target));
    }
}

class TakeCar {
    int count = 0;
    public int take(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    private void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) count++; return;
        }
        // 人数组合只有两种：算上当前值的，不算当前值的
        backtrack(nums, target, index + 1, sum + nums[index]);
        backtrack(nums, target, index + 1, sum);
    }
}