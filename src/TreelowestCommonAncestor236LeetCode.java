import common.TreeNode;

import java.util.ArrayList;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表
 * 示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它
 * 自己的祖先）。”
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * 图片：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以
 * 为节点本身。
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 * @author LemonLin
 * @Description :TreelowestCommonAncestor236LeetCode
 * @date 20.2.12-15:58
 * 思路：递归感觉很难想通。先记住吧
 *参考：
 *https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * solution/java-dfs-jian-zhi-9ms9244-by-lava-4/
 * 以及：
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-jian-j/
 * lowestCommonAncestor2：假设是普通的树
 *    思路：分两步，
 *       一、分别记录根结点到两个目标结点的路径;
 *               利用的是图的深度优先遍历的简化版来记录根结点到目标节点的路径
 *       二、然后把题目转换为求两个链表的最后的公共结点;
 *               默认是根结点到叶子节点的情况，两个链表一样长
 */
public class TreelowestCommonAncestor236LeetCode {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null||root.val ==p.val||root.val==q.val){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if (left!=null&&right!=null){
            return root;
        }
        if (left==null){
            return right;
        }else {
            return left;
        }
    }

    // bug1:
    // 输入：[1,2]
    //2
    //1
    public  TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList path1 = new ArrayList();
        ArrayList path2 = new ArrayList();
        ArrayList temp = new ArrayList();
        getNodePath(root,p,temp,path1);
        //因为temp是列表，相当于全局变量，所以需要重新赋值新的列表
        temp = new ArrayList();
        getNodePath(root,q,temp,path2);
        //如果路径不存在，返回空
        if (path1.size()==0 || path2.size() ==0){
            return null;
        }
        return getLastCommonParent(path1,path2);
    }

    //这里用到||的短路特性，找到目标节点之后立马退出循环。如果用
    // TreeGetLastCommonParent50的getNodePath2写法要找到了之后还要遍历。
    // 要遍历全部
    public  boolean  getNodePath(TreeNode root, TreeNode targetNode,
                               ArrayList tempList ,ArrayList path){
        if ( root == null){
            return false;
        }
        tempList.add(root);
        if(root.val == targetNode.val){
            //list 复制。把tempList的内容复制给path
            path.addAll(tempList);
            return true;
        }
        if(getNodePath(root.left,targetNode,tempList,path)||getNodePath(root.right,targetNode,tempList,path)){
            return true;
        }
        //不包括目标节点，把目标节点排除
        tempList.remove(tempList.size()-1);
        return false;
    }
    //获取两个路径的最后一个公共结点，重点是最后两个字，
    //1-2-4-5 与1-2-3-6，那么获得了2
    public  TreeNode getLastCommonParent(ArrayList<TreeNode> path1,
                                         ArrayList<TreeNode> path2){
        //解决bug1，不能确认哪个长，所有需要取最小的。否则数组越界
        int length = Math.min(path1.size(),path2.size());
        TreeNode treeNode  = null;
        for (int i=0;i<length;i++){
            if (path1.get(i)!=path2.get(i)){
                break;
            }
            treeNode = path1.get(i);
        }
        return treeNode;
    }

    public static void main(String[] args) {
        /**
         *                  8
         *             6       10
         *          null   7    9    11
         */
        TreeNode treeNodeCommon1 = new TreeNode(8);
        TreeNode treeNodeCommon2 = new TreeNode(6);
        TreeNode treeNodeCommon3 = new TreeNode(10);
        TreeNode treeNodeCommon5 = new TreeNode(7);
        TreeNode treeNodeCommon6 = new TreeNode(9);
        TreeNode treeNodeCommon7 = new TreeNode(11);
        treeNodeCommon1.left = treeNodeCommon2;
        treeNodeCommon1.right = treeNodeCommon3;
        treeNodeCommon2.left = null;
        treeNodeCommon2.right = treeNodeCommon5;

        treeNodeCommon3.left = treeNodeCommon6;
        treeNodeCommon3.right = treeNodeCommon7;

        TreeNode p = new TreeNode(6);
        TreeNode q = new TreeNode(7);
        TreeNode treeNode = new TreelowestCommonAncestor236LeetCode().
                lowestCommonAncestor2(treeNodeCommon1, p, q);
        System.out.println(treeNode.val);
    }
}
