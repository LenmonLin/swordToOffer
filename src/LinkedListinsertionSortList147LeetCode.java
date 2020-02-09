/**
 * 对链表进行插入排序。
 *动画链接：https://leetcode-cn.com/problems/insertion-sort-list/
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用
 * 黑色表示）。每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入
 * 到已排好序的链表中。
 * 插入排序算法：
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的
 * 位置，并将其插入。重复直到所有输入数据插入完为止。
 * 示例 1：
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * @author LemonLin
 * @Description :LinkedListinsertionSortList147LeetCode
 * @date 20.2.8-22:31
 */
public class LinkedListinsertionSortList147LeetCode {
    /**
     * Definition for singly-linked list.
     * */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode sorted = dummy;
        ListNode unSorted = head;
        ListNode temp =null;
        while (unSorted!=null){
            while (sorted.next!=null &&unSorted.val>sorted.next.val){
                sorted= sorted.next;
            }
            temp = unSorted.next;
            //这下面两行代码很关键，自己就这两行没写对，一直绕晕了。
            unSorted.next = sorted.next;
            sorted.next =unSorted;
            sorted=dummy;
            unSorted = temp;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new  ListNode(1);
        ListNode l1two = new  ListNode(2);
        ListNode l1three = new  ListNode(2);
        ListNode l1four = new  ListNode(3);
        ListNode l1five = new  ListNode(3);
        ListNode l1six = new  ListNode(6);
        ListNode l1seven = new  ListNode(78);
        l1.next = l1two;
        l1two.next = l1three;
        l1three.next = l1four;
//        l1four.next = null;
        l1four.next = l1five;
        l1five.next =l1six;
        l1six.next =l1seven;
        l1seven.next = null;
        ListNode listNode = new LinkedListinsertionSortList147LeetCode().insertionSortList(l1);
        while (listNode!= null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
