import common.MakeTree;
import common.TreeNode;

import java.util.ArrayList;
/**
 * @author LemonLin
 * @Description :GetLastCommonParent
 * @date 2018/9/20-10:55
 *题目：树中两个结点的最低公共祖先
 * 解法一：假设是二叉搜索树（二叉搜索树是一个排序的二叉树，左子树的结点小于根结点，右子
 * 树的结点大于根节点），故找到一个节点，使其大于左子节点小于右子结点即可。
 * 程序思路有三部分:
 *      一、考虑异常输入
 *      二、考虑正确的输入结果
 *      三、考虑向左递归还是向右递归
 * 解法二：假设是普通的树，但是每个子结点都有指向父结点的指针，这样的话类似与
 * 前面的链表找公共结点一样。(这里要从下往上看，两个结点看做是链表的开始结点，
 * 根节点看成尾节点)
 * (总体思路：1、首先要知道链表是单向链表。如果两个单向链表有公共的结点，那么两个链表
 * 从某一个结点开始，它们的next都指向同一个结点。
 * 2、先求出两个链表的长度，进而求出两个链表的长度差，然后先遍历长的链表长度差，然后同
 * 时出发，当遍历了同一个结点的时候，就是公共结点）
 *解法三：假设是普通的树
 *   思路：分两步，
 *      一、分别记录根结点到两个目标结点的路径;
 *              利用的是图的深度优先遍历的简化版来记录根结点到目标节点的路径
 *      二、然后把题目转换为求两个链表的最后的公共结点;
 *              默认是根结点到叶子节点的情况，两个链表一样长
 */
public class TreeGetLastCommonParent50 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     public int val;
     *     public TreeNode left;
     *     public TreeNode right;
     *     public TreeNode(int x) { val = x; }
     * }
     */
    /**
     *需要用到递归的想法,解法一：假设是二叉搜索树
     */
    public TreeNode lowestCommonAncestor(TreeNode root,
                                                 TreeNode p,TreeNode q){
        TreeNode result=null;
        if (root == null){
            result =root;
        }
        //处理输入:
        //[2,1]
        //2
        //1
        //输出
        //null
        //预期结果
        //2
        TreeNode smallNode = p.val > q.val ? q : p;
        TreeNode bigNode = p.val > q.val ? p : q;
        if (root.val ==smallNode.val||root.val==bigNode.val||
                root.val > smallNode.val && root.val < bigNode.val){
             result=root;
        }
        if (root.val >bigNode.val){
          result =  lowestCommonAncestor(root.left,smallNode,bigNode);
        }
        if (root.val <smallNode.val){
           result= lowestCommonAncestor(root.right,smallNode,bigNode);
        }
        return result;
    }
    //解法三：假设是普通的树,且没有指向父节点的指针
    public  TreeNode GetLastCommonParentThree(TreeNode root ,
                                                  TreeNode node1 ,TreeNode node2){
        ArrayList path1 = new ArrayList();
        ArrayList path2 = new ArrayList();
        ArrayList temp = new ArrayList();
        getNodePath(root,node1,temp,path1);
        getNodePath(root,node2,temp,path2);
        //如果路径不存在，返回空
        if (path1.size()==0 || path2.size() ==0){
            return null;
        }
        return getLastCommonParent(path1,path2);
    }
    //获取路径的方法，有点类似深度优先
    //获取路径的方法，有点难，不好理解。
   public  void  getNodePath(TreeNode root, TreeNode targetNode,
                             ArrayList tempList ,ArrayList path){
        if (root == targetNode || root == null){
            return ;
        }
        tempList.add(root);
       ArrayList<TreeNode> childs = new ArrayList<>();
        if (root.left != null){
           childs.add(root.left);
        }
       if (root.right!= null){
           childs.add(root.right);
       }
        for (TreeNode node :childs ){
            //注意这里用的是比较val,不能直接比较结点，否则会包括比较结点下面的子结点，导致无法进入if
            if (node.val == targetNode.val){
                path.addAll(tempList);
                break;
            }
            getNodePath(node,targetNode,tempList,path);
        }
        //不包括目标节点，把目标节点排除
        tempList.remove(tempList.size()-1);
   }
   //获取两个路径的最后一个公共结点，重点是最后两个字，
    //1-2-4-5 与1-2-3-6，那么获得了2
   public  TreeNode getLastCommonParent(ArrayList<TreeNode> path1,
                                        ArrayList<TreeNode> path2){
       TreeNode treeNode  = null;
        for (int i=0;i<path1.size();i++){
            if (path1.get(i)!=path2.get(i)){
                break;
            }
            treeNode = path1.get(i);
        }
        return treeNode;
   }

    public static void main(String[] args) {
        MakeTree makeTree = new MakeTree();
        TreeNode pRoot = makeTree.makeTree2();
        TreeNode p = new TreeNode(6);
        TreeNode q = new TreeNode(7);
        TreeGetLastCommonParent50 treeGetLastCommonParent50 = new TreeGetLastCommonParent50();
        TreeNode lastCommonParent = treeGetLastCommonParent50.lowestCommonAncestor(pRoot,p,q);
        System.out.println(lastCommonParent.val);
//        TreeNode lastCommonParent = treeGetLastCommonParent50.GetLastCommonParentThree(pRoot
//        ,p,q);
//        System.out.println(lastCommonParent.val);
    }
}
