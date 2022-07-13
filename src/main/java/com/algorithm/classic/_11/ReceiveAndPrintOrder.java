package com.algorithm.classic._11;

import java.util.HashMap;
import java.util.Map;

public class ReceiveAndPrintOrder {
    private static class Node {
        String msg;
        Node next;

        public Node(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Map<Integer, Node> headMap;
    private Map<Integer, Node> tailMap;
    private int waitpoint;

    public ReceiveAndPrintOrder() {
        this.waitpoint = 1;
        this.headMap = new HashMap<>();
        this.tailMap = new HashMap<>();
    }

    public void receive(int num, String msg) {
        if (num < 1) {
            return;
        }

        Node node = new Node(msg);
        headMap.put(num, node);
        tailMap.put(num, node);
        if (tailMap.containsKey(num - 1)) {
            Node nodePre = tailMap.get(num - 1);
            nodePre.setNext(node);
            tailMap.remove(num - 1);
            headMap.remove(num);
        }

        if (headMap.containsKey(num + 1)) {
            Node nodeLast = headMap.get(num + 1);
            node.setNext(nodeLast);
            headMap.remove(num + 1);
            tailMap.remove(num);
        }

        if (num == waitpoint) {
            print();
        }
    }

    private void print() {
        Node cur = headMap.get(waitpoint);
        headMap.remove(waitpoint);
        while (cur != null) {
            System.out.print(cur.getMsg() + " ");
            waitpoint++;
            cur = cur.getNext();
        }
        System.out.println();
        tailMap.remove(waitpoint - 1);
    }
}
