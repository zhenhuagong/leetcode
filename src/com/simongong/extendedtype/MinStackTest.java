package com.simongong.extendedtype;

import java.util.Stack;

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

思路：
2个栈。举例：
数据栈：3, 4, 6, 2, 8, 1, 5, 1
MIN栈： 3, 2, 1, 1
 */
public class MinStackTest {
    
}

class MinStack {
    Stack<Integer> data = new Stack<Integer>();
    Stack<Integer> min = new Stack<Integer>();
    
    public void push(int elem){
        data.push(elem);
        if(min.isEmpty() || elem <= min.peek()){    // push into min only if a equalizer or smaller one comes up
            min.push(elem);
        }
    }
    
    public void pop(){
        data.pop();
        if(data.peek().equals(min.peek())){ // pop min stack only if the equalizer in data stack pops
            min.pop();
        }
    }
    
    public int top(){
        return data.peek();
    }
    
    public int getMin(){
        return min.peek();
    }
}
