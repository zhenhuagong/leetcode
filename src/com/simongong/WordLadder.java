package com.simongong;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

思路：
BFS。
每一位都有一个候选集合[a,z]（当然我们也可以根据dict逐位构造一个候选集），大小为26，可以看做26条分支，递归产生第二个节点的分支。
因此这是一个广度遍历的过程。
遍历的实现就是用队列，JAVA中是LinkedList。
具体实现过程稍微复杂，但整体逻辑很简单，就是对队列中剩余的元素，扫一遍，level++。
对于出队的每个元素的每一位，尝试26次。每次判断替换后的字符串是不是target。
 */
public class WordLadder {

    public int ladder(Set<String> dict, String start, String end){
        if (dict == null || start == null || end == null) {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        
        HashSet<String> set = new HashSet<>();
        set.add(start);
        
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                int len = s.length();
                for (int j = 0; j < len; j++) {
                    StringBuilder sb = new StringBuilder(s);
                    for (char c = 'a'; c <= 'z'; c++) {
                        sb.setCharAt(j, c);
                        String tmp = sb.toString();
                        if (tmp.equals(end)) {
                            return level;
                        }
                        
                        if (set.contains(tmp) || !dict.contains(tmp)) {
                            continue;
                        }
                        set.add(tmp);
                        queue.offer(tmp);
                    }
                }
            }
        }
        return 0;
    }
}
