package 一百分;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 思路：
 * 1.保存消息在hashmap中，发送时刻为key内容为value，并将key放入treeset排序
 * 2.创建消费者对象，保存订阅时刻、取消订阅时刻、消费消息，放入数组保持优先级
 * 3.遍历消息，找到每条消息合适的消费者
 */
public class 模拟消息队列 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] msgs = scanner.nextLine().split(" ");
        Set<Integer> time = new TreeSet<>();
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < msgs.length; i += 2) {
            time.add(Integer.valueOf(msgs[i]));
            map.put(Integer.valueOf(msgs[i]), msgs[i+1]);
        }

        String[] cons = scanner.nextLine().split(" ");
        Consumer[] consumers = new Consumer[cons.length/2];
        for (int i = 0; i < cons.length; i += 2) {
            consumers[i/2] = new Consumer(Integer.valueOf(cons[i]), Integer.valueOf(cons[i+1]));
        }

        //遍历消息treeset找到合适的消费者
        for (Integer t : time) {
            for (Consumer con : consumers) {
                if (con.sub <= t && con.des > t) {
                    if ("-1".equals(con.message)) {
                        con.message = map.get(t);
                    } else {
                        con.message = con.message + " " + map.get(t);
                    }
                    break;
                }
            }
        }

        for (Consumer c : consumers) {
            System.out.println(c.message);
        }
    }
}

class Consumer {
    Integer sub;
    Integer des;
    String message;
    public Consumer(Integer sub, Integer des) {
        this.sub = sub;
        this.des = des;
        message = "-1";
    }
}