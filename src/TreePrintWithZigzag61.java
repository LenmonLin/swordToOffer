import common.MakeTree;
import common.TreeNodeCommon;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author LemonLin
 * @Description :TreePrintWithZigzag61
 * @date 2019/3/9-19:45
 *
 * 题目描述
 * 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺
 * 序打印，第三行按照从左到右的顺序打印，其他行以此类推。
 *
 * 解题思路：
 *
 * 在层次遍历的基础之上，把原本使用队列的情况用两个堆栈来替代，同时设置一个变量来计算层数，
 * 如果打印的层数是奇数层，先保存左子节点再保存右子节点
 * 如果打印的层数是偶数层，先保存右子节点再保存左子节点
 */
public class TreePrintWithZigzag61 {
    public ArrayList<ArrayList<Integer>> Print(TreeNodeCommon pRoot) {

        int layer =1;
        //s1保存奇数层的节点
        Stack<TreeNodeCommon> stack1 = new Stack<>();
        //s2保存偶数层的节点
        Stack<TreeNodeCommon> stack2 = new Stack<>();

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        stack1.add(pRoot);
        while (stack1.size()!=0 ||stack2.size()!=0){

            if (layer%2!=0){
                ArrayList<Integer> temp = new ArrayList<>();
                while (stack1.size()!=0){
                    TreeNodeCommon pop = stack1.pop();
                    if (pop!= null){
                        temp.add(pop.data);
                        System.out.println(pop.data+"  ");
                        stack2.push(pop.left);
                        stack2.push(pop.right);
                    }
                }
                if (temp.size()!=0){
                    result.add(temp);
                    layer++;
                    System.out.println();
                }
            }else {
                ArrayList<Integer> temp = new ArrayList<>();
                while (stack2.size()!=0){
                    TreeNodeCommon pop = stack2.pop();
                    if (pop!= null){
                        temp.add(pop.data);
                        System.out.println(pop.data+"  ");
                        stack1.push(pop.right);
                        stack1.push(pop.left);
                    }
                }
                if (temp.size()!=0){
                    result.add(temp);
                    layer++;
                    System.out.println();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreePrintWithZigzag61 treePrintWithZigzag61 = new TreePrintWithZigzag61();
        MakeTree makeTree = new MakeTree();
        TreeNodeCommon pNode = makeTree.makeTree();
        treePrintWithZigzag61.Print(pNode);
    }
}
