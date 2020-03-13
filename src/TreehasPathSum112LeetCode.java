/**
* * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条
 * 路径上所有节点值相加等于目标和。
* 说明: 叶子节点是指没有子节点的节点。
* 示例: 给定如下二叉树，以及目标和 sum = 22，
*       5
*      / \
*    4   8
*    /   / \
*  11  13  4
*  /  \        \
* 7    2      1
* 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * @author LemonLin
 * @Description :TreehasPathSum112LeetCode
 * @date 2020/1/10-11:16
 * 思路：使用深度优先搜索记录根到每个叶子节点的和。
 * 有一个问题没想好，就是不知道这个求和的变量怎么加比较好?不知道用全局还是局部
 * 变量，很混乱。
 * 看了题解：原来是反向操作，把sum进行减操作，当到叶子节点的时候，判断sum是否
 * 为0即可。有点意思。
 * 思路二：可以用回溯方法，设置一个局部变量，当这个局部变量等于sum的时候，就判断，
 * 这个才是比较常规的思路解法。这题要和LeetCode110做对比，才有收获，LeetCode110
 * 不用遍历所有的节点，而本题需要遍历所有的节点，即使某个节点返回true之后。还是需要
 * 继续遍历接下去的节点。关键区别点在于递归出口的设置，本题递归出口设置为
 *    if (root == null){
 *             return false;
 *         }
 *     和：
 *     if (root.left ==null&&root.right==null&&temp == sum){
 *             return true;
 *         }
 *     不管哪一个，都是必须到叶子节点才能返回，这样就造成递归函数必须遍历到所有的
 *     叶子节点才能终止遍历。为啥到第二个叶子节点的满足递归出口之后，还需要遍历到
 *     后面的节点，因为这里的递归出口是到叶子节点，所以即使左子树的叶子节点满足递归
 *     出口条件，发生了归的操作，但是右子树还没满足，以return出口需要返回left||right，
 *     所以两个都要求。所以还会继续进入右子树去寻求新的叶子节点。这样反复下去就造成
 *     了遍历所有的节点。
 *     而LeetCode110的递归出口设置为：
 *            if (root == null){
 *             return true;
 *         }
 *         和：
 *         if (Math.abs(leftHeight-rightHeight)>=2){
 *             return false;
 *         }
 *         注意观察发现第二个递归出口不一定要到叶子节点才能终止，这样就造成很可能在
 *         某个节点就有归的操作。这里还有一个点，就是LeetCode110的返回出口为：
 *         return isBalanced(root.left)&&isBalanced(root.right);注意这个&&符号，同时
 *         递归出口其中一个返回的是false，&&符号有个短路特性，也就在只要其中一个返回
 *         false了，那么剩下那个不用计算了，不会影响最后结果。而LeetCode112最后是||
 *         的运算，所以都需要计算。
 */
public class TreehasPathSum112LeetCode {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
     //回溯算法。
    public boolean hasPathSum2(TreeNode root, int sum) {
        return helper2(root,sum,0);
    }
    public boolean helper2(TreeNode root, int sum,int temp) {
        if (root == null){
            return false;
        }
        temp +=root.val;
        if (root.left ==null&&root.right==null&&temp == sum){
            return true;
        }
        boolean left = helper2(root.left,sum,temp);
        boolean right = helper2(root.right,sum,temp);
        temp-=root.val;
        return left&&right;
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null){
            return false;
        }
        sum -=root.val;
        //到叶子节点，仔细体会return sum==0这句，这样写可以少写一个判断。
        if (root.left ==null&&root.right==null){
            return (sum==0);
        }
        return hasPathSum(root.left,sum)||
        hasPathSum(root.right,sum);
    }

    public static void main(String[] args) {
        //造一颗树[5,4,8,11,null,13,4,7,2,null,null,null,1]
                /*
                *       5
                *      / \
                *    4   8
                *    /   / \
                *  11  13  4
                *  /  \        \
                * 7    2      1
                */
        TreeNode treeNode1 = new TreeNode(5);
        TreeNode treeNode2 = new TreeNode(4);
        TreeNode treeNode3 = new TreeNode(8);
        TreeNode treeNode4 = new TreeNode(11);
        TreeNode treeNode6 = new TreeNode(13);
        TreeNode treeNode7 = new TreeNode(4);
        TreeNode treeNode8 = new TreeNode(7);
        TreeNode treeNode9 = new TreeNode(2);
        TreeNode treeNode13 = new TreeNode(1);

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
        treeNode7.right = treeNode13;
        treeNode8.left = null;
        treeNode8.right = null;
        treeNode9.left = null;
        treeNode9.right = null;
        treeNode13.left = null;
        treeNode13.right = null;
        System.out.println(new TreehasPathSum112LeetCode().hasPathSum2(treeNode1, 22));
    }
}
