package com.simongong;

/*
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.
Determine if you are able to reach the last index.
For example:
A = [2,3,1,1,4], return true.
A = [3,2,1,0,4], return false.

分析： 
不能到达终点的阻挡因素是值为0的节点。
一种思路：
如果到节点i处的时候，能够往右跳大于n-i步的话，就完成了任务。
因此我们需要知道在节点i处，能往右跳动的最大距离。
这正好能使用贪心策略，只要遍历一次，维护一个当前能跳到的最远位置。
计算在每个节点处能向右到达的最远节点，并不断更新这个值。
边界条件是在节点i处，计算出的最大值等于i。说明无法继续前进了。
 */
public class JumpGame {

    public static void main(String[] args) {
        int[] testData1 = {2,3,1,1,4};
        int[] testData2 = {3,2,1,0,4};
        System.out.println(canJump(testData1));
        System.out.println(canJump(testData2));
    }

    public static boolean canJump(int[] data){
        if(data == null){
            return false;
        }
        int currentMaxReachTo = 0;
        for(int i = 0; i < data.length; i++){
            currentMaxReachTo = Math.max(currentMaxReachTo, i + data[i]);
            if(currentMaxReachTo >= data.length - 1){
                return true;
            }
            if(currentMaxReachTo == i){
                return false;
            }
        }
        return true;
    }
}
