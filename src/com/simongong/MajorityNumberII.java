package com.simongong;

/*
Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.

Find it.

Note
There is only one majority number in the array

Example
For [1, 2, 1, 2, 1, 3, 3] return 1

Challenge
O(n) time and O(1) space

思路：
与一半的数处理思路一样，就是对杀，想办法消除非majority元素。
这里是1/3的比例，因此每次要对杀掉3个元素，这样最后1/3部分的元素才能有残留。
用两个变量保存当前非相同元素，num1, num2以及它们的计数。对于下一个元素num
1. 若num != num1 && num != num2，则count1--, count2--
2. 若count1 == 0 || count2 == 0，用下一个元素替换
3. 最后看哪个count还有残留，对应的number就是要求的majority

 */
public class MajorityNumberII {

    public int majority(int[] data){
        if(data == null || data.length <= 2){
            return -1;
        }
        
        int candidate1 = data[0];
        int count1 = 1;
        int i = 1;
        while(data[i] == candidate1){
            count1++;
            i++;
        }
        int candidate2 = data[i++];
        int count2 = 1;
        while(i++ < data.length){
            if(count1 == 0){
                candidate1 = data[i];
                count1 = 1;
                break;
            }
            if(count2 == 0){
                candidate2 = data[i];
                count2 = 1;
                break;
            }
            if(data[i] == candidate1){
                count1++;
            }else if(data[i] == candidate2){
                count2++;
            }else{
                count1--;
                count2--;
            }
        }
        if(count1 > count2){
            return candidate1;
        }else{
            return candidate2;
        }
    }
}
