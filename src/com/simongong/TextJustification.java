package com.simongong;

import java.util.ArrayList;
import java.util.List;

/*
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. 
Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. 
If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

思路：
主要就是两步：断行，行内对其。
因为每行之间没有关联，所以可以使用递归来处理。

注意行内的处理比较繁琐，要注意区分空格的插入条件，缕清楚了就好。
 */
public class TextJustification {

    public static void main(String[] args) {
        String[] testData = {"This", "is", "an", "example", "of", "text", "justification."};
        int width = 16;
        Utils.printList("Justified text:", justify(testData, width), "\n");
    }

    public static List<String> justify(String[] data, int width){
        List<String> result = new ArrayList<String>();
        if (data == null || data.length == 0) {
            return result;
        }
        justifyLine(data, width, 0, result);
        return result;
    }
    
    public static void justifyLine(String[] data, int width, int index, List<String> result){
        if(index >= data.length){
            return;
        }
        int widthCount = width;
        int end = index;    // index of the last candidate for this line
        for(int i = index; i < data.length && data[i].length() <= widthCount; i++){
            widthCount -= data[i].length();
            widthCount--;   // this if for the space following the word
            end = i;
        }
        widthCount++;   // remove the last space
        
        int wordsCount = end - index + 1;
        int extraSpace = 0, firstExtra = 0;
        if (wordsCount > 1) {   // only need to calculate between-word-spaces when there are more than 1 word
            extraSpace = widthCount / (wordsCount - 1); // number of spaces which will be evenly allocated between words
            firstExtra = widthCount % (wordsCount - 1); // number of spaces which will only be allocated after the first word
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = index; i <= end; i++) {
            sb.append(data[i]);
            
            if (i != end) { // first, insert a space after each word
                sb.append(' ');
            }
            // insert extra spaces if needed
            if (end != data.length - 1) {   // this line is not the last line
                if (firstExtra > 0) {
                    sb.append(' ');
                    firstExtra--;
                }
                if(i != end){   // add extra space after each word for non-last-word
                    int extraSpaceCopy = extraSpace;
                    while(extraSpaceCopy > 0){
                        sb.append(' ');
                        extraSpaceCopy--;
                    }
                }
            }
        }
        
        int tailLen = width - sb.length();  // spaces at tail for the last line
        while(tailLen > 0){
            sb.append(' ');
            tailLen--;
        }
        result.add(sb.toString());
        justifyLine(data, width, end + 1, result);
    }
}
