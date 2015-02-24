package com.simongong;

/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false

思路：
逐字符匹配，用两个指针扫一遍。
1. 指针i, j分别指向字符串、匹配公式。
2. 如果匹配（单个字符或?），直接2个指针一起前进。
3. 如果匹配公式是*，则一直j++，直到匹配i++。
4. 先处理完字符串，再看匹配公式是否剩余

注意：
对于*和?要做探索式匹配，因此要保留上一次开始匹配的位置。比如：
    i i i i
a b c d e f g h
a b * f g e
      j
i和j就是我们要保留的位置。

 */
public class WildcardMatching {

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a") == false);
        System.out.println(isMatch("aa","aa") == true);
        System.out.println(isMatch("aaa","aa") == false);
        System.out.println(isMatch("aa", "*") == true);
        System.out.println(isMatch("aa", "a*") == true);
        System.out.println(isMatch("ab", "?*") == true);
        System.out.println(isMatch("aab", "c*a*b") == false);
        System.out.println(isMatch("hi", "*?") == true);
        System.out.println(isMatch("abefcdgiescdfimde", "ab*cd?i*de") == true);
    }

    public static boolean isMatch(String s, String p){
        if(s == null || p == null){
            return false;
        }
        int dataLen = s.length(), regexLen = p.length();
        int iData = 0, iRegex = 0, preData = 0, preRegex = 0;
        boolean lastAster = false;
        // match all the characters in data
        while(iData < dataLen){
            if(iRegex < regexLen && matchChar(s.charAt(iData), p.charAt(iRegex))){  // character match
                    iData++;
                    iRegex++;
            }else if(iRegex < regexLen && p.charAt(iRegex) == '*'){  // wildcard match
                while(iRegex < regexLen && p.charAt(iRegex) == '*'){    // find next non-* character
                    iRegex++;
                }
                if(iRegex == regexLen){ // return true if regex ends with *
                    return true;
                }
                lastAster = true;
                preData = iData;
                preRegex = iRegex;
            }else if(lastAster){    // restore the starting position
                    iData = ++preData;
                    iRegex = preRegex;
            }else{
                return false;
            }
        }
        // check the remainings in regex, ignore the left *
        while(iRegex < regexLen && p.charAt(iRegex) == '*'){
            iRegex++;
        }
        if(iData == dataLen && iRegex == regexLen){ // return true only if both data and regex reach the end without break
            return true;
        }
        return false;
    }
    
    public static boolean matchChar(char data, char regex){
        return (regex == '?' || data == regex);
    }
}
