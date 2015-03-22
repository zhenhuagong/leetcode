package com.simongong;

import java.util.HashSet;

/*
Given a string, find the length of the longest substring without repeating characters.
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
For "bbbbb" the longest substring is "b", with the length of 1.

思路：
字符串的字串处理，基本思路是：brute force -> 利用已经扫过的字符做DP优化。
brute force的处理，外层逐字符扫字串O(n)，字串还得不断尝试新长度O(n)，对每个字串还得判断重复O(n)。这三个操作是嵌套的，因此是O(n^3)的复杂度
来看如何优化。
首先外层逐字符扫字串O(n)是跑不了，这层忽略。
字串还得不断尝试新长度O(n)也好像跑不了，这层也忽略。
对每个子串还得判断重复O(n)，由于这里是判断重复字符，对于“唯一字符串”，我们可以空间换时间，使用hashset来判断重复。
这样做下来，复杂度为O(n^2)。

上面的这个优化，本质上还是逐次扫描的思路，并没有“利用已经扫过的字符”的信息。
根据KMP算法我们知道，对于包含重复字符的字符串，是可以利用已经扫过的字符来得到下一次扫描起点的，能把O(n^2)的二重扫描优化到O(n)的线性扫描。
这类字符串问题的常见处理方式，就是维护一个滑动窗口，窗口中的元素是不重复元素。
在扫描字符串的过程中，左窗口和右窗口选择其一向右滑动。扫描时默认操作是右窗口后移一位。
选择移动哪个窗口的条件，就是看下一个扫描元素cur是否在窗口包含的元素集合中。如果在，则说明移动右窗口会导致窗口违反不重复规则。因此这时候要移动左窗口，一直到窗口中不包含cur为止。
中间跳过的这些串中不会有更好的结果，因为他们不是重复就是更短。
另外再维护一个max，每次调整左窗口的时候更新max。
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfNonrepeatingSubstring(String str){
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        int max = 0;
        int start = 0, cur = 0, len = str.length();
        HashSet<Character> set = new HashSet<>();
        while (cur < len) {
            if (set.contains(str.charAt(cur))) {
                if (max < cur - start) {    // update max before adjusting window
                    max = cur - start;
                }
                while (start <= cur && str.charAt(start) != str.charAt(cur)) {
                    set.remove(str.charAt(start));
                    start++;
                }
                start++;
            }else {
                set.add(str.charAt(cur));
            }
            cur++;
        }
        return Math.max(max, cur - start);  // last time of updating max
    }
}
