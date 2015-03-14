package com.simongong;

/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

思路：
贪心法，寻找当前能到达的最远位置max，从而能得到下一步最大可覆盖区间[start, end]。
这个“当前”是指上一步能到达的区间[start, end]，因此初始值为[0,0]
对于当前可到达区间，从左往右扫描，计算从当前可到达区间往前能到达的最大位置max。当max>length时，就可以返回step了。
每扫一次可到达区间，step就加一。

核心就是，认清每一步“贪心”的规则：从当前一步可到达的区域，开始贪心地计算下一步可到达的区域。 这里贪心的对象是区域的最大范围。

比如，开始时区间[start, end]，初始max=0. 遍历数组过程中，不断计算data[i]+i并更新最大值max。把原来的end+1作为新的start，新的max则更新为end。
不断循环，直到end>length
比如[2,1,3,1,1,4]，初始start=0, end=0, steps=0
扫[0,0]，初始max=0，扫完[0,0]后得到max=2, start=1, end=2。steps=1
扫[1,2]，初始max=0，扫完[1,2]后得到max=5, start=3, end=5。steps=2
扫[3,5]，初始max=0，扫完[3,5]后得到max=2, start=1, end=2。steps=1

 */
public class JumpGameII {

    public int jump(int[] data){
        if (data == null || data.length == 0 || data.length == 1) {
            return 0;
        }
        
        int start = 0, end = 0;
        int steps = 0;
        while (end < data.length - 1) {
            int max = 0;
            steps++;
            for (int i = start; i <= end; i++) {
                max = Math.max(max,  i + data[i]);
                if (max > data.length - 1) {
                    return steps;
                }
            }
            start = end + 1;
            end = max;
            if (start > end) {  // to many 0, cannot reach the new start. then no need to try forward
                break;
            }
        }
        // cannot reach end
        return Integer.MAX_VALUE;
    }
}
