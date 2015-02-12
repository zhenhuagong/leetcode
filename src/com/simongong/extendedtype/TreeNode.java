package com.simongong.extendedtype;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
    }

    public String toString() {
        return Integer.toBinaryString(this.val);
    }
}
