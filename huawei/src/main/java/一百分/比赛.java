package 一百分;

import java.util.*;

/**
 * 思路：
 * 1.选手对象记录选手编号、总成绩、各裁判打分数组，实现comparable，放入treeset排序
 * 2.TreeSet不能放重复元素
 */
public class 比赛 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] num = scanner.nextLine().split(",");
        int num_r = Integer.parseInt(num[0]);
        int num_p = Integer.parseInt(num[1]);
        if (num_r < 3 || num_r > 10 || num_p < 3 || num_p > 100) {
            System.out.println("-1");
            return;
        }

        int[][] matrix = new int[num_r][num_p];
        for (int i = 0; i < num_r; i++) {
            String[] str = scanner.nextLine().split(",");
            for (int j = 0; j < num_p; j++) {
                int current = Integer.parseInt(str[j]);
                if (current > 10 || current < 1) {
                    System.out.println("-1");
                    return;
                }
                matrix[i][j] = current;
            }
        }

        Set<Player> players = new TreeSet<>();
        for (int i = 0; i < num_p; i++) {
            int total = 0;
            int[] scores = new int[num_r];
            for (int j = 0; j < num_r; j++) {
                total += matrix[j][i];
                scores[j] = matrix[j][i];
            }
            Arrays.sort(scores);
            players.add(new Player(i+1, total, scores));
        }

        Iterator<Player> iterator = players.iterator();
        int count = 0;
        String res = "";
        while (iterator.hasNext()) {
            if (count == 3) break;
            Player player = iterator.next();
            if ("".equals(res)) {
                res = "" + player.index;
            } else {
                res += "," + player.index;
            }
            count++;
        }
        System.out.println(res);
    }
}

class Player implements Comparable<Player>{
    int index;
    int total;
    int[] scores;
    public Player(int index, int total, int[] scores) {
        this.index = index;
        this.total = total;
        this.scores = scores;
    }

    @Override
    public int compareTo(Player o) {
        if (total == o.total) {
            int length = scores.length;
            for (int i = 0; i < length; i++) {
                if (scores[length-i-1] != o.scores[length-i-1]) {
                    return (-1) * Integer.compare(scores[length-i-1], o.scores[length-i-1]);
                }
            }
        } else {
            return (-1) * Integer.compare(total, o.total);
        }
        return 0;
    }
}