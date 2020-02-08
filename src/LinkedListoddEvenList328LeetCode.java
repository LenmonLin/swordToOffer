/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节
 * 点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为
 * O(nodes)，nodes 为节点总数。
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * @author LemonLin
 * @Description :LinkedListoddEvenList328LeetCode
 * @date 20.2.8-20:34
 * 思路：很直白，多搞几个指针。
 */
public class LinkedListoddEvenList328LeetCode {
    /**
     * Definition for singly-linked list.
     * */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode oddEvenList(ListNode head) {
        if (head==null||head.next==null)return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenStart = head.next;
        while (odd!=null&&odd.next!=null&even!=null&&even.next!=null){
            odd.next=even.next;
            odd=odd.next;
            even.next =odd.next;
            even=even.next;
        }
        odd.next =evenStart;
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new  ListNode(1);
        ListNode l1two = new  ListNode(2);
        ListNode l1three = new  ListNode(3);
        ListNode l1four = new  ListNode(4);
//        ListNode l1five = new  ListNode(5);
        l1.next = l1two;
        l1two.next = l1three;
        l1three.next = l1four;
        l1four.next = null;
//        l1four.next = l1five;
//        l1five.next = null;

        ListNode listNode = new LinkedListoddEvenList328LeetCode().oddEvenList(l1);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode =listNode.next;
        }
    }
}
