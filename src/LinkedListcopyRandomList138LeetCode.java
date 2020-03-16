/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何
 * 节点或空节点。
 * 要求返回这个链表的 深拷贝。 
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个
 *  [val, random_index] 表示：
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，
 * 则为  null 。
 * 示例 1：
 * 图片参考：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 * 图片参考：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 * 图片参考：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 * @author LemonLin
 * @Description :LinkedListcopyRandomList138LeetCode
 * @date 20.2.11-17:26
 * 思路：参考剑指offer26
 *  解题思路：(分成三轮遍历)
 *   1、在旧链表中创建新链表，此时不处理新链表的random指针；
 *        遍历链表，复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面；
 *   2、根据旧链表的random指向结点，初始化新链表的兄弟结点
 *         重新遍历链表，复制老结点的随机指针给新结点
 *   3、从旧链表中拆分得到新链表
 *          拆分链表，将链表拆分为原链表和复制后的链表
 */
public class LinkedListcopyRandomList138LeetCode {
    /*
// Definition for a Node.
*/
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head==null){
            return null;
        }
        //遍历链表，复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面
        Node currentNode = head;
        while (currentNode!=null){
            Node cloneNode = new Node(currentNode.val);
            cloneNode.next = currentNode.next;
            currentNode.next = cloneNode;
            currentNode = cloneNode.next;
        }
        //重新遍历链表，复制老结点的随机指针给新结点
        currentNode = head;
        while (currentNode!= null){
            //注意这里有两个易错点，一个是必须判断currentNode.random==null
            //一个是应该将currentNode.random.next赋值给currentNode.next.random，而不
            // 是currentNode.random，这个容易想错
            if (currentNode.random == null){
                currentNode.next.random =null;
            }else {
                currentNode.next.random = currentNode.random.next;
            }
            currentNode = currentNode.next.next;
        }
        //拆分链表，将链表拆分为原链表和复制后的链表
        currentNode = head;
        Node pCloneHead  = head.next;

        while (currentNode!= null){
            //注意这里cloneNode 要放在循环里面
            Node cloneNode = currentNode.next;
            currentNode.next = cloneNode .next;

            if (cloneNode .next ==null){
                cloneNode .next =null;
            }else {
                cloneNode .next = cloneNode .next.next;
            }
            currentNode= currentNode.next;
        }
        return pCloneHead;
    }
}
