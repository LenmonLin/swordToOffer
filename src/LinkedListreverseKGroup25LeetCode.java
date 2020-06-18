/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * @author LemonLin
 * @Description :LinkedListreverseKGroup25LeetCode
 * @date 20.5.16-1:37
 * 思路1参考：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/comments/
 * 思路2参考：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/kge-yi-zu-fan-zhuan-lian-biao-by-powcai/
 * 用栈，我们把 k 个数压入栈中，然后弹出来的顺序就是翻转的。K 就是常数，所以满足
 * 空间复杂度为常数的要求
 */
public class LinkedListreverseKGroup25LeetCode {
    /**
     * Definition for singly-linked list.
     * */
     static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

     //方法一，根据评论区的思路
    public ListNode reverseKGroup(ListNode head, int k) {
         ListNode dummy = new ListNode(0);
         dummy.next = head;
         ListNode curNode = head;
         ListNode preNode = dummy;
         ListNode laterNode = null;
         //记录链表长度
        int length = 0;
        while (head!=null){
            length++;
            head=head.next;
        }
        //外层循环记录有几个k
        for (int i=0;i<length/k;i++){
            //内层循环进行K内的翻转
            for (int j=1;j<k;j++){
                laterNode = curNode.next;
                curNode.next = laterNode.next;
                laterNode.next =preNode.next;
                preNode.next =laterNode;
            }
            preNode = curNode;
            curNode = preNode.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;
        ListNode listNode = new LinkedListreverseKGroup25LeetCode().
                reverseKGroup(listNode1, 2);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode= listNode.next;
        }
    }
}
