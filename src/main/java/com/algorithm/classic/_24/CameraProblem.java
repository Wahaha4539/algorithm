package com.algorithm.classic._24;

//题目四
public class CameraProblem {
    private static class Node {
        private Node left;
        private Node right;

        public Node() {
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    private static class Info {
        private int use;
        private int nouse;

        private int uncover;

        public Info(int use, int nouse, int uncover) {
            this.use = use;
            this.nouse = nouse;
            this.uncover = uncover;
        }

        public int getUncover() {
            return uncover;
        }

        public void setUncover(int uncover) {
            this.uncover = uncover;
        }

        public int getUse() {
            return use;
        }

        public void setUse(int use) {
            this.use = use;
        }

        public int getNouse() {
            return nouse;
        }

        public void setNouse(int nouse) {
            this.nouse = nouse;
        }
    }

    public static int minCamera(Node head) {
        if (head == null) {
            return 0;
        }
        Info headInfo = process(head);
        return Math.min(headInfo.getUse(), headInfo.getNouse());
    }

    private static Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info left = process(node.getLeft());
        Info right = process(node.getRight());
        if (left == null && right == null) {
            return new Info(1, 0, 0);
        }
        if (left == null) {
            return new Info(right.getUncover() + 1, right.getUse(), right.getNouse());
        }
        if (right == null) {
            return new Info(left.getUncover() + 1, left.getUse(), left.getNouse());
        }
        return new Info(Math.min(left.getUncover(), Math.min(left.getUse(), left.getNouse())) +
                Math.min(right.getUncover(), Math.min(right.getUse(), right.getNouse()))
                ,
                Math.min(Math.min(left.getUse() + right.getUse(), left.getUse() + right.getNouse()), left.getNouse()) + right.getUse(),
                left.getNouse() + right.getNouse());
    }
}

