import common.MakeTree;
import common.TreeNodeCommon;
import java.util.Stack;
/**
 * 中序遍历和先序遍历的非递归算法
 * @author LemonLin
 * @Description :TreeTraversalNotRecursionByLemonLin
 * @date 19.8.21-16:12
 */
public class TreeTraversalNotRecursionByLemonLin {
    //非递归中序遍历
    public  void inorderTraversal(TreeNodeCommon root){
        //用一个栈来保存树的轨迹
        Stack<TreeNodeCommon> stack = new Stack();
        //用一个树节点暂时保存root
        TreeNodeCommon temp = root;
        while (temp!=null||!stack.empty()){
            //左边节点不断进栈
            while (temp!=null){
                stack.push(temp);
                temp = temp.left;
            }
            if (!stack.empty()){
                 temp = stack.pop();
                System.out.println(temp.data);
                temp = temp.right;
            }
        }
    }
    //先序遍历
    public  void preorderTraversal(TreeNodeCommon root){
        //用一个栈来保存树的轨迹
        Stack<TreeNodeCommon> stack = new Stack();
        //用一个树节点暂时保存root
        TreeNodeCommon temp = root;
        while (temp!=null||!stack.empty()){
            //左边节点不断进栈
            while (temp!=null){
                System.out.println(temp.data);
                stack.push(temp);
                temp = temp.left;
            }
            if (!stack.empty()){
                temp = stack.pop();
                temp = temp.right;
            }
        }
    }
    //后序遍历
    public  void PostorderTraversal(TreeNodeCommon root){
        //有点难度还没写
    }
    public static void main(String[] args) {
        /**
         * 新建一颗树      8
         *                  6       10
         *                5   7    9    11
         * 中序遍历结果：56789 10 11
         * 先序遍历结果；8657 10 9 11
         */
        TreeNodeCommon treeNodeCommon = new MakeTree().makeTree();
//        new TreeTraversalNotRecursionByLemonLin().inorderTraversal(treeNodeCommon);
        new TreeTraversalNotRecursionByLemonLin().preorderTraversal(treeNodeCommon);
    }
}
