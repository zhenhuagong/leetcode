package com.simongong;

import java.util.ArrayList;
import java.util.List;

/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",
return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

思路：

 */
public class RestoreIPAddresses {

    public static void main(String[] args) {
        String testData = "25525511135";
        Utils.printList("Possible IP Addresses:", restoreIPAddress(testData), ", ");
    }

    public static List<String> restoreIPAddress(String data){
        if(data == null || data.length() < 4 || data.length() > 12){
            return null;
        }
        List<String> result = new ArrayList<String>();
        List<String> path = new ArrayList<String>();
        
        dfs(data, 0, path, result);
        
        return result;
    }

    private static void dfs(String data, int index, List<String> path, List<String> result) {
        if(path.size() == 4){   // A possible IP address comes out
            if(index == data.length()){ // All elements scanned already
                StringBuilder sb = new StringBuilder();
                for(String str : path){
                    sb.append(str);
                    sb.append('.');
                }
                sb.deleteCharAt(sb.length() - 1);
                result.add(sb.toString());
            }
            return; // Finish this path of dfs
        }
        
        int length = data.length();
        for(int i = index; (i < index+3) && i < length; i++){
            if(data.charAt(i) == '0' && i > index){ //
                break;
            }
            String pre = data.substring(index, i + 1);
            if(!isValidIPSegment(pre)){
                continue;
            }
            path.add(pre);
            dfs(data, i + 1, path, result);
            path.remove(path.size() - 1);   // Finish a branch of dfs, remove it
        }
    }
    
    private static boolean isValidIPSegment(String data){
        int num = Integer.parseInt(data);
        return num >= 0 && num <= 255;
    }
}
