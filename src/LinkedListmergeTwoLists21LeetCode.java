/**
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所
 * 有节点组成的。 
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * @author LemonLin
 * @Description :LinkedListmergeTwoLists21LeetCode
 * @date 20.5.3-17:33
 * 参考：LeetCode23,以及：https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-you-xu-lian-biao-bi-xu-miao-dong-by-sweeti/
 */
public class LinkedListmergeTwoLists21LeetCode {
    /**
     * Definition for singly-linked list.
     *   */
     static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        //哑结点的设置是为了最后返回处理方便
        ListNode dummy = new ListNode(-1);
        ListNode curNode = dummy;
        while (l1!=null && l2!=null){
            ListNode node = null;
            if (l1.val<l2.val){
                 node= l1;
                //把当前链表的下一位后移。
                 l1 = l1.next;
            }else {
                node = l2;
                //把当前链表的下一位后移。
                l2 = l2.next;
            }
            curNode.next = node;
            curNode = curNode.next;
        }
        curNode.next = l1 == null? l2: l1;
        return dummy.next;
    }

    public static void main(String[] args) {
         ListNode listNode1 = new ListNode(1);
         ListNode listNode2 = new ListNode(2);
         ListNode listNode3 = new ListNode(4);
         ListNode listNode11 = new ListNode(1);
         ListNode listNode22 = new ListNode(3);
         ListNode listNode33 = new ListNode(4);
         listNode1.next = listNode2;
         listNode2.next = listNode3;
         listNode3.next = null;
         listNode11.next = listNode22;
         listNode22.next = listNode33;
         listNode33.next = null;
        System.out.println(new LinkedListmergeTwoLists21LeetCode().
                mergeTwoLists(listNode1, listNode11));
    }
}
