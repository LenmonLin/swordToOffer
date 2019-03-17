import common.TreeNodeCommon;

import java.util.ArrayList;
/**
 *  @author LemonLin
 *  @Description :PathInTree25
 *  @date 2018/3/22-20:10
 * 题目描述
 * 输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为
 * 从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。(注意: 在返回值的list中，数组长度
 * 大的数组靠前)
 * 解题思路：
 * 由于路径是从根结点出发到叶节点，也就是说路径总是以根结点为起始点，因此需要遍历根结点。只有
 * 前序遍历是首先访问根节点的。过程中需要一个变量来记录路径和，同时需要一个arraylist当作栈来存
 * 储遍历的路径
 *
 * 当前节点访问结束后，递归函数将自动回到它的父结点，因此我们在函数退出之前要在路径上删除当前结点
 * 并减去当前结点的值，以确保返回父结点时路径刚好是从根结点到父结点的路径
 * 小知识点：
 * 关于把一个arraylist赋值给另外一个arraylist
 * ArrayList<Integer> arrayList1 = new ArrayList<>(arrayList);
 */
public class TreePathInTree25 {

    public ArrayList<ArrayList<Integer>> FindPath(TreeNodeCommon root, int target) {
        if (root == null){
            return new ArrayList<ArrayList<Integer>>();
        }
        int currentSum = 0;
        //用来存储符合要求的所有的路径的数列
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<ArrayList<Integer>>();
        //用来存储每一次得到的符合要求的路径和的数列
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        FindPath(root,target,currentSum,arrayList,arrayLists);
        return arrayLists;
    }
    void FindPath(TreeNodeCommon root, int target,int currentSum,
                                           ArrayList arrayList,ArrayList<ArrayList<Integer>> arrayLists){
        //是否到达了叶子结点，是，就打印出来
        currentSum +=root.data;
        arrayList.add(root.data);
        Boolean isLeaf = (root.left==null && root.right ==null);
        if (currentSum == target && isLeaf){
            //这里必须新建一个arrayList1来存储结果，否则因为传入的arrayList是指针，最后会使
            //双数组为空
            ArrayList<Integer> arrayList1 = new ArrayList<>(arrayList);
            arrayLists.add(arrayList1);
        }
        //否就遍历左右结点
        if (root.left != null){
            FindPath(root.left,target,currentSum,arrayList,arrayLists);
        }
        if (root.right != null){
            FindPath(root.right,target,currentSum,arrayList,arrayLists);
        }
        //返回父结点之前，在路径上删除当前结点
        arrayList.remove(arrayList.size()-1);
    }
    public static void main(String[] args) {
        TreeNodeCommon treeNodeCommon1 = new TreeNodeCommon(10);
        TreeNodeCommon treeNodeCommon2 = new TreeNodeCommon(5);
        TreeNodeCommon treeNodeCommon3 = new TreeNodeCommon(12);
        TreeNodeCommon treeNodeCommon4 = new TreeNodeCommon(4);
        TreeNodeCommon treeNodeCommon5 = new TreeNodeCommon(7);

        treeNodeCommon1.left = treeNodeCommon2;
        treeNodeCommon1.right = treeNodeCommon3;
        treeNodeCommon2.left = treeNodeCommon4;
        treeNodeCommon2.right = treeNodeCommon5;

        TreePathInTree25 treePathInTree25 = new TreePathInTree25();
        ArrayList<ArrayList<Integer>> arrayLists = treePathInTree25.FindPath(treeNodeCommon1, 22);
        System.out.println(arrayLists);
    }
}
