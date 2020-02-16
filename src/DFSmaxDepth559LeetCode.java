import java.util.ArrayList;
import java.util.List;
/**
 * 给定一个 N 叉树，找到其最大深度。
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * 例如，给定一个 3叉树 :
 *图片见：
 * https://leetcode-cn.com/classic/problems/maximum-depth-of-n-ary-tree/description/
 * 我们应返回其最大深度，3。
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 * 思路：使用DFS，同时使用一个局部变量记录深度，一个全局变量记录最大深度。进行比较。
 * 解题bug:为啥判断只能用 if (root.children.isEmpty())，不能用 if (root.children==null),
 * 另外例子的输入也没看懂为啥是1，null,这个null指什么。稀里糊涂的就过了。
 * 输入
 * [1,null,3,2,4,null,5,6]
 * 输出
 * 3
 * 预期结果
 * 3
 * @author LemonLin
 * @Description :DFSmaxDepth559LeetCode
 * @date 20.1.15-15:51
 */
public class DFSmaxDepth559LeetCode {
    /*
// Definition for a Node.
*/
    class  Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    int maxDepth=0;
    public int maxDepth(Node root) {
        if (root == null) return 0;
        int depth =0;
        dfs(root,depth);
        return maxDepth;
    }
    public void dfs(Node root,int depth){
        if (root==null)return;
        //depth++不能写在dfs(root.children.get(i),depth+1);中，因为 if (depth>maxDepth){
        //需要判断，所以必须在这个之前就要做判断，写在前面还有传入root根节点的时候，就
        //需要加1。否则root根节点无法加一。
        depth++;
        if (root.children.isEmpty()){
            if (depth>maxDepth){
                maxDepth = depth;
            }
        }
        if (!root.children.isEmpty()){
            for (int i=0;i<root.children.size();i++){
                dfs(root.children.get(i),depth);
            }
        }
    }

    public static void main(String[] args) {
        DFSmaxDepth559LeetCode dfSmaxDepth559LeetCode
                = new DFSmaxDepth559LeetCode();
        Node node1 = dfSmaxDepth559LeetCode.new Node(1);
        Node node2 = dfSmaxDepth559LeetCode.new Node(2);
        Node node3 = dfSmaxDepth559LeetCode.new Node(3);
        Node node4 = dfSmaxDepth559LeetCode.new Node(4);
        Node node5 = dfSmaxDepth559LeetCode.new Node(5);
        Node node6 = dfSmaxDepth559LeetCode.new Node(6);
        node1.children  = new ArrayList<>();
        node1.children.add(node2);
        node1.children.add(node3);
        node1.children.add(node4);
        node3.children = new ArrayList<>();
        node3.children.add(node5);
        node3.children.add(node6);
        System.out.println(new DFSmaxDepth559LeetCode().maxDepth(node1));
    }
}
