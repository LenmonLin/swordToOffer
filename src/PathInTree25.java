import common.TreeNodeCommon;

import java.util.ArrayList;

/**
 * 基本思路前序遍历外加数组来保存路径
 *
 * 关于把一个arraylist赋值给另外一个arraylist
 * ArrayList<Integer> arrayList1 = new ArrayList<>(arrayList);
 * @author LemonLin
 * @Description :PathInTree25
 * @date 2018/3/22-20:10
 */
public class PathInTree25 {

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

        PathInTree25 pathInTree25 = new PathInTree25();
        ArrayList<ArrayList<Integer>> arrayLists = pathInTree25.FindPath(treeNodeCommon1, 22);
        System.out.println(arrayLists);


    }
}
