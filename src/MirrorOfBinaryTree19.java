import common.TreeNodeCommon;

/**
 * 考虑求镜像的退出条件，结点的左右子树都是空的情况
 * 之后就是交换指针的问题
 * @author LemonLin
 * @Description :MirrorOfBinaryTree19
 * @date 2018/2/25-20:38
 */
public class MirrorOfBinaryTree19 {

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
