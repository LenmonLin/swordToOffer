import com.sun.org.apache.xpath.internal.SourceTree;
import common.TreeNodeCommon;

/**
 * @author LemonLin
 * @Description :ConstructBinaryTree06
 * @date 2018/2/7-17:23
 *
 *
 * 疑问点：
 *
 * Java中怎么表示指针，比如数组要用首指针和末尾指针表示；
 * 为解决这个问题，应该把数组首地址和数组的第一个下标和最后一个下标传入即可；
 *
 * Java中表示c语言中的结构体可以用类来表示，Java中没有结构体的说法。
 *
 * 重要解题要点:
 *      使用递归方法解题，一定要写出口代码，不然程序肯定会报错；
 *
 *
 *
 */

/*public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}*/


public class ConstructBinaryTree06 {


    //中序遍历二叉树
    public void InorderTraversal( TreeNodeCommon bt )
    {
        if(bt == null){
            return ;
        }
        InorderTraversal(bt.left);
        System.out.println("========"+bt.data);
        InorderTraversal(bt.right);
    }



    public TreeNodeCommon reConstructBinaryTree(int [] preorder,int [] inorder) {


        return ConstructBinaryTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);

    }

    public TreeNodeCommon ConstructBinaryTree(int [] preorder,int preStart,int preEnd,int [] inorder,int inStart,int inEnd) {

        TreeNodeCommon rootNode = new TreeNodeCommon(preorder[preStart]);

        int  rootValue = preorder[preStart];
        rootNode.left = rootNode.right = null;

        if(preorder.length == 0||inorder.length == 0){
            return null;
        }

        if (preStart == preEnd){
            if (inStart==inEnd&&preorder[preStart]==inorder[inStart]){
                return rootNode;
            }
        }
        int  rootInorder=preStart;

        System.out.println("前序遍历的左半部分开头preStart==="+preStart);
//        for (;rootInorder<=inEnd;rootInorder++){
//            if (rootValue == inorder[rootInorder]){
//                break;
//            }
//        }

        while (rootInorder <=inEnd && inorder[rootInorder]!=rootValue){
            rootInorder++;
        }
        System.out.println("中序遍历根节点的位置rootInorder="+rootInorder);

       // int leftInLength =in.length- i;//这里不能用这个，因为这样做算的是右子树的长度
        int leftInLength =rootInorder-inStart;//这里i是指的是中序系列的根节点的坐标，所以也要用instart,而不是prestart
        int leftPreorderEnd = preStart+leftInLength;


        System.out.println("in.length中序遍历的序列长度=="+inorder.length);
        System.out.println("leftInLength中序遍历的根节点的左半部分长度=="+leftInLength);

        //第一个参数固定pre
        //注意得判断左子树和右子树是否还有长度才再调用，否则递归出错
        if (leftInLength>0){
            rootNode.left = ConstructBinaryTree(preorder,preStart+1,leftPreorderEnd,inorder,
//                    inStart,inStart+leftInLength-1);
                    inStart,rootInorder-1);
        }
        if (leftInLength<preEnd-preStart){
            rootNode.right =  ConstructBinaryTree(preorder,leftPreorderEnd+1,preEnd,inorder,
                    rootInorder+1,inEnd);
        }
        return rootNode;
    }


    public static void main(String[] args) {

        int []preorder={1,2,3,4};

        int [] inorder={4,3,2,1};

        //这里不能写TreeNodeCommon，不是返回什么就写什么；
        //新建一个什么类，自然就返回什么类的，类和方法不一样。

        ConstructBinaryTree06 test = new ConstructBinaryTree06();
        TreeNodeCommon treeNodeCommon = test.reConstructBinaryTree(preorder, inorder);

        //打印二叉树
        test.InorderTraversal(treeNodeCommon);


    }

}




