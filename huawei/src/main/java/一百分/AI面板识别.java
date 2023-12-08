package 一百分;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 思路：
 * 1.创建AIPanel对象保存区域信息
 * 2.放入treeset根据规则排序
 * 难点：
 * 题目难读懂，没有说识别区域是一个圆
 */
public class AI面板识别 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());

        Set<AIPanel> set = new TreeSet<>();
        for (int i = 0; i < num; i++) {
            int id = scanner.nextInt();
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            set.add(new AIPanel(id, (x1 + x2) / 2, (y2 + y2) / 2, (x2 - x1) / 2));
        }

        StringBuilder sb = new StringBuilder();
        for (AIPanel panel : set) {
            sb.append(panel.id).append(" ");
        }
        System.out.println(sb.substring(0, sb.length() - 1));
    }
}

class AIPanel implements Comparable<AIPanel> {
    int id;
    int x;
    int y;
    int r;
    public AIPanel(int id, int x, int y, int r) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public int compareTo(AIPanel o) {
        if (y - o.y <= r) {
            return Integer.compare(x, o.x);
        }

        return Integer.compare(y, o.y);
    }
}