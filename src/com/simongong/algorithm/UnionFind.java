package com.simongong.algorithm;

/*
关于Union-Find算法，参考普林斯顿教授的讲解：
https://www.youtube.com/watch?v=zeF_d5ok_1k&list=PLe-ggMe31CTexoNYnMhbHaWhQ0dvcy43t
http://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf

目的：
解决一个动态图中，A,B两点是否连通的问题

已知条件：
多个集合{a,b,c},{d,e},{f,g,h}，集合中的点表示互相之间可以连通的点。这些集合的状态可以从单个节点的集合合并而来，这个合并过程就是不断地进行Union操作。
比如初始状态：
{a}, {b}, {c}, {d}, {e}, {f}, {g}, {h}
然后根据两点连通的关系，不断合并集合。合并完成之后，到达上面三个集合的状态。
我们需求是，查找某两个点{b,g}是否是连通的，也就是Find操作。

这个算法是不断的Union以更新集合状态，然后进行Find查找某两点是否相连。因此叫做Union-Find算法。

建立模型：
问题分为两个：1. 查找；2. 合并
如何建立并优化这两个操作的模型，请参考上面的链接。

总结就是两点：
1. 因为连接的传递性a->b, b->c，则a->c，使用树结构，把一个连通区域内两点之间的连通性问题，简化为判断所在树的根节点是否相同。（quickFind）
2. 优化查找根节点时间。使每颗树的结构尽量平。（quickUnion -> weighed quickUnion -> compressed weighed quickUnion）
因此产生了如下的算法改进过程：
quickFind -> quickUnion -> weighed quickUnion -> compressed weighed quickUnion
为什么通过以上几个步骤能提高算法效率？
quickFind -> quickUnion，采用树结构来使得每个连通区域变得扁平，减少了平均查找时间
quickUnion -> weighed quickUnion，合并两颗树时，考虑到合并结果的平衡性，使得合并之后的树更平衡
weighed quickUnion -> compressed weighed quickUnion，使得树内的结构更扁平

算法的核心是：
1. 以树结构来存储连通集合
2. 合并连通集合时，考虑到合并树的平衡性
3. 查找某个节点所在树的根节点时，调整其父节点的父节点指向，使得树的结构更平，以求更快的找到根节点
4. 对于(p, q)，只要比较p和q的根节点，就知道其是否相连

扩展，该算法可以被用来解决很多问题：
1. 网络节点的连通性
2. Percolation，一个矩形从顶部某点是否能连通底部某点。（3年前玩过的手机游戏“水管工人”，就是以这个算法为核心的）
3. 图像处理
4. Least Common Ancestor，树中某两个节点的最近公共祖先
等等

这里的实现以一个以为数组来存储树结构，并且各个节点的值为[1,n]范围内的某个整数。
 */
public class UnionFind {
    private int[] ids;
    private int[] size;
    
    public UnionFind(int n){
        ids = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            size[i] = 1;
        }
    }
    
    // Unite p and q in the forest
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if (size[i] > size[j]) {    // i will be the parent of j
            ids[j] = i;
            size[i] += size[j];
        }else {
            ids[i] = j;
            size[j] += size[i];
        }
    }
    
    // find if p and q is connected
    public boolean find(int p, int q){
        return root(p) == root(q);
    }
    
    // find the root of i
    private int root(int i){
        while (i != ids[i]) {
            ids[i] = ids[ids[i]];   // make every other node in path point to its grandparent
            i = ids[i];
        }
        return i;
    }
}
