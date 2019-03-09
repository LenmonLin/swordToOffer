import com.sun.scenario.effect.impl.prism.ps.PPSBlend_ADDPeer;
import common.MakeTree;
import common.TreeNodeCommon;

/**
 * @author LemonLin
 * @Description :TreeKthNode63
 * @date 2019/3/9-20:48
 *
 * 题目描述
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）
 * 中，按结点数值大小顺序第三小结点的值为4。
 *
 * 解题思路：利用二叉搜索树的特性，使用中序遍历树得到的就是排序的结果。
 */
public class TreeKthNode63 {

    //计数器
    int index =0;
    TreeNodeCommon KthNode(TreeNodeCommon pRoot, int k) {

        if (pRoot != null){
            TreeNodeCommon node = KthNode(pRoot.left,k);
            //如果没有if(node != null)这句话 那么那个root就是返回给上一级的父结点的，而不是递归结束
            // 的条件了,有了这句话过后，一旦返回了root，那么node就不会为空了，就一直一层层的递归出
            // 去到结束了。
            if(node != null)
                return node;
            index++;
            if (index ==k) {
                return pRoot;
            }
            node = KthNode(pRoot.right,k);
            if(node != null)
                return node;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeKthNode63 treeKthNode63 = new TreeKthNode63();
        MakeTree makeTree = new MakeTree();
        TreeNodeCommon pRoot = makeTree.makeTree();
        TreeNodeCommon result = treeKthNode63.KthNode(pRoot, 1);
        if (result!=null) {
            System.out.println(result.data);
        }
    }
}
