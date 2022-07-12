package com.algorithm.structure._04_unionfindset;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionFindSet<T> {
    //v代表的节点 在内部查找合并操作的是Node对象
    private static class Node {
    }

    private HashMap<T, Node> values = new HashMap<>();
    private HashMap<Node, Node> parents = new HashMap<>();
    private HashMap<Node, Integer> sizeMap = new HashMap<>();

    public UnionFindSet(List<T> origin) {
        for (T t : origin) {
            Node node = new Node();
            this.values.put(t, node);
            this.parents.put(node, node);
            this.sizeMap.put(node, 1);
        }
    }

    /**
     * node 必须要在values map里 调用的时候决定
     */
    private Node findParent(Node node) {
        Stack<Node> path = new Stack<>();
        // 只有父亲是自己 才是头节点
        while (node != this.parents.get(node)) {
            path.add(node);
            node = this.parents.get(node);
        }
        // 重点优化 在找父亲节点的时候 沿途的节点父亲节点全部指向头节点
        while (!path.isEmpty()) {
            Node poll = path.pop();
            this.parents.put(poll, node);
        }
        return node;
    }

    public boolean isSameSet(T value1, T value2) {
        Node node1 = values.get(value1);
        Node node2 = values.get(value2);
        if (node1 == null || node2 == null) {
            return false;
        }
        Node headNode1 = findParent(node1);
        Node headNode2 = findParent(node2);
        return headNode1 == headNode2;
    }

    public void unionSet(T value1, T value2) {
        Node node1 = values.get(value1);
        Node node2 = values.get(value2);
        if (node1 == null || node2 == null) {
            // 报异常
            throw new RuntimeException("存在不属于数组中的数 ");
        }
        Node head1 = findParent(node1);
        Node head2 = findParent(node2);
        Node big = this.sizeMap.get(head1) > this.sizeMap.get(head2) ? head1 : head2;
        Node small = big == head1 ? head2 : head1;
        this.parents.put(small, big);
        this.sizeMap.put(big, this.sizeMap.get(big) + this.sizeMap.get(small));
        this.sizeMap.remove(small);
    }
    public int size() {
        return this.sizeMap.size();
    }
}
