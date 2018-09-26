import common.TreeNodeCommon;

/**
 * @author LemonLin
 * @Description :TreeDepth39_1
 * @date 2018/5/12-10:54
 */
public class TreeDepth39_1 {

    public int TreeDepth(TreeNodeCommon root) {
        if (root == null){
            return 0;
        }
        int nLeft = TreeDepth(root.left);
        int nRight = TreeDepth(root.right);

        return (nLeft>nRight)?(nLeft+1):(nRight+1);
    }


    public static void main(String[] args) {
        TreeNodeCommon treeNodeCommon1 = new TreeNodeCommon(10);
        TreeNodeCommon treeNodeCommon2 = new TreeNodeCommon(5);
        TreeNodeCommon treeNodeCommon3 = new TreeNodeCommon(12);
        TreeNodeCommon treeNodeCommon4 = new TreeNodeCommon(4);
        TreeNodeCommon treeNodeCommon5 = new TreeNodeCommon(7);

        treeNodeCommon1.left = treeNodeCommon2;
        treeNodeCommon1.right = treeNodeCommon3;
        treeNodeCommon2.left = treeNodeCommon4;
        treeNodeCommon2.right = treeNodeCommon5;

        TreeDepth39_1 treeDepth39_1 = new TreeDepth39_1();
        int iDepth = treeDepth39_1.TreeDepth(treeNodeCommon1);
        System.out.println(iDepth);
    }
}
