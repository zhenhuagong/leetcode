package com.simongong.sort;

import java.util.ArrayList;
import java.util.Iterator;

/*
思路：
1. 设置一个定量的数组当作空桶。
2. 寻访序列，并且把元素一个一个放到对应的桶里去。
3. 对每个不是空的桶进行排序。
4. 从不是空的桶里把元素再依次放回原来的序列中。

可以看出，桶排序的特点：
1. 不是基于比较的，因此稳定，而且时间复杂度是线性的
2. 适用于数据在已知范围之内的，这样才好分配桶
3. 会消耗额外的空间
 */
public class BucketSort {

    public static void main(String[] args){
        int[] a = {1,4,8,3,2,9,5,0,7,6,9,10,9,13,14,15,11,12,17,16};
        BucketSort sorter = new BucketSort();
        sorter.sortSimple(a, 0, a.length, 20);
        
        for (int i = 0; i < a.length; i++) {
            if (i != a.length - 1) {
                System.out.print(a[i] + ", ");
            }else {
                System.out.print(a[i]);
            }
        }
    }
    
    public void sort(double[] arr){
        ArrayList<Double> lists[] = new ArrayList[arr.length];
        
        // reflect arr[i] into [0, arr.length)
        for (int i = 0; i < arr.length; i++) {
            int tmp = (int)Math.floor(arr.length * arr[i]);
            if (lists[tmp] == null) {
                lists[tmp] = new ArrayList<>();
                lists[tmp].add(arr[i]);
            }
        }
        
        // sort each bucket with some way
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                insertSort(lists[i]);
            }
        }
        
        // merge all the bucket
        int count = 0;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                Iterator<Double> iter = lists[i].iterator();
                while(iter.hasNext()){
                    arr[count++] = iter.next();
                }
            }
        }
    }
    
    private <E> void insertSort(ArrayList<E> list){
        
    }
    
    public void sortSimple(int[] keys, int from, int len, int max){
        int[] count = new int[max];
        int[] tmp = new int[len];
        
        // count keys[from...from+len] in their buckets
        for (int i = 0; i < len; i++) {
            count[keys[from + i]]++;
        }
        
        //calculate the position for each bucket, which will be reflected as index in keys
        for (int i = 1; i < max; i++) {
            count[i] = count[i] + count[i-1];
        }
        
        // back up keys[from...from+len]
        System.arraycopy(keys, from, tmp, 0, len);
        
        for (int i = len - 1; i >= 0; i--) {
            keys[--count[tmp[i]]] = tmp[i];
        }
    }
}
