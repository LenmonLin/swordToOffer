/**
*  给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径
 *  都代表一个数字。例如，从根到叶子节点路径 1->2->3 代表数字 123。
*  计算从根到叶子节点生成的所有数字之和。
*  说明: 叶子节点是指没有子节点的节点。
*  示例 1:
*  输入: [1,2,3]
*     1
*    / \
*   2   3
*  输出: 25
*  解释:
*  从根到叶子节点路径 1->2 代表数字 12.
*  从根到叶子节点路径 1->3 代表数字 13.
*  因此，数字总和 = 12 + 13 = 25.
*  示例 2:
*  输入: [4,9,0,5,1]
*     4
*    / \
*   9   0
*  / \
* 5   1
*  输出: 1026
*  解释:
*  从根到叶子节点路径 4->9->5 代表数字 495.
*  从根到叶子节点路径 4->9->1 代表数字 491.
*  从根到叶子节点路径 4->0 代表数字 40.
*  因此，数字总和 = 495 + 491 + 40 = 1026.
 * @author LemonLin
 * @Description :TreesumNumbers129LeetCode
 * @date 2020/1/11-10:22
 * 思路：参考第113的解法，用递归思路，然后设置两个全局变量传入函数，第一个变量用
 * 来记录临时的一条路径的拼凑数，这里要注意需要用到回溯。第二个变量用来记录总的和。
 * 注意这里的全局变量要设置在外面，不能通过在函数中传递参数来体现全局变量，在做
 * 113题的时候为什么可以，因为113题传递的是引用变量，是数组，在Java中引用变量
 * 天生就是全局的，这里的int是基础类型，基础类型在函数中作为参数传入就是局部变量，
 * 之前在113题的时候理解有误。
 * 还有一个套路，这里回溯写法是个固定的helper ,返回void，这里不能把helper去掉，
 * 目前是还想不到的，回溯写法需要另起一个辅助函数。先暂时记住这个套路吧.
 * 本题让笔者对回溯算法的局部变量和全局变量有了更深刻的理解：
 *1、 首先先记住局部变量的声明是放在一个函数之中的，然后再通过，函数参数传递的方式。
 * 比如：
 * sumNumbers2中的tempSum就是局部变量的定义。回想一下，LeetCode113中的写法
 *  public void helper(TreeNode root, int sum, List<List<Integer>> result, ArrayList
 *             temp){
 *      注意这里的temp严格意义上是局部变量。
 *2、为啥要用局部变量，因为是回溯算法，回溯的时候需要用到归这个操作，局部变量的设定
 * 是底层递归函数回退到上层递归函数的时候，局部变量也就变成上层时的值。可以观察
 * 本题 public int sumNumbers2(TreeNode root) {中的
 * public void helper2 (TreeNode root,int tempSum){
 *  中的 helper2(root.left,tempSum);
 *         helper2(root.right,tempSum);
 *         这两行代码之后对tempSum的任何操作都是无效也没有必要的，应该tempSum
 *         局部变量，当底层递归函数回退到上层递归函数的时候，局部变量也就变成上层时的值。
 *          底层递归函数对他局部变量的操作就烟消云散了。看官会有疑惑了，那没LeetCode113
 *          中的temp.remove(temp.size()-1);这个怎么解释。其实这个是因为这里的temp
 *          是数组，数组是引用类型，就是可以理解为全局变量的特性。就是说虽然temp名义
 *          上是局部变量，但是因为是ArrayList引用类型，它具有全局变量的特性，所以需要
 *          手动的回溯，归的时候需要把底层递归函数对变量的影响给消除掉，所以有了remove的
 *          操作。
 *    参考本题如果把tempSum变成全局变量，就需要手动回溯修改，参考sumNumbers
 *    函数代码。
 *
 */
public class TreesumNumbers129LeetCode {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    int temp =0;
    int sum =0;
    public int sumNumbers(TreeNode root) {
        helper(root);
        return sum;
    }
    public void helper (TreeNode root){
        //return必须写在这里，不能把return写在if (root.left ==null&&root.right ==null){
        //里面，如果写了，会因为直接return，没执行到回溯一步而导致错误结果。
        if (root == null)return;
        temp = temp*10+root.val;
        if (root.left ==null&&root.right ==null){
            sum+=temp;
        }
        helper(root.left);
        helper(root.right);
        //这里反正最后一位是不需要的，所以可以不用减直接写成
        temp =temp/10;
//        temp = (temp-root.val)/10;
    }

    int result =0;
    public int sumNumbers2(TreeNode root) {
        int tempSum=0;
        helper2(root,tempSum);
        return result;
}
    public void helper2 (TreeNode root,int tempSum){
        //return必须写在这里，不能把return写在if (root.left ==null&&root.right ==null){
        //里面，如果写了，会因为直接return，没执行到回溯一步而导致错误结果。
        if (root == null)return;
        tempSum = tempSum*10+root.val;
        if (root.left ==null&&root.right ==null){
            result+=tempSum;
        }
        helper2(root.left,tempSum);
        helper2(root.right,tempSum);
//        tempSum =tempSum/10;
    }
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left =null;
        treeNode2.right = null;
        treeNode3.left = null;
        treeNode3.right = null;
        System.out.println(new TreesumNumbers129LeetCode().sumNumbers2(treeNode1));
    }
}
