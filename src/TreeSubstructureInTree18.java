import common.TreeNodeCommon;

/**
 * 题目描述
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 * 解题思路：
 * 分为两步骤：
 *  一、先判断根节点是不是相同的，使用一种遍历方法，遍历需要判定的树，进行判断。
 *  二、对左右子树进行判断，这个问题比较多，应该仔细反复思考下；
 *
 * @author LemonLin
 * @Description :SubstructureInTree18
 * @date 2018/2/23-15:55
 */
public class TreeSubstructureInTree18 {

    //先判断根节点是否是相同的
    public boolean HasSubtree(TreeNodeCommon root1, TreeNodeCommon root2) {

        boolean result = false;
        //当左子树和右子树都不为空的时候才能进行比较，否则返回空；
        if (root1!=null &&root2!=null){
            if (root1.data == root2.data ){
                result = DoseTree1HaveTree2(root1,root2);
            }
            //对root1的左子树寻找
            if (!result){
                result = HasSubtree(root1.left,root2);
            }
            //对root2的右子树寻找
            if (!result){
                result = HasSubtree(root1.right,root2);
            }
        }
        return result;
    }

// 左子树中是否含有右子树
    public boolean DoseTree1HaveTree2(TreeNodeCommon root1,TreeNodeCommon root2) {

//如果Tree2都遍历完了，都能够对应的上，则返回真
        if (root2 ==null){
            return true;
        }
        //如果Tree1先遍历完，则是对应不上，返回假
        if (root1 == null){
            return false;
        }
        //如果其中一个点没有对应上，则返回假
        if (root1.data != root2.data){
            return false;
        }

        //分别对子树的左节点和右节点进行遍历
        return DoseTree1HaveTree2(root1.left,root2.left)&&DoseTree1HaveTree2(root1.right,root2.right);
    }
}
