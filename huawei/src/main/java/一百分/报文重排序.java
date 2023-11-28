package 一百分;

import java.util.*;

/**
 * 思路：
 * 1.将报文后缀的数字作为key，报文作为value放在map
 * 2.treeset维护报文顺序
 * 难点：
 * 后缀的数字不知道是几位数
 */
public class 报文重排序 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());

        String[] strs = scanner.nextLine().split(" ");
        Set<Integer> index = new TreeSet<>();
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String s = strs[i];
            for (int j = 0; j < s.length(); j++) {
                if (!Character.isDigit(s.charAt(s.length()-j-1))) {
                    Integer key = Integer.valueOf(s.substring(s.length()-j, s.length()));
                    map.put(key, s.substring(0,s.length()-j));
                    index.add(key);
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i : index) {
            sb.append(map.get(i)).append(" ");
        }
        System.out.println(sb.substring(0, sb.length()-1));
    }
}
