import common.MakeTree;
import common.TreeNodeCommon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LemonLin
 * @Description :TreePrintMultipleLines60
 * @date 2019/3/9-15:32
 *
 * 题目描述
 * 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
 *
 * 思路：其实就是层次遍历的基础上的改造，需要两个变量：toBePrinted 表示在当前层中还没有打印的结点数，
 * nextLevel 表示下一层的结点的数目。
 */
public class TreePrintMultipleLines60 {

    ArrayList<ArrayList<Integer>> Print(TreeNodeCommon pRoot) {
        if (pRoot == null){
            return new ArrayList<ArrayList<Integer>>();
        }

        ArrayList<ArrayList<Integer>>  result = new ArrayList<ArrayList<Integer>>();
        Queue<TreeNodeCommon>  queue = new LinkedList<TreeNodeCommon>();
        ArrayList temp = new ArrayList();

        int toBePrinted  = 1;
        int nextLevel =0;

        queue.add(pRoot);

        while (queue.size()!=0){

            //顺序很重要，先取出，再添加到临时的数组中去
            TreeNodeCommon remove = queue.remove();
            temp.add(remove.data);
            toBePrinted--;

            if (remove.left != null){
                queue.add(remove.left);
                nextLevel++;
            }
            if (remove.right != null){
                queue.add(remove.right);
                nextLevel++;
            }

            //关键点
            if (toBePrinted ==0){
                result.add(new ArrayList<Integer>(temp));
                temp.clear();
                toBePrinted = nextLevel;
                nextLevel =0;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        TreePrintMultipleLines60 treePrintMultipleLines60 = new TreePrintMultipleLines60();
        MakeTree makeTree = new MakeTree();
        TreeNodeCommon nodeCommon = makeTree.makeTree();
        ArrayList<ArrayList<Integer>> print = treePrintMultipleLines60.Print(nodeCommon);
        

    }
}
