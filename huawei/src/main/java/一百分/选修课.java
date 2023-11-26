package 一百分;

import java.util.*;

/**
 * 思路：
 * 难点在排序，用treeset自然排序和手动排序特性
 * 单独维护一个前5位学号的treeset作为一级排序
 * 使用student类排序成绩，成绩相同再排序学号
 */
public class 选修课 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str1 = input.nextLine();
        String str2 = input.nextLine();

        // 报名了课程2的学生转换成hashmap
        StringTokenizer st1 = new StringTokenizer(str1, ";");
        StringTokenizer st2 = new StringTokenizer(str2, ";");
        HashMap<String, Integer> st2map = new HashMap<>();
        while (st2.hasMoreElements()) {
            String[] idandscore = st2.nextToken().split(",");
            st2map.put(idandscore[0], Integer.parseInt(idandscore[1]));
        }
        // 查找同时报名了2门课程的学生
        Set<String> fiveids = new TreeSet<>();
        Map<String, Set<Student>> students = new HashMap<>();
        while (st1.hasMoreElements()) {
            String[] idandscore = st1.nextToken().split(",");
            String id = idandscore[0];
            String fiveid = id.substring(0, 5);
            int score = Integer.parseInt(idandscore[1]);
            if (st2map.containsKey(id)) {
                fiveids.add(fiveid);
                students.computeIfAbsent(fiveid, key -> new TreeSet<>()).add(new Student(id, score));
            }
        }

        if (fiveids.isEmpty()) {
            System.out.println("NULL");
            return;
        }
        for (String fiveid : fiveids) {
            System.out.println(fiveid);
            StringBuilder sb = new StringBuilder();
            for (Student stu : students.get(fiveid)) {
                sb.append(stu.id).append(";");
            }
            System.out.println(sb.substring(0, sb.length() - 1));
        }
    }
}

class Student implements Comparable<Student> {
    String id;
    int score;
    public Student(String id, int score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public int compareTo(Student o) {
        return score != o.score ? Integer.compare(score, o.score) : id.compareTo(o.id);
    }
}