import java.util.ArrayList;

/**
* 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
* 假设一个二叉搜索树具有如下特征：
* 节点的左子树只包含小于当前节点的数。
* 节点的右子树只包含大于当前节点的数。
* 所有左子树和右子树自身必须也是二叉搜索树。
* 示例 1:
* 输入:
* 2
* / \
* 1   3
* 输出: true
* 示例 2:
* 输入:
* 5
* / \
* 1   4
*      / \
*     3   6
* 输出: false
* 解释: 输入为: [5,1,4,null,null,3,6]。
*      根节点的值为 5 ，但是其右子节点值为 4 。
 * 思路：乍一看，这是一个平凡的问题。只需要遍历整棵树，检查 node.right.val >
 * node.val 和node.left.val < node.val 对每个结点是否成立。(本人最开始也是这
 * 么想的，囧，主要遗漏了上下界的考虑)，问题是：这种方法并不总是正确。不仅右子结点
 * 要大于该节点，整个右子树的元素都应该大于该节点。这意味着我们需要在遍历树的同时
 * 保留结点的上界与下界，在比较时不仅比较子结点的值，也要与上下界比较。
 * 这里上下界的获取比较麻烦。复杂一点先遍历一遍获取上下界。题解是反向思维，直接
 * 传入null的上下界，然后用false来判断，简直神奇。
*  @author LemonLin
 * @Description :TreeisValidBST98LeetCode
 * @date 2019/12/27-15:03
 * 如果直接上述递归没想明白，可以考虑用中序遍历的方式，比较简洁明了，因为中序遍历
 * 的二叉排序树的结果是递增的数据，可以利用这个特点进行判断。
 * 参考：https://leetcode-cn.com/problems/validate-binary-search-tree/solution/
 * bstjing-guo-zhong-xu-bian-li-listzhong-de-yuan-su-/
 */
public class TreeisValidBST98LeetCode {
    public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
     }
    //中序遍历解法
    public boolean isValidBST2(TreeNode root,ArrayList<TreeNode> result ){
        inorderTravel(root,result);
        for (int i=0;i<result.size()-1;i++){
            if (result.get(i).val>=result.get(i+1).val){
                return false;
            }
        }
        return true;
    }

    private void inorderTravel(TreeNode root,ArrayList<TreeNode> result){
        if (root ==null){
            return ;
        }
        inorderTravel(root.left,result);
        result.add(root);
        inorderTravel(root.right,result);
    }



    public boolean isValidBST(TreeNode root) {
        ArrayList<TreeNode> result = new ArrayList<>();
        return isValidBST2(root,result);
        //return isValidBST(root,null,null);
    }
     /**
      *@Description
      *@params  lower 、upper分别表示传入的上下界
      *@author LemonLin
      *@date  2019/12/27
      */
    public boolean isValidBST(TreeNode node,Integer lower ,Integer upper) {

        Integer val = null;
        if (node!=null) {
            val = (Integer) node.val;
        }
        if (node ==null )return true;
        /**
         * 这里要有等号，因为如果没有等号，测试用例返回了true
         * 输入：
         *[1,1]
         *输出：
         *true
         *预期：
         *false
         */
        if (lower!=null&&val <=lower)return false;
        if (upper!=null&&val >=upper)return false;
        return isValidBST(node.left,lower,val)&& isValidBST(node.right,val,upper);
    }
}
