/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * @author LemonLin
 * @Description :LinkedListreorderList143LeetCode
 * @date 20.2.7-16:01
 * 思路：遍历到中间节点，然后开始把后半段节点旋转一下，然后把两个链表聚合在一起。
 * 怎么遍历中间节点，用快慢指针，慢指针走一步，快指针走两步，快指针走到末尾，慢
 * 指针就走到了链表中间。
 */
public class LinkedListreorderList143LeetCode {
    /**
     * Definition for singly-linked list.
     * */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return ;
//            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        //主要这里判断fast的条件，要两个同时判断，否则会发生空指针异常
        //如果节点个数是偶数的话，slow 走到的是左端点,否则刚好走到中间节点，也就是
        // 不管奇偶数个结点数，slow都是指向需要旋转起始节点的前一个结点
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast =fast.next.next;
        }
        ListNode listNode2 = reverseList(slow.next);
        slow.next = null;
        ListNode listNode1 = head;
        //把两个链表链接起来。调了两个小时才出来，思路出现了偏差。
        //这个两个链表链接起来的模板要记下来。
        ListNode temp = null;
        while (listNode2!=null){
            temp = listNode2.next;
            listNode2.next = listNode1.next;
            listNode1.next = listNode2;
            listNode1 = listNode2.next;
            listNode2 = temp;
        }
//        return head;
    }
    private ListNode reverseList(ListNode head) {
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

    public static void main(String[] args) {
        ListNode l1 = new  ListNode(1);
        ListNode l1two = new  ListNode(2);
        ListNode l1three = new  ListNode(3);
        ListNode l1four = new  ListNode(4);
        ListNode l1five = new  ListNode(5);
        l1.next = l1two;
        l1two.next = l1three;
        l1three.next = l1four;
        l1four.next = null;
//        l1four.next = l1five;
//        l1five.next = null;
//        ListNode listNode = new LinkedListreorderList143LeetCode().reorderList(l1);
//        while (listNode!=null){
//            System.out.println(listNode.val);
//            listNode = listNode.next;
//        }
    }
}
