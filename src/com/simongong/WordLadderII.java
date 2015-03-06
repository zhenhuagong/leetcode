package com.simongong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/*
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.

思路：
1. 首先观察示例中的解集合，可以把两个解画到一棵树上
2. 再观察题目描述，每次只改变一个字母，而且规定了起点节点和终点节点，要求二者之间的路径
3. 我们可以用树结构来组织数据，把起点作为根节点，然后从字典里依次取第一层的子节点，能当作子节点的条件就是它与父节点只有一个字母的区别。用深度遍历来建立，在建立的过程发现需要避免出现环，因此要记录节点是否已经被加入到树。
5. 还可以先求编辑距离，然后按照最短距离来找结果。
6. 每次往树里添加节点的时候都判断是否找到目标节点，是则输出路径，并回退继续深度遍历。

从以上过程可以看出，其实用树并不合适，图结构更合适。图中每个node都是一个string，两个node相连如果他们只相差一个字符。把问题转化为找到所有从start到end的最短路径（路径上节点）
1. 这个图是动态构建的：也就是我们访问到某个string而且这个string在dict里面，我们才把他加入图。
2. 因为是要求最短路径，所以要使用BFS而不能用DFS。当遇到一个没有见过的string，而且在dict里，就把它加入一个队列，并且记录这个string是从哪些节点访问到的。
3. 当访问到了end的时候，采用back trace（我们已经记录了每个string的前一个string的集合），来找到所有通往start的路径。
4. 最后，可以利用string与start的距离来决定哪些是最短路径。

注意：
1. 主要是如何构图的问题。 首先不能用Adjacency matrix，O(n^2)超时。考虑用Adjacency List
邻接列表（Adjacency List）。具体细节可以参见这篇wiki：http://en.wikipedia.org/wiki/Adjacency_list 。简单来说，这是一个存储图中每个顶点的所有邻接顶点的数据结构。如无向图：
   a
 /   \
b --- c
它的邻接列表为：
a => b, c
b => a, c
c => a, b
具体到本问题，我们可以发现，start到end的所有序列，就是一个这些序列中所有单词为点组成的图。
如果我们生成了该图的邻接列表，就可以不断的在每个单词的邻接列表里找到转换的下一个单词，从而最终找到end。
那么，我们首先要对字典里的单词生成邻接列表：遍历字典里的单词，针对每个单词用前面逐字母替换的方法找出邻接单词，并保存起来。
有了邻接列表，寻找序列的方法就发生变化了。
我们不再逐个替换字母，而是从start出发，遍历start的邻接顶点，将邻接顶点放入队列中。并重复操作直到队列为空。
还有一个发生变化的地方是去重操作。由于不再遍历字典，现在我们发现非同层出现重复的单词就跳过它而不是从字典里删去。


 */
public class WordLadderII {

    public static void main(String[] args) {
        String start = "hit", end = "cog";
        HashSet<String> dict = new HashSet<String>();
        dict.add("hot");
        dict.add("dot");
        dict.add("dog");
        dict.add("lot");
        dict.add("log");
        Utils.printList("word ladders:", findLadders(start, end, dict), ", ");
    }

    /*
     * HashMap<String, HashSet<String>> visited //存储每个string的前一个string的集合（根据我们在图的访问顺序）
     * HashMap<String, Integer> level   //存储每个string距离start的距离，最小值是1
     * LinkedList<String> queue   //存储我们已经知道的，但是没有正式访问的string
     */
    public static ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict){
        
        //存储每个string的前一个string的集合（根据我们在图的访问顺序）
        HashMap<String, HashSet<String>> visited = new HashMap<String, HashSet<String>>();
        
        //存储每个string距离start的距离，最小值是1
        HashMap<String, Integer> level = new HashMap<String, Integer>();
        
        //BFS用。存储我们已经知道的，但是没有正式访问的string
        LinkedList<String> queue = new LinkedList<String>();
        
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        
        if(start == null || end == null || start.length() != end.length()){
            return result;
        }
        
        // store the path from start
        HashSet<String> path = new HashSet<String>();
        // record the minimal length we get
        int minLength = Integer.MAX_VALUE;
        visited.put(start, path);
        level.put(start, 1);
        queue.add(start);
        while(!queue.isEmpty()){
            String s = queue.remove();
            char[] chars = s.toCharArray();
            for(int i = 0; i < s.length(); i++){
                char old = chars[i];
                for(char c = 'a'; c <= 'z'; c++){
                    chars[i] = c;
                    String s2 = new String(chars);
                    // avoid circle
                    // check if s2 is in the dict and only add the string that is nearer to the start
                    if(dict.contains(s2) && (!level.containsKey(s2) || (level.containsKey(s2) && level.get(s2) > level.get(s)))){
                        if(visited.containsKey(s2)){
                            // update the ancestor of s2
                            visited.get(s2).add(s);
                        }else{
                            // the first time we met this node, add it to the queue and its ancestor
                            path = new HashSet<String>();
                            path.add(s);
                            visited.put(s2, path);
                            level.put(s2, level.get(s) + 1);
                            queue.add(s2);
                        }
                    }
                    if(s2.equals(end)){
                        // got a solution, use back trace to get the path to start
                        if(level.get(s) < minLength){
                            // this is the shortest path
                            ArrayList<String> entries = new ArrayList<String>();
                            entries.add(end);
                            result.addAll(backTrace(s, visited, entries));
                            minLength = level.get(s) + 1;
                        }else{
                            // all the remaining path should be longer
                            break;
                        }
                    }
                }
                chars[i] = old;
            }
        }
        return result;
    }
    
    /*
     * find path using DFS
     */
    private static ArrayList<ArrayList<String>> backTrace(String end, HashMap<String, HashSet<String>> visited, ArrayList<String> path){
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> entries = new ArrayList<String>(path);
        entries.add(0, end);
        if(visited.get(end).size() < 1){
            result.add(entries);
            return result;
        }
        for(String str: visited.get(end)){
            result.addAll(backTrace(str, visited, entries));
        }
        return result;
    }
}
