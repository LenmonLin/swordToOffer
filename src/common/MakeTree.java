package common;

/**
 * @author LemonLin
 * @Description :MakeTree
 * @date 2018/3/22-17:04
 *                  8
 *             6       10
 *          5   7    9    11
 *
 */
public class MakeTree {

    public  TreeNodeCommon makeTree(){
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

        return treeNodeCommon1;
    }

    public static void main(String[] args) {

    }
}
