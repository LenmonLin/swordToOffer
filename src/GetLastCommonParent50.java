import common.MakeTree;
import common.TreeNodeCommon;

/**
 * @author LemonLin
 * @Description :GetLastCommonParent
 * @date 2018/9/20-10:55
 *题目：树中两个结点的最低公共祖先
 *
 * 解法一：假设是二叉搜索树（二叉搜索树是一个排序的二叉树，左子树的结点小于根结点，右子树的结点大于
 * 根节点），故找到一个节点，使其大于左子节点小于右子结点即可。
 *
 * 程序思路有三部分:
 *      一、考虑异常输入
 *      二、考虑正确的输入结果
 *      三、考虑向左递归还是向右递归
 */
public class GetLastCommonParent50 {


    /*
     需要用到递归的想法
     */
    public TreeNodeCommon GetLastCommonParentOne(TreeNodeCommon tRoot,
                                                 TreeNodeCommon tLeft,TreeNodeCommon tRight){

        TreeNodeCommon treeNodeCommon = null;

        if (tRoot == null || tRight.data<tLeft.data){
            return  null;
        }

        if (tRoot.data >tRight.data){
            return treeNodeCommon = GetLastCommonParentOne(tRoot.left,tLeft,tRight);
        }

        if (tRoot.data <tLeft.data){
            return treeNodeCommon = GetLastCommonParentOne(tRoot.right,tLeft,tRight);
        }

        if (tRoot.data > tLeft.data && tRoot.data < tRight.data){
            return  tRoot;
        }

        return  treeNodeCommon;
    }

    public static void main(String[] args) {
        MakeTree makeTree = new MakeTree();

        TreeNodeCommon pRoot = makeTree.makeTree();
        TreeNodeCommon tLeft = new TreeNodeCommon(5);
        TreeNodeCommon tRight = new TreeNodeCommon(11);

        GetLastCommonParent50 getLastCommonParent50 = new GetLastCommonParent50();
        TreeNodeCommon lastCommonParent = getLastCommonParent50.GetLastCommonParentOne(pRoot,tLeft,tRight);
        System.out.println(lastCommonParent.data);
    }
}
