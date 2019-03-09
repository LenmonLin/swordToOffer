import common.TreeNodeCommon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/**
 public class TreeNode {
 int val = 0;
 TreeNode left = null;
 TreeNode right = null;

 public TreeNode(int val) {
 this.val = val;

 }

 }
 */

/**
 * 题目描述
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印
 *
 *层次遍历和前序遍历的代码总体模块有点像，那为什么层次遍历的代码可以读取左结点之后再读取右结点，而不是
 * 像前序遍历那样读取左结点的左子树结点，主要原因是因为层次遍历的
 * if(pNode->m_pLeft)
 *  dequeTreeNode.push_back(pNode->m_pLeft)
 *  是队列进队不是前序遍历的递归函数。
 *
 *  第二点要先处理根结点要先进队才能有出队打印出第一个结点
 *
 *  JAVA中队列的使用;Queue
 *
 * @author LemonLin
 * @Description :PrintFromTopToBottom23
 * @date 2018/3/22-15:58
 */
public class TreePrintFromTopToBottom23 {

    public ArrayList<Integer> PrintFromTopToBottom(TreeNodeCommon root) {

        if (root == null){
//            return null;
            return new ArrayList<>();
        }
        Queue<TreeNodeCommon> queue = new LinkedList<TreeNodeCommon>();
        queue.add(root);


        //牛客网的返回类型，所以需要把队列中的数据添加进去
        ArrayList arrayList = new ArrayList();
        arrayList.add(root.data);

        while (queue.size()!=0){
            TreeNodeCommon treeNodeCommon = queue.remove();

            if (treeNodeCommon.left!=null){

                queue.add(treeNodeCommon.left);
                arrayList.add(treeNodeCommon.left.data);
            }
            if (treeNodeCommon.right != null){

                queue.add(treeNodeCommon.right);
                arrayList.add(treeNodeCommon.right.data);
            }
        }
        return arrayList;
    }

    //测试代码
    public static void main(String[] args) {

        TreeNodeCommon treeNodeCommon1 = new TreeNodeCommon(8);
        TreeNodeCommon treeNodeCommon2 = new TreeNodeCommon(6);
        TreeNodeCommon treeNodeCommon3 = new TreeNodeCommon(10);
        TreeNodeCommon treeNodeCommon4 = new TreeNodeCommon(5);
        TreeNodeCommon treeNodeCommon5 = new TreeNodeCommon(7);
        TreeNodeCommon treeNodeCommon6 = new TreeNodeCommon(9);
        TreeNodeCommon treeNodeCommon7 = new TreeNodeCommon(11);

        treeNodeCommon1.left = treeNodeCommon2;
        treeNodeCommon1.right = treeNodeCommon3;
        treeNodeCommon2.left = treeNodeCommon4;
        treeNodeCommon2.right = treeNodeCommon5;

        treeNodeCommon3.left = treeNodeCommon6;
        treeNodeCommon3.right = treeNodeCommon7;
        TreePrintFromTopToBottom23 treePrintFromTopToBottom23 = new TreePrintFromTopToBottom23();

        ArrayList<Integer> arrayList = treePrintFromTopToBottom23.PrintFromTopToBottom(treeNodeCommon1);


        System.out.println("从上到下存入结点" + arrayList);
    }
}
