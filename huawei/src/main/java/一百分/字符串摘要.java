package 一百分;

import sun.util.resources.hr.CalendarData_hr;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

/**
 * 思路：
 * 1.一个字符对应一个数字（次数），可以重复
 * 2.创建StringNode对象，用treeset排序
 * 难点：
 * 找到一个字符后面的次数：连续出现的，非连续出现的
 */
public class 字符串摘要 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine().toLowerCase();

        Set<StringNode> set = new TreeSet<>();
        for (int i = 0; i < line.length(); i++) {
            char current = line.charAt(i);
            if (!Character.isLetter(current)) continue;

            int count = 0;
            for (int j = i; j < line.length(); j++) {
                if (line.charAt(j) != current) break;
                count++;
            }
            //连续字符串循环时跳过
            if (count >= 2) {
                i += count - 1;
            } else {
                count = -1;
                for (int j = i; j < line.length(); j++) {
                    if (line.charAt(j) == current) count++;
                }
            }
            set.add(new StringNode(String.valueOf(current), count));
        }
        StringBuilder sb = new StringBuilder();
        for (StringNode sn : set) {
            sb.append(sn.letter).append(sn.count);
        }
        System.out.println(sb);
    }
}

class StringNode implements Comparable<StringNode> {
    String uuid;
    String letter;
    Integer count;
    public StringNode(String letter, Integer count) {
        this.letter = letter;
        this.count = count;
        uuid = UUID.randomUUID().toString();
    }

    @Override
    public int compareTo(StringNode o) {
        if (!letter.equals(o.letter)) {
            return letter.compareTo(o.letter);
        } else if (count != o.count) {
            return (-1) * count.compareTo(o.count);
        }
        return uuid.compareTo(o.uuid);
    }
}