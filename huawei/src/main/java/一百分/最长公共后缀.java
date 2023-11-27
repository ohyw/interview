package 一百分;

import java.util.Scanner;

/**
 * 思路：逐个比较，第一个和第二个的公共后缀跟第三个比较，以此类推
 */
public class 最长公共后缀 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine()
                .replace("[", "")
                .replace("]", "")
                .replace("\"", "")
                .replace(" ", ",")
                .split(",");
        String result = strings[0];
        for (int i = 1; i < strings.length; i++) {
            result = compare(result, strings[i]);
            if ("".equals(result)) {
                result = "@Zero";
                break;
            }
        }
        System.out.println(result);
    }

    private static String compare(String str1, String str2) {
        int str1_length = str1.length();
        int str2_length = str2.length();
        int min = Math.min(str1_length, str2_length);
        int index = 0;
        for (int i = 0; i < min; i++) {
            if (str1.charAt(str1_length - 1 - i) == str2.charAt(str2_length - 1 - i)) {
                index++;
            }
        }

        return str1.substring(str1_length - index, str1_length);
    }
}
