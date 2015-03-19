package com.simongong.tree;

import com.simongong.extendedtype.TreeNode;

/*
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3

思路：
类似于寻找连续最大和的子数组问题，这里是找path。
注意，这个path不是一定从root出发，有可能是穿过root的。

因此，递归思路就是对于当前节点root：
取左右节点的最大和(maxLeft, maxRight) + root.val，再和root.val比较，
取三者的最大值： {maxLeft, maxRight, maxRoot}，作为当前节点作为根节点的pathSum的最大值。

根据最开始的分析，对于以root为根节点的一颗树，pathSum不是一定从root出发，有可能是穿过root的。
因此我们要对max{maxLeft, maxRight, maxRoot}和left+root+right取最大值，作为这棵树的pathSum最大值，
然后更新全局的max。

注意不能拿max{maxLeft, maxRight, maxRoot}和left+root+right的最大值作为当前节点的pathSum最大值，
因为最终我们需要的是“某一条路径下”的最大值，因此只能是经过当前节点往左节点走或往右节点走。

对于最大值的更新，不需要保存所有节点的maxSum，因为每次计算当前节点的时候，只需要其lef/right子节点的最大值即可，因此只需1个全局变量就可以了。
实现上，由于JAVA中primitive type的参数是copy value，没有全局变量的性质，因此使用大小为1的数组来保存max，这样就可以在dfs的时候更新同一份max。

 */
public class BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root){
        int[] max = new int[1]; // use an array as parameter of dfs so that the update on it can be effective globally
        max[0] = Integer.MIN_VALUE; // initialized with min value
        dfs(root, max);
        return max[0];
    }
    
    private int dfs(TreeNode root, int[] max){
        if(root == null){
            return 0;
        }
        
        int maxLeft = dfs(root.left, max);
        int maxRight = dfs(root.right, max);
        
        // NOTE that maxThisLevel means the level of a node in the final path with max sum
        // from this node, we can only go either left or right to consist the final path.
        // On the other hand, Math.max(maxThisLevel, root.val + maxLeft + maxRight) means the maxPathSum of a tree with root of this node
        int maxThisLevel = Math.max(root.val, Math.max(root.val + maxLeft, root.val + maxRight));
        max[0] = Math.max(max[0], Math.max(maxThisLevel, root.val + maxLeft + maxRight));

        return maxThisLevel;
    }
}
