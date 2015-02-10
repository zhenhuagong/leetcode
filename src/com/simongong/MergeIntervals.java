package com.simongong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

分析：
1. 输入n个二元对象，输出m个二元对象
2. 由于涉及边界的大小比较问题，因此考虑先排序，后处理

思路：
1. 对n个二元对象intervals排序
2. 扫一遍，定义结果集合results，以及当前的nextResult.start = intervals[i].start, nextResult.startend = result[0].end
3. 比较intervals[k]和intervals[k+i]，取并集
4. 若intervals[i].start > nextResult.startend，完成一个result，并开始下一个
复杂度：
排序是NlogN, 而merge本身是N。所以总体时间复杂度是NlogN
注意：
因为现在Leetcode所有入参都搞成了List,所以遍历时最好使用Iterator，这样无论对于Linkedlist,还是arraylist，性能都是一样的，否则使用get(i)对于LinkedList会相当的缓慢，就不是O(1)的时间了。

 */
public class MergeIntervals {

    public static void main(String[] args) {
        List<Interval> testData = new ArrayList<Interval>();
        testData.add(new Interval(1, 3));
        testData.add(new Interval(2, 6));
        testData.add(new Interval(8, 10));
        testData.add(new Interval(15, 18));
        Utils.printList("Original Intervals", testData, ",");
        
        testData = mergeIntervals(testData);
        Utils.printList("Merged Intervals", testData, ",");
    }

    public static List<Interval> mergeIntervals(List<Interval> intervals){
        List<Interval> results = new ArrayList<Interval>();
        if(intervals == null || intervals.size() == 0){
            return results;
        }
        
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        
        Interval nextResult = intervals.get(0);
        
        Iterator<Interval> iterator = intervals.iterator();
        while(iterator.hasNext()){  // Use iterator of list to ensure O(1) complexity of loop
            Interval current = iterator.next();
            if(current.start > nextResult.end){ // find a new nextResult
                results.add(nextResult);
                nextResult = current;
            }else{  // merge current into nextResult
                nextResult = new Interval(nextResult.start, Math.max(nextResult.end, current.end));
            }
        }
        results.add(nextResult);
        
        return results;
    }    
}

class Interval{
    int start;
    int end;
    Interval(){
        this.start = 0;
        this.end = 0;
    }
    Interval(int start, int end){
        this.start = start;
        this.end = end;
    }
    @Override
    public String toString() {
        return "[" + this.start + "," + this.end + "]";
    }
    
}
