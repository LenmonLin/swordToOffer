import common.TreeNodeCommon;
/**
 * @author LemonLin
 * @Description :TreeIsSymmetrical59
 * @date 2019/3/9-14:41
 * 题目描述
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树
 * 的镜像是同样的，定义其为对称的。
 * 思路：
 * 前序遍历算法中的序指的是父结点，前序就是先父再左再右，中序是父结点放中间，
 * 先左再父再右，后序是父结点放最后，先左再右再父。
 * 自定义一种遍历算法：对称前序遍历序列：先父再右再左；
 * 比较二叉树的前序遍历序列和对称前序遍历序列是否相同，判断二叉树是不是对称的，
 * 如果两个序列是一样的，那么二叉树就是对称的
 * 为了应对特殊情况：一个二叉树的所有结点值都相等，这样要是不对称但是结点个数相同，就会
 * 误认为是对称的，所以要把null考虑进来
 * */
public class TreeIsSymmetrical59 {

    boolean isSymmetrical(TreeNodeCommon pRoot) {
        return isSymmetrical(pRoot,pRoot);
    }

    //这里pRoot1,pRoot2到时候调用的时候其实是一个结点，只是命名的时候，传入参数不能相同而已。
    public boolean isSymmetrical(TreeNodeCommon pRoot1,TreeNodeCommon pRoot2){
        if (pRoot1 == null &&pRoot2==null){
            return  true;
        }
        if (pRoot1 == null||pRoot2 == null){
            return false;
        }
        if (pRoot1.data != pRoot2.data){
            return false;
        }
        return isSymmetrical(pRoot1.left,pRoot2.right)&&isSymmetrical(pRoot1.right,pRoot2.left);
    }
    public static void main(String[] args) {

    }
}
