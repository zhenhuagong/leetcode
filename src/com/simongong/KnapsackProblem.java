package com.simongong;

import java.util.ArrayList;
import java.util.List;

/*
 * 背包问题（Knapsackproblem）
 * 给定一组物品，每种物品都有自己的重量和价格，在限定的总重量内，我们如何选择，才能使得物品的总价格最高。
 * 
 * 我们用子问题定义状态来描述的话可以这样解释：
 * 用f[i][v]表示前i件物品恰放入一个容量为v的背包可以获得的最大价值。用公式表示:
 * f[i][v]=max{f[i-1][v],f[i-1][v-c[i]]+w[i]}
 * * * v表示背包的最大容量，c[i]表示第i件物品的大小，w[i]表示第i件物品的价值
 * 只考虑第i件物品的策略（放或不放），那么就可以转化为一个只涉及前i-1件物品和第i件物品的问题。如果不放第i件物品，那么问题就转化为“前i-1件物品放入容量为v的背包中”，价值为f[i-1][v]；如果放第i件物品，那么问题就转化为“前i-1件物品放入剩下的容量为v-c[i]的背包中”，此时能获得的最大价值就是f[i-1][v-c[i]]再加上通过放入第i件物品获得的价值w[i]
 * v表示背包的最大容量，c[i]表示第i件物品的大小，w[i]表示第i件物品的价值
 * 
 */
public class KnapsackProblem {
    private static final int MAX = 8;
    private static final int MIN = 1;

    public static void main(String[] args){
        int[] item = new int[MAX + 1];
        int[] value = new int[MAX + 1];
        List<PackItem> items = new ArrayList<PackItem>();
        items.add(new PackItem(4, 4500));
        items.add(new PackItem(5, 4700));
        items.add(new PackItem(2, 2250));
        items.add(new PackItem(1, 1100));
        items.add(new PackItem(6, 3700));
        items.add(new PackItem(2, 4900));
        items.add(new PackItem(3, 5800));
        
        for(int i = 0; i < MAX + 1; i++){
            item[i] = 0;
            value[i] = 0;
        }
        for(int i = 0; i < 7; i++){
            for(int s = items.size(); s <= MAX; s++){
                // s is the current size of pack
                int p = s - items.get(i).getSize(); // left size after adding ith item
                int newValue = value[p] + items.get(i).getPrice();  // value[p] means possible value the added space could bring
                if(newValue > value[s]){    // current total value is bigger than as size is s
                    value[s] = newValue;
                    item[s] = i;    // add i into pack
                }
            }
        }
        
        System.out.println("item\tprice");
        for(int i = MAX; i > MIN; i = i - items.get(item[i]).getSize()){
            System.out.println(i + "\t" + items.get(item[i]).getPrice());
        }
        System.out.println("Total: \t" + value[MAX]);
    }
}

class PackItem{
    private int size, price;
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public PackItem(int size, int price) {
        super();
        this.size = size;
        this.price = price;
    }
}
