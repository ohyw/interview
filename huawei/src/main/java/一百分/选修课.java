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

        new ElectiveCourse().solve(str1, str2);
    }
}

class ElectiveCourse {
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
    public void solve(String str1, String str2) {
        //把数据转换成hash表，key-value：学号-成绩
        HashMap<String, Integer> student_one = convert(str1);
        HashMap<String, Integer> student_two = convert(str2);

        //找出同时报了两门选修课的学生信息，合并成绩，剔除掉其他学生信息，结果保留在student_one中
        search(student_one, student_two);

        //按照要求输出结果
        if (student_one.isEmpty()) {
            System.out.println("NULL");
        }
        /*
        1.前5位学号升序排列
        2.同学号按两门成绩之和升序排列
        3.同成绩按后3位升序排列
        */
        //转成格式：hashmap<前5位, Set<完整学号, 总分数>>
        Set<String> fiveids = new TreeSet<>();
        Map<String, Set<Student>> students = new HashMap<>();

        Iterator<Map.Entry<String, Integer>> iterator = student_one.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String fiveid = entry.getKey().substring(0, 5);
            fiveids.add(fiveid);
            students.computeIfAbsent(fiveid, k -> new TreeSet<>()).add(new Student(entry.getKey(), entry.getValue()));
        }

        for (String fiveid : fiveids) {
            System.out.println(fiveid);
            StringBuilder s = new StringBuilder();
            for (Student stu : students.get(fiveid)) {
                s.append(stu.id).append(";");
            }
            System.out.println(s.substring(0, s.length() - 1));
        }
    }

    private void search(HashMap<String, Integer> source, HashMap<String, Integer> target) {
        Iterator<Map.Entry<String, Integer>> iterator = source.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (target.containsKey(entry.getKey())) {
                entry.setValue(entry.getValue() + target.get(entry.getKey()));
            } else {
                iterator.remove();
            }
        }
    }

    private HashMap<String, Integer> convert(String string) {
        HashMap<String, Integer> map = new HashMap<>();
        //String格式：xxxxxxxx,xx;xxxxxxxx,xx
        StringTokenizer st = new StringTokenizer(string, ";");
        while (st.hasMoreElements()) {
            String[] ss = st.nextToken().split(",");
            map.put(ss[0], Integer.valueOf(ss[1]));
        }
        return map;
    }
}
