import java.util.LinkedList;
import java.util.Queue;
/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 * 思路：不管用哪种遍历，都可以通过遍历去到每个节点，所以前中后序遍历随意选一种即可，
 * 关键是递归传入的参数，这里要注意题干给的平衡二叉搜索树，在二叉搜索树的前提下，要
 * 平衡，同时注意传入的是升序的有序数组，所以取数组的中点就可以作为子树的边界，但是
 * 平衡这个怎么处理？同时中序遍历二叉排序树时输出是递增数组这个性质怎么用，其实从根据
 * 数组建立二叉树的题型可以得知，从二叉树到递增数组可以用中序遍历，但是从递增数组到
 * 二叉树不必一定要用中序遍历，也可以用先序和后序，递增数组只要能够遍历到树的每个节点，
 * 然后把数组中的数给赋值进去即可。所以本题用什么遍历都可以。只需要考虑中间节点作为
 * 边界，即可满足二叉搜索树的条件，同时假定了给定的递增数组一定能刚好构成平衡的树，所以
 * 本题代码上不用考虑平衡。
 * 思路矫正：以上思路描述有错误有正确，重新整理一下：首先理解两个定理：
 * 1、从二叉树到递增数组，要用中序遍历二叉树才能得到递增数组；反之不可以，一个遍历
 * 后得到的数组无法确定一棵树，必须两种遍历数组才能确定。
 * 2、两个遍历数组次序才能确定一颗普通的二叉树，比如前序加中序，后序加中序。这里
 * 前序和后序生成的数组存在的意义是确立根节点，中序遍历生成的数组存在的意义是根据
 * 根节点确立左右子树的范围。
 * 基于以上两点：本提给了一个数组，可以确定是中序遍历产生的数组，因为是一个二叉搜索树
 * 被中序遍历之后得到的就是递增数组，这里题目默认是还原一颗树，也就是数组的产生是由
 * 二叉搜索树，然后再还原成二叉搜索树。有了中序遍历的数组了，基于第二个定理，需要
 * 一个前序或者后序来确定根节点，但是没有前序也没有后序，但是只要能确定根节点就可以
 * 题干要求平衡，平衡就说明可以用中序遍历的中间节点作为根节点，这样两边都分一半，
 * 一定会平衡，所以可以由题干平衡推出根节点，之后再利用105,106前序加中序或者后序
 * 加中序的解题思路推出平衡二叉树。思路上说的用什么遍历都可以，指的是解题方式是递归，
 * 通过什么遍历都可以，只要是递归就可以。因为前序中序后序遍历本质上都是递归。
 *
 *这里递归有个bug一直没想通，递归的变量定义一定要放在递归函数内部，因为向上返回时
 * 还需要用到这个变量，而且是需要用到上一层递归时的变量的值，这里之前把mid设置成了
 * 全局变量，导致了向下递归的时候数据是正确的，当时向上回溯时mid的值是错误的，保留
 * 的是上一次的变量，不是原来的递归函数内的mid的值。这一点要非常注意。
 * @author LemonLin
 * @Description :TreesortedArrayToBST108LeetCode
 * @date 2019/12/24-21:10
 */
public class TreesortedArrayToBST108LeetCode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums,0,nums.length-1);
    }
    public TreeNode sortedArrayToBST(int[] nums,int left ,int right){
        if (left>right)
            return null;
        //这里的mid一定不能设置成全局变量，所有的递归函数需要的变量一般都设置在
        //递归函数内部比较合理
        int mid = (int)Math.ceil(((double) left+right)/2);
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = sortedArrayToBST(nums,left,mid-1);
        treeNode.right = sortedArrayToBST(nums,mid+1,right);
        return treeNode;
    }

    //树的层次遍历输出
    public void TreeLevelOrderByLemonLin(TreeNode root){
        Queue<TreeNode> queue = new LinkedList();
        //先放入根节点
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode TreeNode = queue.poll();
            System.out.println(TreeNode.val);
            if (TreeNode.left!= null){
                queue.offer(TreeNode.left);
            }
            if (TreeNode.right!=null){
                queue.offer(TreeNode.right);
            }
        }
    }

    public static void main(String[] args) {
        //                        0,1,2,3,4
        int [] nums = {-10,-3,0,5,9};
        TreeNode treeNode = new  TreesortedArrayToBST108LeetCode().sortedArrayToBST(nums);
        new  TreesortedArrayToBST108LeetCode().TreeLevelOrderByLemonLin(treeNode);
    }
}
