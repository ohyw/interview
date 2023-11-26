package 一百分;

import java.util.Scanner;

/**
 * 思路：
 * 遍历数组，每个index位置双指针滑动窗口寻找最大连接
 */
public class 五子棋迷 {
    public static void main(String[] args) {
        // 输入数据，转换格式
        Scanner scanner = new Scanner(System.in);
        int target = scanner.nextInt();
        scanner.nextLine(); // nextInt后需要手动读取换行符，才能开始读下一行
        String[] strings = scanner.nextLine().split(" ");
        int count = strings.length;
        int[] pieces = new int[count];
        for (int i = 0; i < count; i++) {
            pieces[i] = Integer.parseInt(strings[i]);
        }

        // 遍历数组，每个index位置用双指针滑动窗口寻找最大连接数
        int maxCount = 0;
        int index = -1;
        int indexToMid = 0;
        for (int i = 0; i < count; i++) {
            if (pieces[i] != 0) continue;
            int lt = i - 1;
            int rt = i + 1;
            int lt_count = 0;
            int rt_count = 0;
            // 窗口左滑
            while (lt >= 0 && pieces[lt] == target) {
                lt--;
                lt_count++;
            }
            // 窗口右滑
            while (rt < count && pieces[rt] == target) {
                rt++;
                rt_count++;
            }
            int totalCount = lt_count + rt_count;
            int distanceToMid = Math.abs(i - count / 2);
            if (totalCount > maxCount || (totalCount == maxCount && distanceToMid < indexToMid)) {
                maxCount = totalCount;
                indexToMid = distanceToMid;
                index = i;
            }
        }
        System.out.println(index);
    }
}
