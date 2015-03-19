package com.simongong.extendedtype;

import java.util.Stack;

/*
原题链接 ： http://lintcode.com/zh-cn/problem/implement-queue-by-stacks/#

As the title described, you should only use two stacks to implement a queue's actions.

The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

Both pop and top methods should return the value of first element.

样例
For push(1), pop(), push(2), push(3), top(), pop(), you should return 1, 2 and 2

挑战
implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.

思路：
两个栈A，B。现在来模拟：
1. queue.add()
对A压栈；
若A已满，则不断弹出A栈顶并压进B栈，直到A空（这里实现的时候不考虑对这种场景的处理）
2. queue.pop()
若B不空，弹出B栈顶；
否则，不断弹出A栈顶并压进B栈，直到A空

注意：
可以看出，最多情况下，A+B的可容纳元素个数等于queue的可容纳元素个数
 */
public class QueueByStack {

    private Stack<Integer> in;
    private Stack<Integer> out;
    
    public QueueByStack(){
        this.in = new Stack<Integer>();
        this.out = new Stack<Integer>();
    }
    
    public void add(int value){
        this.in.push(value);
    }
    
    public int pop(){
        if(this.out.isEmpty()){
            while(!this.in.isEmpty()){
                this.out.push(this.in.pop());
            }
        }
        return this.out.pop();
    }
    
    public int top(){
        if(this.out.isEmpty()){
            while(!this.in.isEmpty()){
                this.out.push(this.in.pop());
            }
        }
        return this.out.peek();
    }
}
