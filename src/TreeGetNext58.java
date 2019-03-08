import common.MakeTreelink;
import common.TreeLinkNode;
import common.TreeNodeCommon;

/**
 * @author LemonLin
 * @Description :TreeGetNext58
 * @date 2019/3/8-15:37
 *
 * 题目描述
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含
 * 左右子结点，同时包含指向父结点的指针。
 *
 * 思路:
 *
 *                             A
 *                   B                    C
 *              D       E           F        G
 *                 H  I    J            K  L    M
 *              N
 *中序遍历排序：DNHBIEJAFKCLGM(中序是指根结点中序)
 * 1、有右子树的，那么下个结点就是右子树最左边的点；（eg：D，B，E，A，C，G）
 * 2、没有右子树的，也可以分成两类，
 * a)是父节点左孩子（eg：N，I，L） ，那么父节点就是下一个节点 ；
 * b)是父节点的右孩子（eg：H，J，K，M）找他的父节点的父节点的父节点...直到当前结点是其父节点的左孩子位置。
 * c) 如果没有eg：M，那么他就是尾节点。
 */
public class TreeGetNext58 {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null){
            return null;
        }
        //结点有右子树
        if (pNode.right!= null){
            pNode = pNode.right;
            while (pNode.left != null){
                pNode = pNode.left;
            }
            return pNode;
            //节点是父节点左孩子
        }else if (pNode.parent != null&&pNode.parent.left== pNode){
            return pNode.parent;
            //节点时父结点的右孩子
        }else if (pNode.parent != null&&pNode.parent.right == pNode){
            while (pNode.parent!=null&&pNode.parent.left !=pNode){
                pNode=pNode.parent;
            }
            return pNode.parent;
            //结点无父亲结点，则为根结点
        }else {
            return pNode.parent;
        }
    }

    public static void main(String[] args) {
        TreeGetNext58 treeGetNext58 = new TreeGetNext58();
        //建的好像没有用
        TreeLinkNode treeLinkNode = new MakeTreelink().MakeLikedList();
        TreeLinkNode test =new TreeLinkNode('A');
        TreeLinkNode result = treeGetNext58.GetNext(test);
        System.out.println(result.val);
    }
}
