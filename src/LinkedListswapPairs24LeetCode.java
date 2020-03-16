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
 * 我们利用一个stack，然后不断迭代链表，每次取出两个节点放入stack中，再从stack中
 * 拿出两个节点。借助stack后进先出的特点，放进去的时候是1,2。拿出来的时候就是2，1
 * 两个节点了。再把这两个节点串联起来，重复这个逻辑遍历完整个链表，就可以做到两两反
 * 转的效果了。虽然用到了stack，但因为只存了两个元素，所以空间复杂度还是O(1)，时
 * 间复杂度是O(n)。
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
        //这一步把head指向了temp,即使后期temp怎么变，head指向的都是最原始的temp，
        // 结果返回head.next即可。这一步不好看懂。这一步操作，主要是为了最后返回结果。
        //这一步，head指向的是要新生成的链表，原来的链表开头保存到了cur，所以不用太慌。
        head = temp;
        while (cur!=null&&cur.next!=null){
            //把第一个第二个节点存入栈中，这里cur遍历的是交换前的链表
            stack.addLast(cur);
            stack.addLast(cur.next);
            //cur移到第三个节点下个循环再来遍历操作。
            cur = cur.next.next;
            //temp其实就是指向交换后的链表，遍历的是交换后的链表
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
