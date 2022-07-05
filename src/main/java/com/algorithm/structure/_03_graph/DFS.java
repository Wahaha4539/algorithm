package com.algorithm.structure._03_graph;

import java.util.HashSet;
import java.util.Stack;

public class DFS {
    public void dfs(Node start) {
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        System.out.println(start.getValue());
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node node : cur.getNexts()) {
                // 找到一个没有遍历的节点 打印 并将cur 和找到的节点放入 stack
                if (!set.contains(node)) {
                    stack.push(cur);
                    stack.push(node);
                    set.add(node);
                    System.out.println(node.getValue());
                    break;
                }
            }
        }
    }
}
