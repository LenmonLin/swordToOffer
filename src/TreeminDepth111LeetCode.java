/**
* * 给定一个二叉树，找出其最小深度。
* 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
* 说明: 叶子节点是指没有子节点的节点。
* 示例:
* 给定二叉树 [3,9,20,null,null,15,7],
*    3
*   / \
*  9  20
* /  \
*15  7
* 返回它的最小深度  2.
 * 思路：求根结点到每个结点的高度，再比较最小值。不能用104求树高的函数，因为它是
 * 求最大的树高，不过可以借鉴解决方法。这里是求最小深度。
 * 输入:
*  [1,2]
*  输出
*  1
 * 预期结果
 * 2
 * 这个测试用例老是过不了，如果只有一个root == null 的判断条件的话，这里主要是
 * 递归出口应该有多个，如果只有一个root == null的话，题干的叶子结点是没有左右子树
 * 的结点，测试用例中1不能算叶子结点，2才算，所以结果应该是2，这里递归出口条件没
 * 考虑好。应该有以下的递归出口条件：
 * 1、根结点为空return 0
 * 2、根结点的左右子树都为空返回1；
 * 3、根结点只有左子树或者右子树  （这一点与第二点容易混淆）
 * 4、根结点既有左子树又有右子树
 * 不应该这么搞这么复杂，条件1就覆盖条件2，所以应该正常情况下只有一、四两种情况的
 * 递归出口，为什么有3,这个出口，主要是求得是最小深度。那么以下示例：
 *  1
 *2
 * 过不了，为什么？因为按照只有1,4，递归出口，1右子树高度为0,1左子树高度为1，二者
 * 取其小数再加一，也就是1的树高为0+1=1，显然这是不对的。而LeetCode104为啥没有
 * 这个错误，因为人家都说求最大的，不会遇到这种情况。所以本题的这种情况就催生了第三
 * 种递归出口，也就是
 *              if (root.left== null||root.right ==null)
 *             //这里其中一个节点为空，说明有一个必然为0，所以可以返回leftHeight+rightHeight+1;
 *                      return leftHeight+rightHeight+1;
 *   最终版在 minDepth3，minDepth2是错误版，留下来研究一下
 *
* @author LemonLin
 * @Description :TreeminDepth111LeetCode
 * @date 2020/1/9-21:05
 */
public class TreeminDepth111LeetCode {
    /**
     * Definition for a binary tree node.
     *  */
     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }
    public int minDepth3(TreeNode root) {
        if(root==null)return 0;
        int leftHeight = minDepth3(root.left);
        int rightHeight = minDepth3(root.right);
        if (root.left== null||root.right ==null){
            return leftHeight+rightHeight+1;
        }
        return Math.min(leftHeight,rightHeight)+1;
    }

    //按照和LeetCode104对比，常规逻辑应该写成这样，但是这样的代码，过不了[1,2]
    public int minDepth2(TreeNode root) {
        if(root==null)return 0;
        int leftHeight = minDepth2(root.left);
        int rightHeight = minDepth2(root.right);
        return Math.min(leftHeight,rightHeight)+1;
    }

    public int minDepth(TreeNode root) {
         //1、根结点为空return 0
        if (root == null) return 0;
        //2、根结点的左右子树都为空返回1；
        if (root.left==null&&root.right==null)return 1;

        int minHeight =Integer.MAX_VALUE ;
        int leftHeight = minDepth(root.left);
        int rightHeight = minDepth(root.right);
        // 3、根结点只有左子树或者右子树  （这一点与第二点容易混淆）
        if (root.left== null||root.right ==null)
            //这里其中一个节点为空，说明有一个必然为0，所以可以返回leftHeight+
            // rightHeight+1;
            return leftHeight+rightHeight+1;
        if (minHeight>Math.min(leftHeight,rightHeight)){
            minHeight = Math.min(leftHeight,rightHeight);
        }
        // 4、根结点既有左子树又有右子树
        return minHeight+1;
    }

    public static void main(String[] args) {
         //造一颗树[3,9,20,null,null,15,7]
        TreeNode TreeNode1 = new TreeNode(3);
        TreeNode TreeNode2 = new TreeNode(9);
        TreeNode TreeNode3 = new TreeNode(20);
        TreeNode TreeNode6 = new TreeNode(15);
        TreeNode TreeNode7 = new TreeNode(7);

        TreeNode1.left = TreeNode2;
        TreeNode1.right = null;
//        TreeNode2.left = null;
//        TreeNode2.right = null;
//
//        TreeNode3.left = TreeNode6;
//        TreeNode3.right = TreeNode7;

        System.out.println(new TreeminDepth111LeetCode().minDepth2(TreeNode1));

    }
}
