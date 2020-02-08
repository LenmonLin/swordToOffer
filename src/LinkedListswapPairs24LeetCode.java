import java.util.LinkedList;
/**
* 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
* 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
* 示例:
* 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * @author LemonLin
 * @Description :LinkedListswapPairs24LeetCode
 * @date 20.2.7-15:52
 * 思路：先把偶数位置的节点单独组成一个链表。然后两个链表链接。
 * 经过一番非常无奈的挣扎，确定上述方法只是存在理论可能，实际操作起来，各种困难。
 * 非常尴尬，放弃。
 * 参考：
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/
 * dong-hua-yan-shi-24-liang-liang-jiao-huan-lian-bia/
 */
public class LinkedListswapPairs24LeetCode {
    /**
     * Definition for singly-linked list.
     * */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        LinkedList<ListNode> stack = new LinkedList();
        ListNode temp = new ListNode(0);
        ListNode cur = head;
        //这一步存起来了temp,即使后期temp怎么变，head放的都是最原始的temp，结果
        // 返回head.next即可
        head = temp;
        while (cur!=null&&cur.next!=null){
            stack.addLast(cur);
            stack.addLast(cur.next);
            cur = cur.next.next;
            temp.next = stack.pollLast();
            temp=temp.next;
            temp.next = stack.pollLast();
            temp = temp.next;
        }
        //注意边界条件，当链表长度是奇数时，cur就不为空
        if(cur!=null) {
            temp.next = cur;
        } else {
            temp.next = null;
        }
        return head.next;
    }

    //自己最原始的思路写的有bug,可以不看，只是留着存档。
    public ListNode swapPairs2(ListNode head) {
        ListNode tempNode1 = head;
        ListNode tempNode2 = head.next;
        ListNode listNode2 = tempNode2;
        ListNode headMove = head;
        while (headMove!=null){
            tempNode1=headMove;
            tempNode2 = headMove.next;
            headMove =headMove.next.next;
        }
        while (tempNode1!=null&&tempNode1.next!=null&&tempNode1.next.next!=null){
            tempNode1.next = tempNode1.next.next;
            tempNode1=tempNode1.next;
        }
        while (tempNode2!=null&&tempNode2.next!=null&&tempNode2.next.next!=null){
            tempNode2.next=tempNode2.next.next;
            tempNode2=tempNode2.next;
        }
        return listNode2;
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
//        l1four.next = null;
        l1four.next = l1five;
        l1five.next = null;
        ListNode listNode = new LinkedListswapPairs24LeetCode().swapPairs(l1);
        while (listNode!= null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
