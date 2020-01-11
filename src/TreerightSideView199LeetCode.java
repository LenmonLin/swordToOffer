import java.util.ArrayList;
import java.util.List;
/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能
 * 看到的节点值。示例:
* 输入: [1,2,3,null,5,null,4]
* 输出: [1, 3, 4]
* 解释:
*   1            <---
* /   \
* 2    3         <---
* \     \
* 5     4       <---
 *思路：递归法，一直向右走。
 * 一直优先遍历root.right,如果没有root.right,再考虑遍历root.left。遍历了一个root.left，
 * 下一个还是优先考虑有没有root.right.递归结束是root.left 和root.right都等于null。
 * 但是这个有个问题，如果是以下这种树的话，就没有办法了。看来这个思路有问题。
 *      1            <---
 *    /   \
 *   2    3         <---
 *   \     \
 *    5    4       <---
 *    /
 *    3           <---
 *  这种情况下3没有被遮住。以上思路无法奏效。。。
 *  看了题解，为了解决以上思路存在的bug,可以创建一个当前深度标记变量deep，
 *  让deep与遍历的结果集合result.length进行比较，如果deep大于result.length，
 *  就说明这一层还没有将最右节点放入结果集，反之则是放过了。这样就能达成每层只
 *  放一个节点的逻辑。这里其实是把全部节点都遍历了，即使已经存了路径，还是要把
 *  所有的节点都遍历，因为不知道树高是多少。所以这里用result.length == deep
 *  真的很巧妙。
 *  另外一种思路：相当于对二叉树进行层序遍历，将每一层的最后一个节点存入最终的
 *  结果即可。
 * @author LemonLin
 * @Description :TreerightSideView199LeetCode
 * @date 2020/1/11-10:27
 */
public class TreerightSideView199LeetCode {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList arrayList = new ArrayList();
        //这里的level需要的是局部变量，因为还需要利用回溯时level的值来判断是否已经
        //已经放入这一层的值
        int level =0;
        helper(root,arrayList,level);
        return arrayList;
    }
    public void helper(TreeNode root,ArrayList arrayList,int level){
        if (root ==null)return;
        if (arrayList.size() == level){
            arrayList.add(root.val);
        }
        helper(root.right,arrayList,level+1);
        helper(root.left,arrayList,level+1);
    }

    public static void main(String[] args) {
        //造一颗树[5,4,8,11,null,13,4,7,2]
        /*
         *       5
         *      / \
         *    4   8
         *    /   / \
         *  11  13  4
         *  /  \
         * 7    2
         */
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(8);
        TreeNode treeNode4 = new TreeNode(11);
        TreeNode treeNode6 = new TreeNode(13);
        TreeNode treeNode7 = new TreeNode(4);
        TreeNode treeNode8 = new TreeNode(7);
        TreeNode treeNode9 = new TreeNode(2);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left =treeNode4;
        treeNode2.right = null;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        treeNode4.left = treeNode8;
        treeNode4.right = treeNode9;
        treeNode6.left = null;
        treeNode6.right = null;
        treeNode7.left = null;
        treeNode7.right = null;
        treeNode8.left = null;
        treeNode8.right = null;
        treeNode9.left = null;
        treeNode9.right = null;
        new TreerightSideView199LeetCode().rightSideView(treeNode1);
    }
}
