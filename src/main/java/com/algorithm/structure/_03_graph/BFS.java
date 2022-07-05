package com.algorithm.structure._03_graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图的宽度优先遍历
 */
public class BFS {
    public void bfs(Node start) {
        //宽度优先遍历 使用queue
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> has = new HashSet<>();
        queue.add(start);
        has.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.getValue());
            List<Node> nexts = cur.getNexts();
            for (Node node : nexts) {
                if (!has.contains(node)) {
                    queue.add(node);
                    has.add(node);
                }
            }
        }
    }
}
