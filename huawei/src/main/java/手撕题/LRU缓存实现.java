package 手撕题;

import java.util.HashMap;

public class LRU缓存实现 {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(5);
        for (int i = 0; i < 22; i++) {
            cache.put(i, i * 2);
        }
        cache.list();
        System.out.println("==============================");
        cache.get(18);
        cache.put(33, 33);
        cache.list();
    }
}

class LRUCache {
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;
        public Node() {}
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size;
    private int capacity;
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> cache;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        cache = new HashMap<>(capacity);
        head.next = tail;
        tail.prev = head;
    }

    public void list() {
        Node current = head;
        while (true) {
            current = current.next;
            if (current == tail) break;
            System.out.println(current.key + " = " + current.value);
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
            return;
        }
        Node node = new Node(key, value);
        if (size >= capacity) {
            removeTail();
        }
        cache.put(key, node);
        addToHead(node);
        size++;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        moveToHead(node);
        return node.value;
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeTail() {
        removeNode(tail.prev);
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
}