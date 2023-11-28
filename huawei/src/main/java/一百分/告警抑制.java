package 一百分;

import java.util.*;

/**
 * 思路：
 * 1.将某个告警被谁抑制的信息放在hashmap<key, Set<>>中
 * 2.如果抑制set和当前要输出的告警信息没有交集，这个告警就能输出
 */
public class 告警抑制 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        HashMap<String, Set<String>> map = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String[] line = scanner.nextLine().split(" ");
            String y = line[0];
            String b = line[1];
            if (map.containsKey(b)) {
                map.get(b).add(y);
            } else {
                Set<String> set = new TreeSet<>();
                set.add(y);
                map.put(b, set);
            }
        }

        String[] warns = scanner.nextLine().split(" ");
        Set<String > warn_set = new TreeSet<>();
        for (int i = 0; i < warns.length; i++) {
            warn_set.add(warns[i]);
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < warns.length; i++) {
            if (!map.containsKey(warns[i])) {
                output.append(warns[i]).append(" ");
            } else {
                if (Collections.disjoint(map.get(warns[i]), warn_set)) {
                    output.append(warns[i]).append(" ");
                }
            }
        }
        System.out.println(output.substring(0,output.length() - 1));
    }
}
