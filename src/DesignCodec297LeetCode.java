import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据
 * 存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反
 * 方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法
 * 执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序
 * 列化为原始的树结构。
 * 示例: 
 * 你可以将以下二叉树：
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格
 * 式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应
 * 该是无状态的。
 * @author LemonLin
 * @Description :DesignCodec297LeetCode
 * @date 20.6.16-15:24
 * 思路：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/liang-chong-jie-fa-by-jason-2-13/
 * 序列化：用先序遍历树。
 * 其序列化后为， 1, 2, # ,# ,3 ,4, #, # ,5 ,# ,#,
 * 可以发现，#标识的NULL节点，紧跟在其父节点的后面。
 * 反序列化：
 * 观察序列化结构，除了根节点，其他节点的空指针一定是连续排在父节点的后面。
 * 先重建根节点，如果是NULL节点，返回。如果是数字节点，递归重建左子树。之后，再
 * 重建右子树。利用把序列化的字符串转换为字符串数组。然后遍历数组
 */
public class DesignCodec297LeetCode {
    /**
     * Definition for a binary tree node.
     *  */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        return serializeHelper(root,stringBuilder);
    }
    private String serializeHelper(TreeNode root,StringBuilder stringBuilder){
        if(root==null){
            stringBuilder.append("#,");
        }else {
            //添加","是为了在反序列化的时候，能够方便分隔。
            stringBuilder.append(root.val+",");
            serializeHelper(root.left,stringBuilder);
            serializeHelper(root.right,stringBuilder);
        }
        return stringBuilder.toString();
    }


    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(",")));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals("#")) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }


    // 这种写法要注意index只能用全局变量，这样才能顺序遍历反序列化下去，因为当反序
    // 列化遍历data的时候，如果是用的局部变量，拿main函数中的树举例，当下标为2，
    // 元素为#时，也就是6的左子树遍历结束，进入6的右子树的时候，index如果是局部变
    // 量，下标又变为2了，这个就有问题，下标应该是3，对应元素是7才对。所以应该用全局
    // 变量，或者是具有全局性质的队列。
    int index =0;
    public TreeNode deserialize2(String data) {
        String [] strings = data.split(",");
        return deserializeHelper2(strings);
    }
    private TreeNode deserializeHelper2(String [] strings){
        String  temp = strings[index] ;
        index++;
         if (temp.equals("#")||index>=strings.length){
            return null;
        }

        TreeNode root  = new TreeNode(Integer.valueOf(temp));
        root.left = deserializeHelper2(strings);
        root.right=deserializeHelper2(strings);
        return root;
    }
    // Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
    public static void main(String[] args) {
        /**
         *                  8
         *             6       10
         *          null   7    9    11
         */
        TreeNode treeNodeCommon1 = new TreeNode(8);
        TreeNode treeNodeCommon2 = new TreeNode(6);
        TreeNode treeNodeCommon3 = new TreeNode(10);
//        TreeNode treeNodeCommon4 = new TreeNode(5);
        TreeNode treeNodeCommon5 = new TreeNode(7);
        TreeNode treeNodeCommon6 = new TreeNode(9);
        TreeNode treeNodeCommon7 = new TreeNode(11);
//        TreeNode treeNodeCommon8 = new TreeNode(11);
//        TreeNode treeNodeCommon9 = new TreeNode(11);

        treeNodeCommon1.left = treeNodeCommon2;
        treeNodeCommon1.right = treeNodeCommon3;
        treeNodeCommon2.left = null;
        treeNodeCommon2.right = treeNodeCommon5;

        treeNodeCommon3.left = treeNodeCommon6;
        treeNodeCommon3.right = treeNodeCommon7;
        String string = new DesignCodec297LeetCode().serialize(treeNodeCommon1);
        System.out.println(new DesignCodec297LeetCode().deserialize(string));
    }
}
