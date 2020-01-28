import java.util.HashMap;
/**
*  在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个
 *  地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子
 *  与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵
 *  二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
*  计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
*  示例 1:
*  输入: [3,2,3,null,3,null,1]
*  3
*  / \
*  2   3
*  \   \
*  3   1
*  输出: 7
*  解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
*  示例 2:
*  输入: [3,4,5,1,3,null,1]
*  3
*  / \
*  4   5
*  / \   \
*  1   3   1
*  输出: 9
*  解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 * @author LemonLin
 * @Description :DProb337LeetCode
 * @date 20.1.28-12:04
 * 思路：参考：https://leetcode-cn.com/problems/house-robber-iii/solution/
 * san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
 * 我们可以得出单个节点的钱该怎么算：4个孙子偷的钱 + 爷爷的钱 VS 两个儿子偷的钱，
 * 哪个组合钱多，就当做当前节点能偷的最大钱数。
 * 4个孙子投的钱加上爷爷的钱如下
 * int method1 = root.val + rob(root.left.left) + rob(root.left.right) +
 * rob(root.right.left) + rob(root.right.right)
 * 两个儿子偷的钱如下
 * int method2 = rob(root.left) + rob(root.right);
 * 挑选一个钱数多的方案则
 * int result = Math.max(method1, method2);
 * 但是这个方案，在回溯的时候还需要重新计算？重新计算没弄懂
 * 由于二叉树不适合拿数组当缓存，这次使用哈希表来存储结果，TreeNode当做key，
 * 能偷的钱当做value
 */
public class DProb337LeetCode {
    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
    public int rob(TreeNode root) {
        HashMap hashMap = new HashMap();
        return robHelper(root,hashMap);
    }
    private int robHelper(TreeNode root, HashMap<TreeNode,Integer> hashMap){
        if (root==null)return 0;
        if (hashMap.containsKey(root))return hashMap.get(root);
        int money = root.val;
        if (root.left!=null){
            money+=robHelper(root.left.left,hashMap)+robHelper(root.left.right,hashMap);
        }
        if (root.right!=null){
            money+=robHelper(root.right.left,hashMap)+robHelper(root.right.right,hashMap);
        }
        int result = Math.max(money,robHelper(root.left,hashMap)+robHelper(root.right
        ,hashMap));
        hashMap.put(root,result);
        return result;
    }

    public static void main(String[] args) {

    }
}
