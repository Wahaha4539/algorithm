package com.algorithm.structure._03_graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int value;
    private int in;
    private int out;
    private List<Node> nexts;
    private List<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nexts = new ArrayList<>();
        this.edges = new ArrayList<>();
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public List<Node> getNexts() {
        return nexts;
    }

    public void setNexts(List<Node> nexts) {
        this.nexts = nexts;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
