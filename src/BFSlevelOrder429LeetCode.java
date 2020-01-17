import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 * 例如，给定一个 3叉树 :
 * 图见：
 * https://leetcode-cn.com/classic/problems/n-ary-tree-level-order-traversal/description/
 * 返回其层序遍历:
 * [
 *      [1],
 *      [3,2,4],
 *      [5,6]
 * ]
 * 说明:
 * 树的深度不会超过 1000。
 * 树的节点总数不会超过 5000。
 * 思路：单纯的广度优先遍历，可参考559的对N叉树的处理方法,
 * 细节：N叉树孩子节点的判空if (node.children!=null&&!node.children.isEmpty())
 * @author LemonLin
 * @Description :BFSlevelOrder429LeetCode
 * @date 20.1.17-17:07
 */
public class BFSlevelOrder429LeetCode {
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
    ArrayList result = new ArrayList();
    public List<List<Integer>> levelOrder(Node root) {
        bfs(root);
        return result;
    }
    public void bfs(Node root){
        if (root == null)return;
        Queue queue = new LinkedList();
        //放进队列
        queue.offer(root);
        while (!queue.isEmpty()){
            int cnt = queue.size();
            ArrayList temp = new ArrayList();
            while (cnt>0){
                //从队列中取出节点
                Node node =(Node) queue.poll();
                temp.add(node.val);
                if (node.children!=null&&!node.children.isEmpty()) {
                    for (int i = 0; i < node.children.size(); i++) {
                        queue.offer(node.children.get(i));
                    }
                }
                cnt--;
            }
            result.add(temp);
        }
    }
    public static void main(String[] args) {
        BFSlevelOrder429LeetCode bfSlevelOrder429LeetCode
                = new BFSlevelOrder429LeetCode();
        Node node1 = bfSlevelOrder429LeetCode.new Node(1);
        Node node2 = bfSlevelOrder429LeetCode.new Node(2);
        Node node3 = bfSlevelOrder429LeetCode.new Node(3);
        Node node4 = bfSlevelOrder429LeetCode.new Node(4);
        Node node5 = bfSlevelOrder429LeetCode.new Node(5);
        Node node6 = bfSlevelOrder429LeetCode.new Node(6);
        node1.children  = new ArrayList<>();
        node1.children.add(node2);
        node1.children.add(node3);
        node1.children.add(node4);
        node3.children = new ArrayList<>();
        node3.children.add(node5);
        node3.children.add(node6);
        List<List<Integer>> lists = new BFSlevelOrder429LeetCode().levelOrder(node1);
        for (List list:lists){
            System.out.println("====");
            for (int i=0;i<list.size();i++){
                System.out.println(list.get(i));
            }
        }
    }
}
