package com.simongong.extendedtype;

public class DTreeNode {
    public int val;
    public DTreeNode left;
    public DTreeNode right;
    public DTreeNode parent;

    public DTreeNode(int x) {
        this.val = x;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public String toString() {
        return Integer.toBinaryString(this.val);
    }
}
