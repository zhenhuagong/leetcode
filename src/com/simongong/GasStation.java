package com.simongong;

/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.

Greedy

 据说是Bloomberg的面试题。

思路：
输入是gas[],cost[]数组，输出是gas的某个index。
我们先来看看暴力法：
逐个从从[1,...,n]个位置出发，尝试是否能走一圈。但实际上，我们可以再下一次尝试的时候，利用上一次尝试的经验，从而选出更优的起点。

改进：
设当前车处在i位置（1<i<n），尝试出发走一圈，那么到下一个位置j之后的状态为：
sum += gas[j] - cost[j]，如果sum < 0，说明无法从i直接走到j.
我们看看从[i+1, j-1]这个范围内某点出发能否到达j。
一方面，i能走到i+1，而i不能到j，说明i+1不能到j。依次类推都不能到j。
因此，如果sum < 0，下一次出发的起点就设成j+1，同时sum置零。

这就是贪心法的应用。

实现：
思路还是跟上面一样，用一个sum来判断当前的点是否适合做起点。
另外再加一个total变量，计算从头到尾全部的gas[i] - cost[i]，判断总的需求是不是大于供给。
如果总的需求大于供给那么肯定是无解的，如果需求小于等于供给，就可以返回刚才找到的起始点。
 */
public class GasStation {

    public int getGasStation(int[] gas, int[] cost){
        if(gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length){
            return -1;
        }
        int start = 0;
        int sum = 0;
        int total = 0;
        for(int i = 0; i < gas.length; i++){
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if(sum < 0){
                start = i + 1;
                sum = 0;
            }
        }
        
        if(total < 0){
            return -1;
        }else{
            return start;
        }
    }
}
