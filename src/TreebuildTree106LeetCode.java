import java.util.HashMap;
/**
* * 根据一棵树的中序遍历与后序遍历构造二叉树。
* 注意:
* 你可以假设树中没有重复的元素。
* 例如，给出
* 中序遍历 inorder = [9,3,15,20,7]
* 后序遍历 postorder = [9,15,7,20,3]
 * 预期结果
 *[3,9,20,null,null,15,7]
* 返回如下的二叉树：
* 3
* / \
* 9  20
* /  \
* 15   7
 ** 解题思路：其实就是找根结点，找左子树，右子树下标带入递归函数，步骤如下：
 * 1、后序队列找到根节点；
 * 2、利用后序序列找到的根节点对应到中序序列中，找到对应根节点的左子树和右子树。
 * 3、根据中序序列找到的左子树和右子树到后序序列中找对应左子树的根节点和右子树
 * 的根节点。
 * 总结来说：在后序序列中找根节点，在中序序列中找左子树和右子树。
 *
 *参考LeetCode105的ConstructBinaryTree2解法，放弃以下被{}括起来的解法：
 * {
 *虽然做题思路是这样，但是从代码来看，有一个特别的点，就是第三步，不是继续从左子树
 * 和右子树中到后序序列找对应于的根节点，可以看出根节点代码下标是postIndex，而且
 * 在递归代码逻辑中进行postIndex--，也就是这其中不是利用中序序列分割出来的左子树
 * 和右子树跟着递归从后序序列中找对应的根节点，而是认为后序序列中的每个下标节点
 * 都是某一棵树的根节点，只要把每个节点的作为根节点的情况下，它的左子树和右子树的
 * 关系都建立好，那么就完成了整颗树的建立。
 *
 * 尚未解决的bug:这里有两个疑问，
 * 一、如果根据105题前序和中序构建一个树的解题分析，
 * 既然postIndex下标都是某一棵树的根节点，那为什么实际测试中，如果是按照105题
 * 的写法，把postIndex初始化为0，期间用postIndex++写代码运行结果是错误的。
 * 二、为什么要先构建root.right，再构建root.left.反过来就不行，这个也想不明白。
 *
 * 猜想，但是看各路大神的题解来看，如果代码严格按照解题思路的解决方法，传入参数
 * （中序序列左边界，中序序列右边界，后序序列左边界，后序序列右边界）这种解题方法
 * 没有以上两个想不明白的bug点。
 * }
 *新的解法用的是传入四个下标的参数，后序遍历的首尾坐标，中序遍历的首尾坐标。
 *
 * 后序遍历 preorder = 
 *  [4  ,   8,   9,      5,            2,                                                       10,        6,        7,            3,                    1]
 *   |                                     |                                                         |                                     |                     |
 * postLeft      (pivotIndex-1-inLeft)+(postLeft)    (pivotIndex-1-inLeft)+(postLeft)+1    postRight-1         postRight
 *  中序遍历 inorder =
 *  [4,        2,8,         5, 9,                                 1,                                  6,             10,3,                        7]
 *   |                              |                                  |                                    |                                              |
 * inLeft               pivotIndex-1                   pivotIndex                 pivotIndex+1                               inRight
 *对比LeetCode105，中序遍历的下标坐标都是一样的，只是后序遍历和前序遍历的下标不一样，前序遍历的根节点是从前往后发现的，
 * 后序遍历的根节点是从后往前发现的过程。
 * @author LemonLin
 * @Description :TreebuildTree106LeetCode
 * @date 2019/12/27-20:44
 */
public class TreebuildTree106LeetCode {
    //中序遍历二叉树验证结果
    public void InorderTraversal( TreeNode bt ) {
        if(bt == null){
            return ;
        }
        InorderTraversal(bt.left);
        System.out.println("========"+bt.val);
        InorderTraversal(bt.right);
    }

    public class TreeNode { int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private HashMap<Integer,Integer> hashMap  = new HashMap<>();
    int postIndex ;


    public TreeNode buildTree(int [] inorder,int [] postorder) {
        //因为需要用到中序序列的下标，所以用一个hashmap来存下标，会方便很多，不用
        // 很多循环来获得坐标因为代码不能像人工那样，从中序序列中找到根节点之后，
        // 人可以一眼就对应到后序序列的根节点，程序不行，程序找到中序序列的根节点
        // 的值之后，利用这个值，去对应到后序序列的值，再找到值对应的下标，把下标
        // 传入递归函数的参数。这里要注意，hashmap是无序的，存入之后。
        postIndex = postorder.length-1;
        for (int i=0;i<inorder.length;i++){
            hashMap.put(inorder[i],i);
        }
//        return ConstructBinaryTree(0,inorder.length-1,inorder,postorder);
        return ConstructBinaryTree2(0,postorder.length-1,
                0,inorder.length-1,postorder,inorder);
    }
    //重构二叉树传入的参数只要是有序数组的首和尾即可。传入postorder和inorder是为了不设置全局变量
    public TreeNode ConstructBinaryTree2(int postLeft,int postRight,int inLeft,int inRight,
                                         int [] postorder,int [] inorder) {
        if(postLeft > postRight||inLeft>inRight){
            return null;
        }
        TreeNode rootNode = new TreeNode(postorder[postRight]);
        int  pivotIndex=hashMap.get(postorder[postRight]);
        rootNode.left = ConstructBinaryTree2(
                postLeft,pivotIndex-1-inLeft+postLeft,
                inLeft,pivotIndex-1,
                postorder,inorder);
        rootNode.right = ConstructBinaryTree2(
                pivotIndex-inLeft+postLeft,postRight-1,
                pivotIndex+1,inRight,
                postorder,inorder);
        return rootNode;
    }
    public TreeNode ConstructBinaryTree(int left,int right,int [] inorder,int [] postorder) {
        //这里的left和right指的都是中序序列中的坐标，同时递归出口要想明白，只有left大
        // 于right的时候，才返回null，而不是left ==right的情况，left ==right时应该
        //返回对应的剩下的节点，而不是null。这个递归出口这里花了很多时间，要留心。
        //递归出口属于重中之中，一定要想明白
        if(left > right){
            return null;
        }
        TreeNode rootNode =null;
        int indexRootInOrder =0;
        if (postIndex>=0) {
            rootNode = new TreeNode(postorder[postIndex]);
            indexRootInOrder = hashMap.get(postorder[postIndex]);
        }
        //这里的postIndex可以参考上面的注释讲解。
        postIndex--;
        //注意这里先右后左，还没想清楚是为什么
        rootNode.right = ConstructBinaryTree(indexRootInOrder+1,right,inorder,postorder);
        rootNode.left = ConstructBinaryTree(left, indexRootInOrder - 1 , inorder,postorder);
        return rootNode;
    }
    public static void main(String[] args) {
        int [] inorder={9,3,15,20,7};
        int [] postorder ={9,15,7,20,3};
        //预期输出结果[3,9,20,null,null,15,7]
        TreeNode node = new TreebuildTree106LeetCode().buildTree(inorder,postorder);
        new TreebuildTree106LeetCode().InorderTraversal(node);
    }
}
