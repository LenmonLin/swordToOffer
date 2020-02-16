import java.util.HashMap;

/**
* * 根据一棵树的前序遍历与中序遍历构造二叉树。
* 注意:
* 你可以假设树中没有重复的元素。
* 例如，给出
* 前序遍历 preorder = [3,9,20,15,7]
* 中序遍历 inorder = [9,3,15,20,7]
* 返回如下的二叉树：
* 3
* / \
* 9  20
* /  \
* 15   7
 * 解题思路：其实就是找根结点，找左子树，右子树下标带入递归函数，步骤如下：
 * 1、前序队列找到根节点；
 * 2、利用前序序列找到的根节点对应到中序序列中，找到对应根节点的左子树和右子树。
 * 3、根据中序序列找到的左子树和右子树到前序序列中找对应左子树的根节点和右子树
 * 的根节点。
 * 总结来说：在前序序列中找根节点，在中序序列中找左子树和右子树。
 * {}这部分看看就好，应该放弃这种做法，容易出错，特别是在后序中不通用
 *{
 *  虽然做题思路是这样，但是从代码来看，有一个特别的点，就是第三步，不是继续从左子树
 * 和右子树中到前序序列找对应于的根节点，可以看出根节点代码下标是preIndex，而且
 * 在递归代码逻辑中进行preIndex++，也就是这其中不是利用中序序列分割出来的左子树
 * 和右子树跟着递归从前序序列中找对应的根节点，而是认为前序序列中的每个下标节点
 * 都是某一课树的根节点，只要把每个节点的作为根节点的情况下，它的左子树和右子树的
 * 关系都建立好，那么久完成了整颗树的建立。
 * }
 * 合理的解法：参考：
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and
 * -inorder-traversal/solution/qian-xu-bian-li-python-dai-ma-java-dai-ma-by-liwei/
 *补充：ConstructBinaryTree2完全是按照做题思路的解法，注意，这里要传入四个下标的
 * 参数，前序遍历的首尾坐标，中序遍历的首尾坐标。应该放弃ConstructBinaryTree的解法。
 * 注意在前序的左子树的尾坐标，应该包含用中序中找到的左右子树的长度来替代。
 * 疑问点：
 * Java中怎么表示指针，比如数组要用首指针和末尾指针表示；
 * 为解决这个问题，应该把数组首地址和数组的第一个下标和最后一个下标传入即可；
 * Java中表示c语言中的结构体可以用类来表示，Java中没有结构体的说法。
 * 重要解题要点:
 *      使用递归方法解题，一定要写出口代码，不然程序肯定会报错；
 * @author LemonLin
 * @Description :TreebuildTree105LeetCode
 * @date 2019/12/27-16:51
 */
public class TreebuildTree105LeetCode {
     public class TreeNode { int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    private HashMap<Integer,Integer> hashMap  = new HashMap<>();
    int preIndex =0;


    public TreeNode buildTree(int [] preorder,int [] inorder) {
        //因为需要用到中序序列的下标，所以用一个hashmap来存下标，会方便很多，不用很多循环来获得坐标
        //因为代码不能像人工那样，从中序序列中找到根节点之后，人可以一眼就对应
        //到前序序列的根节点，程序不行，程序找到中序序列的根节点的值之后，利用这个
        //值，去对应到前序序列的值，再找到值对应的下标，把下标传入递归函数的参数。
        //这里要注意，hashmap是无序的，存入之后。
        for (int i=0;i<inorder.length;i++){
            hashMap.put(inorder[i],i);
        }
        return ConstructBinaryTree2(0,preorder.length-1,
                0,inorder.length-1,preorder,inorder);
//        return ConstructBinaryTree(0,inorder.length-1,preorder,inorder);
    }
    //重构二叉树传入的参数只要是有序数组的首和尾即可。传入preorder和inorder是为了不设置全局变量
    public TreeNode ConstructBinaryTree(int left,int right,int [] preorder,int [] inorder) {

        //这里的left和right指的都是中序序列中的坐标，同时递归出口要想明白，只有left大
        // 于right的时候，才返回null，而不是left ==right的情况，left ==right时应该
        //返回对应的剩下的节点，而不是null。这个递归出口这里花了很多时间，要留心。
        //递归出口属于重中之中，一定要想明白
        if(left > right){
            return null;
        }
        TreeNode rootNode = new TreeNode(preorder[preIndex]);
        int  indexRootInOrder=hashMap.get(preorder[preIndex]);
        //这里的preIndex可以参考上面的注释讲解。
        preIndex++;
        rootNode.left = ConstructBinaryTree(left,indexRootInOrder-1,preorder,inorder);
        rootNode.right = ConstructBinaryTree(indexRootInOrder+1,right,preorder,inorder);
        return rootNode;
    }

    public TreeNode ConstructBinaryTree2(int preLeft,int preRight,int inLeft,int inRight,
                                         int [] preorder,int [] inorder) {
        if(preLeft > preRight||inLeft>inRight){
            return null;
        }
        TreeNode rootNode = new TreeNode(preorder[preLeft]);
        int  pivotIndex=hashMap.get(preorder[preLeft]);
/**
 *前序遍历 preorder = 
 * [1  ,           2,         4,       5,           8,      9,                                                 3,        6,          10,      7]
 *  |               |                                            |                                                   |                                 |
 *preLeft   preLeft+1    (pivotIndex-1-inLeft)+(preLeft+1)   (pivotIndex-1-inLeft)+(preLeft+1)+1   preRight
 * 中序遍历 inorder =
 * [4,        2,8,         5, 9,                                 1,                                  6,             10,3,                        7]
 *  |                              |                                  |                                    |                                              |
 *inLeft               pivotIndex-1                   pivotIndex                 pivotIndex+1                               inRight
 */
        rootNode.left = ConstructBinaryTree2(
                //前序左子树的尾坐标应该是(pivotIndex-1-inLeft)+(preLeft+1)=pivotIndex-inLeft+preLeft
                //把两个1消掉。
                preLeft+1,pivotIndex-inLeft+preLeft,
                inLeft,pivotIndex-1,
                preorder,inorder);
        rootNode.right = ConstructBinaryTree2(
                pivotIndex-inLeft+preLeft+1,preRight,
                pivotIndex+1,inRight,
                    preorder,inorder);
        return rootNode;
    }

    public static void main(String[] args) {
        int[] preorder={3,9,20,15,7};
        int[] inorder={9,3,15,20,7};
        new TreebuildTree105LeetCode().buildTree(preorder,inorder);
    }
}
