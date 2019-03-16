import common.TreeNodeCommon;
/**
 * 题目描述
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * 解题思路：
 * 先前序遍历这棵树的每个结点，如果遍历到的结点有子结点，就交换它的两个子节点，
 * 当交换完所有的非叶子结点的左右子结点之后，就得到了树的镜像。（主要就是设第三变量
 * 交换左右结点的问题）
 *
 * 考虑求镜像的退出条件，结点的左右子树都是空的情况
 * @author LemonLin
 * @Description :MirrorOfBinaryTree19
 * @date 2018/2/25-20:38
 */
public class TreeMirrorOfBinaryTree19 {
    public void Mirror(TreeNodeCommon root) {
        if (root ==null)
            return;
        if (root.left==null&&root.right ==null){
            return;
        }
        TreeNodeCommon temp =null;
        temp = root.left;
        root.left =root.right;
        root.right =temp;
        if (root.left!=null){
            Mirror(root.left);
        }
        if (root.right != null){
            Mirror(root.right);
        }
    }
}
