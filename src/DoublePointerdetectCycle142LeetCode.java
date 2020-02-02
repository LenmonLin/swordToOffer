/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置
 * （索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 说明：不允许修改给定的链表。
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 图片见：https://leetcode-cn.com/classic/problems/linked-list-cycle-ii/description/
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 图片见：https://leetcode-cn.com/classic/problems/linked-list-cycle-ii/description/
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 * 图片见：https://leetcode-cn.com/classic/problems/linked-list-cycle-ii/description/
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 * @author LemonLin
 * @Description :DoublePointerdetectCycle142LeetCode
 * @date 20.2.2-17:42
 * 思路：和剑指offer上题目一样，但是要会证明就有点难度。
 * 参考：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/
 * linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
 * bug1:
 * []
 * -1
 * java.lang.NullPointerException
 * bug2：
 * 最后执行的输入：
 * [1,2]
 * -1
 * java.lang.NullPointerException
 * bug3:
 * [1]
 * -1
 * java.lang.NullPointerException
 * bug4:
 * [-21,10,17,8,4,26,5,35,33,-7,-16,27,-12,6,29,-12,5,9,20,14,14,2,13,-24,21,23,-21,5]
 * -1
 * java.lang.NullPointerException
 */
public class DoublePointerdetectCycle142LeetCode {
    /**
     * Definition for singly-linked list.
     *      */
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public ListNode detectCycle(ListNode head) {
        //处理bug1
        if (head==null)return null;
        ListNode fast = head;
        ListNode slow = head;
        ListNode temp =head;
        //处理bug4,不能写成fast.next!=null,而且处理bug2和bug3的代码要放到while
        // 循环里面。
        while (fast!=null){
            //处理bug2
            if (fast.next==null)return null;
            //处理bug3
            if (fast.next.next==null)return null;
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast){
                while (slow!=temp){
                    slow=slow.next;
                    temp=temp.next;
                }
                return temp;
            }
        }
        return null;
    }
}
