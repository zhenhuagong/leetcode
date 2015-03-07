package com.simongong;

import java.util.Stack;

/*
Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

思路：
计算逆波兰式的值。
用一个数字栈，扫描逆波兰式：
碰到数字就压栈
碰到运算符，就弹出两个数字计算结果并压栈

注意：运算符的优先级问题
逆波兰式本身就是考虑了运算符的优先级问题的，从示例中也可看出，翻译出的表达式是自带括号的，所以在计算它的时候无需考虑
 */
public class EvaluateReversePolishNotation {

    public static void main(String[] args) {
        String[] testData1 = {"2", "1", "+", "3", "*"};
        System.out.println(evaluate(testData1) == 9);
        String[] testData2 = {"4", "13", "5", "/", "+"};
        System.out.println(evaluate(testData2) == 6);
    }

    public static int evaluate(String[] notation){
        if(notation == null || notation.length == 0){
            return 0;
        }
        Stack<Integer> operants = new Stack<>();
        for (int i = 0; i < notation.length; i++) {
            String cur = notation[i];
            if (cur.equals("+") || cur.equals("-") || cur.equals("*") || cur.equals("/")) {
                // pop two operants
                int operant1 = operants.pop();
                int operant2 = operants.pop();
                switch (cur.charAt(0)) {
                    case '+':
                        operants.push(operant1 + operant2);
                        break;
                    case '-':
                        operants.push(operant2 - operant1);
                        break;
                    case '*':
                        operants.push(operant1 * operant2);
                        break;
                    case '/':
                        operants.push(operant2 / operant1);
                        break;
                    default:
                        break;
                }
            }else{
                operants.push(Integer.parseInt(cur));
            }
        }
        if (operants.isEmpty()) {
            return 0;
        }
        return operants.pop();
    }
}
