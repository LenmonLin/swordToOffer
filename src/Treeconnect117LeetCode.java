/**
 * 给定一个二叉树
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节
 * 点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * 示例：
 *图片参考：
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一
 * 个右侧节点，如图 B 所示。
 * 提示：
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 * @author LemonLin
 * @Description :Treeconnect117LeetCode
 * @date 20.2.13-16:14
 * 思路：参考以下链接解法三，
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
 * solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-28/
 * 这题要学会和LeetCode116对比，解决下一行的结点不是按照完美二叉树存在的规律。
 * 总体思路:设置cur,指向已经链接了next的一层，设置dummy指向cur下一层（还没连接next）
 * 的开头，用tail遍历cur下一层（还没连接next）。
 * 第一点，用dummy作为每一层还未连接的哑结点，这样每次遍历完一层已经链接了next
 * 的结点，可以通过cur = dummy.next，更新新的一层的开头位置。
 * 第二点，最开始用tail指向dummy，等到tail.next 指向cur.left或者cur.right 时，
 * dummy.next自然指向了cur.left或者cur.right。下一层自然就链接起来了。
 */
public class Treeconnect117LeetCode {
    /*
// Definition for a Node.*/
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    public Node connect(Node root) {
        Node cur = root;
        Node dummy = new Node();
        Node tail = null;
        //这一个while主要控制层数的遍历，比如第一层，第二层，第三层...
        while (cur!=null){
            //每一层需要重新更新下，否则会出现循环
            dummy.next = null;
            tail = dummy;
            //这一个循环主要控制每一层的结点。比如第一层的第一个结点，第一层的第二个
            // 结点。。。
            while (cur!=null){
                if (cur.left != null){
                    tail.next = cur.left;
                    tail = tail.next;
                }
                if (cur.right!=null){
                    tail.next = cur.right;
                    tail = tail.next;
                }
                cur = cur.next;
            }
            //更新到下一层
            cur = dummy.next;
        }
        return  root;
    }
}
