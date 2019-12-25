/**
* * 给定一个二叉树，找出其最大深度。
* 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
* 说明: 叶子节点是指没有子节点的节点。
* 示例：
* 给定二叉树 [3,9,20,null,null,15,7]，
* 3
* / \
* 9  20
* /  \
* 15   7
* 返回它的最大深度 3 。
 * 思路：比较简单，利用遍历手段，求左子树的树高，右子树的树高，比较哪个大再加一，
 * 即为整颗树的树高。这里要注意的一个bug就是，左子树树高和右子树的树高以及总树
 * 高不能设置为全局变量，会出错，详见代码。同时if (root.left == null && root.right
 * == null)的判断可有可无，因为root==null这个判断就包含了所有的情况，所以
 * root.left == null && root.right == null的判断可以理解为剪枝操作，让遍历更快一些。
* @author LemonLin
* @Description :TreemaxDepth104LeetCode
* @date 2019/12/23-21:00
*/
public class TreemaxDepth104LeetCode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        //这样写有个bug
        //输入：[0,-3,-2,-9,-8,null,null,2,null,null,-5,5]
        // 下标   0，1，2,3 ,4, 5,    6,   7, 8,   9,    10,11
        //输出：4
        //预期：5
        /*
        *注释掉以下的left和right的代码，出现这个bug
        * 输入:
        * [1,2,3,4,5]
        * * 1
         * / \
         *2  3
          /   \
        4     5
        * 输出
        * 2
        * 预期结果
        * 3
        * 知道了，原来不是注释掉left和right的代码的原因，是因为把leftHight和
        * RightHight以及Hight都设置成全局变量，这样当遍历到2的时候，记录
        * 2的leftHight就是1，而当遍历到5的时候又把leftHight重置为0，造成错误。
        * 所以应该将这些变量设置为递归函数的局部变量。
        */
        if (root.left == null && root.right == null)
            return 1;
        int LeftHight = maxDepth(root.left);
        int RightHight = maxDepth(root.right);
        int Hight =0;
        if (LeftHight >= RightHight) {
            Hight = LeftHight + 1;
        } else {
            Hight = RightHight + 1;
        }
        return Hight;
    }

    //题解的答案。
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepth(root.left);
            int right_height = maxDepth(root.right);
            return java.lang.Math.max(left_height, right_height) + 1;
        }
    }

    //数组转换为二叉树的代码，和LeetCode的建树代码还是有差别，无法处理形如
    //输入：[0,-3,-2,-9,-8,null,null,2,null,null,-5,5]
    // 下标   0，1，2,3 ,4, 5,    6,   7, 8,   9,    10,11
    /* *      0
            * /  \
            -3   -2
           /  \
        -9   -8
       /       \
      2        -5
     /
    5
    这里注意数组最后一个数字 5的存放位置，这里的构造函数暂时解决不了这个问题。留个bug
    **/
    public TreeNode createBinaryTreeByArray(Integer[] array, int index) {
        TreeNode tn = null;
        if (index < array.length) {
            Integer value = array[index];
            if (value == null) {
                return null;
            }
            tn = new TreeNode(value);
            tn.left = createBinaryTreeByArray(array, 2 * index + 1);
            tn.right = createBinaryTreeByArray(array, 2 * index + 2);
            return tn;
        }
        //这里的tn是当index < array.length不满足时返回，也就是数组坐标最后几个数，
        //他们的孩子结点超出了坐标返回，要给他们的孩子结点设置null值。
        return tn;
    }


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,2,3,4,5,6,7,8};
        TreeNode root = new TreemaxDepth104LeetCode().createBinaryTreeByArray(arr, 0);
        System.out.println(new TreemaxDepth104LeetCode().maxDepth(root));
    }
}