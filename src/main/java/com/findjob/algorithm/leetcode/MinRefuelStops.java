package com.findjob.algorithm.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 能够到达目的地 最少加油次数
 * https://leetcode.cn/problems/minimum-number-of-refueling-stops/
 */
public class MinRefuelStops {
    // 贪心算法 + 大根堆(优先队列)
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (stations == null || stations.length == 0) {
            return startFuel >= target ? 0 : -1;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int[] distinct = new int[stations.length];
        for (int i = 0; i <= stations.length - 1; i++) {
            distinct[i] = stations[i][0] - (i == 0 ? 0 : stations[i-1][0]);
        }
        int ans = 0;
        int rest = target;
        int fuel = startFuel;
        for (int i = 0; i < stations.length && rest >= 0; i++) {
            int toI = distinct[i];
            fuel = fuel - toI;
            rest = rest - toI;
            // 贪心 少油的时候 可以逻辑回去加上最多油
            if (fuel < 0) {
                // 加油
                while (fuel < 0) {
                    // 没有油可以加 返回-1
                    if (queue.peek() == null) {
                        return -1;
                    }
                    fuel += queue.poll();
                    ans++;
                }
            }
            // 优先队列加上本站的油 待后续没油的时候使用
            queue.add(stations[i][1]);
        }
        fuel = fuel - rest;
        while (fuel < 0) {
            // 没有油可以加 返回-1
            if (queue.peek() == null) {
                return -1;
            }
            fuel += queue.poll();
            ans++;
        }
        return ans;
    }


    // target 要跑的距离
    // startFuel 开始能够跑的距离
    // stations 加油站信息 stations[i][0] 代表距离开始位置距离 stations[i][1] 代表如果要加油加多少油
    public static int minRefuelStops1(int target, int startFuel, int[][] stations) {
        if (stations == null || stations.length == 0) {
            return startFuel >= target ? 0 : -1;
        }
        return process(target - stations[0][0], startFuel - stations[0][0], 0, stations, new HashMap<Cache, Integer>());
    }

    public static class Cache {
        private Integer rest;
        private Integer has;

        public Integer getRest() {
            return rest;
        }

        public void setRest(Integer rest) {
            this.rest = rest;
        }

        public Integer getHas() {
            return has;
        }

        public void setHas(Integer has) {
            this.has = has;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public Cache(Integer rest, Integer has, Integer index) {
            this.rest = rest;
            this.has = has;
            this.index = index;
        }

        @Override
        public int hashCode() {
            return (this.getRest() + " " + this.getHas() + " " + this.getIndex()).hashCode();
        }

        private Integer index;

        public boolean equals(Object obj) {
            Cache ob = (Cache) obj;
            return ob.getHas().equals(this.getHas()) && ob.getIndex().equals(this.getIndex()) && ob.getRest().equals(getRest());
        }
    }

    /**
     * 到达i位置时，能够到达rest的最少加油次数
     *
     * @param rest     剩余的距离
     * @param has      还剩多少油
     * @param i        i号加油站
     * @param stations 加油站信息
     */
    public static int process(int rest, int has, int i, int[][] stations, HashMap<Cache, Integer> set) {
        // 来到i加油站 如果has<0 说明没有油量能够来到i
        if (has < 0) {
            return -1;
        }
        if (has >= rest) {
            return 0;
        }
        // i== stations.length - 1 最后一个加油站
        if (i == stations.length - 1) {
            // 如果has >= rest 说明不用加油就可以到达
            // 如果 has< rest 那么就要看加了油了能不能到达rest 如果能 则加了一次油 不能返回-1
            return has + stations[i][1] >= rest ? 1 : -1;
        }
        if (set.get(new Cache(rest, has, i)) != null) {
            return set.get(new Cache(rest, has, i));
        }
        int disnext = stations[i + 1][0] - stations[i][0];
        // 此时不加油
        int p1 = process(rest - disnext, has - disnext, i + 1, stations, set);
        // 此时加油
        int p2 = process(rest - disnext, has + stations[i][1] - disnext, i + 1, stations, set);
        int ans;
        if (p1 == -1 && p2 == -1) {
            ans = -1;
        } else if (p1 == -1) {
            ans = p2 + 1;
        } else if (p2 == -1) {
            ans = p1;
        } else {
            ans = Math.min(p1, 1 + p2);
        }
        set.put(new Cache(rest, has, i), ans);
        return ans;
    }


//    public static int minRefuelStops2(int target, int startFuel, int[][] stations) {
//        if (stations == null || stations.length == 0) {
//            return startFuel >= target ? 0 : -1;
//        }
//
//        int N = stations.length;
//        int M = target + 1;
//        int K = target + 1;
//
//
//    }


    //100
    //25
    //[[25,25],[50,25],[75,25]]
    //[[10,60],[20,30],[30,30],[60,40]]
    public static void main(String[] args) {
//        int[][] stations = new int[4][2];
//        stations[0][0] = 10;
//        stations[0][1] = 60;
//        stations[1][0] = 20;
//        stations[1][1] = 30;
//        stations[2][0] = 30;
//        stations[2][1] = 30;
//        stations[3][0] = 60;
//        stations[3][1] = 40;

        int[][] stations = new int[3][2];
        stations[0][0] = 25;
        stations[0][1] = 25;
        stations[1][0] = 50;
        stations[1][1] = 25;
        stations[2][0] = 75;
        stations[2][1] = 25;

        System.out.println(minRefuelStops(100, 25, stations));

    }
}
