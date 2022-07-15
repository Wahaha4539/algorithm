package com.algorithm.classic._17;

import java.util.Comparator;

public class BuildingOutline {
    //描述高度变化的对象
    public static class Op {
        //x轴上的值
        public int x;
        // true为加入，false为删除
        public boolean isAdd;// true为加入，false为删除
        // 高度
        public int h;

        // 高度
        public Op(int x, boolean isAdd, int h) {
            this.x = x;
            this.isAdd = isAdd;
            this.h = h;
        }
    }


//    排序的比较策略

//1，第一个维度的x值从小到大。
//
//2，如果第一个维度的值相等，看第二个维度的值，“加入”排在前，“删除”排在后

//3，如果两个对象第一维度和第二个维度的值都相等，则认为两个对象相等，谁在前都行。

    public static class NodeComparator implements Comparator<Op> {
        @Override
        public int compare(Op o1, Op o2) {
            if (o1.x != o2.x) {
                return o1.x - o2.x;
            }
            if (o1.isAdd != o2.isAdd) {
                return o1.isAdd ? -1 : 1;
            }
            return 0;
        }
    }
}