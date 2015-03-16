package com.simongong;

import java.awt.Point;
import java.util.HashMap;

/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

思路：
题目中straight line是指平面坐标系上的任意直线，需要寻找哪个条直线上包含给定的点最多。
在一条直线上的点的特征是斜率相同，也就是y=kx+b中的k相同。
我们的着眼点不能放在直线上。如果从直线出发，寻找落在某个k斜率的线上的点，会比较低效，而且无法处理重合点的问题。
因此，从给定的点出发比较划算。采用雷达式的扫描，从一个点point[i]出发，扫描其它的点，并计算point[i]与其它点的斜率，同时判断重合点，都计入相同斜率的点的个数，得到包含点数最多的那个斜率。
使用一个map来保存遍历结果：<Double, Integer> - <k, pointTotal>

测试用例边界值：
1. 包含Y轴斜率的线
2. 包含重复点
3. 只有重复点

注意:
1. 重要！每次换一个点计算时，map要重建。因为即使k相同，只代表这两条线是平行的，不代表会是同一条线。
2. 对于之前已经计算过的点，内层扫描是可以忽略。也就是j的初始值是i。

注意程序中标注的NOTE，这个程序要bug free不容易。
 */
public class MaxPointsOnLine {

    public static void main(String[] args) {

    }

    public static int maxPoints(Point[] points){
        int max = 0;
        
        if (points == null) {
            return max;
        }
        
        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> kMap = new HashMap<Double, Integer>();
            int duplicate = 0;
            for (int j = i; j < points.length; j++) {
                // NOTE1: first, check if it's another duplicate
                if (points[j].x == points[i].x && points[j].y == points[i].y) {
                    duplicate++;
                    continue;
                }
                double k = Double.MAX_VALUE;    // NOTE2: initialize slope as MAX_VALUE
                if (points[j].x != points[i].x) {   // NOTE3: not in a vertical direction
                    // NOTE4: a float value in Java could be minus zero -0.0
                    // we can get around the problem by adding 0
                    // see: http://stackoverflow.com/questions/6724031/how-can-a-primitive-float-value-be-0-0-what-does-that-mean
                    k = 0 + (double)(points[j].y - points[i].y) / (double)(points[j].x - points[i].x);
                }
                if (kMap.containsKey(k)) {
                    kMap.put(k, kMap.get(k) + 1);
                }else{
                    kMap.put(k, 1);
                }
            }
            //NOTE5: the input points could be a set of duplicate points
            max = Math.max(max, duplicate);
            for(int n: kMap.values()){
                max = Math.max(max, n + duplicate);
            }
        }
        return max;
    }
}