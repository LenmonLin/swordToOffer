/**
 * @author LemonLin
 * @Description :TreeVerifySquenceOfBST24
 * @date 2019/3/15-11:16
 *
 * 题目描述
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。
 * 假设输入的数组的任意两个数字都互不相同。
 *
 * 解题思路：
 *
 * 采用分治法的思想，找到根结点、左子树的序列、右子树的序列，分别判断左右子序列是否为二叉树的后序
 * 序列。总体思路，一是利用后序遍历的特点，根结点在最后，结合搜索树的特点划分出左子树和右子树。
 * 二是利用搜索树的特点，左子树的数比根结点小，右子树的数比根结点大的特点进行递归
 * 判断是不是搜索树。
 *
 * 由题意可得：
 * 1. 后序遍历序列的最后一个元素为二叉树的根节点；
 * 2. 二叉搜索树左子树上所有的结点均小于根结点、右子树所有的结点均大于根结点。
 *
 * 算法步骤如下：
 * 1. 找到根结点；
 * 2. 遍历序列，找到第一个大于等于根结点的元素i，则i左侧为左子树、i右侧为右子树；
 * 3. 我们已经知道i左侧所有元素均小于根结点，那么再依次遍历右侧，看是否所有元素均大于根结点；若出
 * 现小于根结点的元素，则直接返回false；若右侧全都大于根结点，则：
 * 4. 分别递归判断左/右子序列是否为后序序列；
 */
public class TreeVerifySquenceOfBST24 {
    public boolean VerifySquenceOfBST(int [] sequence) {
        //注意这里有个等号，定义上说空树是二叉搜索树，但是出题者在测试用例上认为它不是的
        if (sequence ==null||sequence.length<=0){
            return false;
        }
        return VerifySquenceOfBST(sequence,0,sequence.length-1);
    }
    public boolean VerifySquenceOfBST(int[] sequence ,int start, int end){
        //注意这个地方容易想不明白，start ==end 对应叶子结点，start>end 对应空树，空树
        //也应该返回true,比如{5，7，6，8}没有右子树，但是结果是也应该是正确的
        if (start>=end){
            return true;
        }
        int root = sequence[end];
        //先找出左子树和右子树的分割线：
        int i=start;
        while (sequence[i]<root){
            i++;
        }
        //判断右子树
        int j=i;
        while (j<end){
            if (sequence[j]<root){
                return false;
            }
            j++;
        }
        //递归判断上述情况
        boolean left = VerifySquenceOfBST(sequence,start,i-1);
        //注意把根结点排除掉
        boolean right = VerifySquenceOfBST(sequence,i,end-1);
        return left&&right;
    }

    public static void main(String[] args) {
        TreeVerifySquenceOfBST24 treeVerifySquenceOfBST24 = new TreeVerifySquenceOfBST24();
        int [] test = {4,6,7,5};
        boolean result = treeVerifySquenceOfBST24.VerifySquenceOfBST(test);
        System.out.println(result);
    }
}
