/**
 * 反转一个单链表。
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * @author LemonLin
 * @Description :LinkedListreverseList206LeetCode
 * @date 20.2.7-16:02
 * 思路：用两个指针记录一前一后记录目标链表，进行反转.
 */
public class LinkedListreverseList206LeetCode {
    /**
     * Definition for singly-linked list.
     * */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode reverseList(ListNode head) {
        ListNode preCur = null;
        ListNode cur = head;
        //用来临时记录cur之后的节点，这一步很关键，仔细体会
        ListNode temp = null;
        while (cur!=null){
            //记录当前节点的下一个节点
            temp = cur.next;
            //然后将当前节点指向pre
            cur.next = preCur;
            //pre和cur节点都前进一位
            preCur =cur;
            cur =temp;
        }
        //主要这里返回的是preCur而不是Cur,因为Cur指针在退出循环时为空。preCur指向
        // 了最后一个节点
        return preCur;
    }
}
