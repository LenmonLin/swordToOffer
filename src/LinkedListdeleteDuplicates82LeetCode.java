/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中没有重复出现的数字。
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 * @author LemonLin
 * @Description :LinkedListdeleteDuplicates82LeetCode
 * @date 20.2.7-15:57
 * 思路：链表的题目看似简单，其实难得一批，细节太恶心了。
 * 最后放弃，看了题解,参考：
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 * solution/java-ya-jie-dian-fei-di-gui-rong-yi-li-jie-yong-sh/
 * 有哪些点我没做好导致没写出来？
 * 1、设置哑结点，哑结点要指向head。哑结点指针不要动，后期返回还需要。
 * 2、关键，设置cur指针指向哑结点，从哑结点开始遍历
 * 3、关键是比较cur.next.val ==cur.next.next.val,和第二点相辅相成。
 * 4，找到相等点的时候，需要设置一个临时指针来记录相等的点temp，这个也是关键
 * bug1:
 * [1,1]
 */
public class LinkedListdeleteDuplicates82LeetCode {
    /**
     * Definition for singly-linked list.
     * */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode deleteDuplicates(ListNode head) {
        //设置哑结点，方便处理
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode temp = null;
        while (cur!=null){
            //注意这里是while不是if,这个bug隐藏的很深，找了半天
            while (cur.next!=null&&cur.next.next!=null&&cur.next.val ==cur.next.next.val){
                temp = cur.next;
                //这个循环是解题的关键
                while (temp!=null&&temp.next!=null&&temp.val == temp.next.val){
                    temp = temp.next;
                }
                //退出循环时，temp指向重复节点的最后一个，同时把cur保留在开头，这个
                // 很关键
                cur.next = temp.next;
            }
            cur=cur.next;
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
        ListNode l1seven = new  ListNode(7);
        l1.next = l1two;
        l1two.next = l1three;
        l1three.next = l1four;
//        l1four.next = null;
        l1four.next = l1five;
        l1five.next =l1six;
        l1six.next =l1seven;
        l1seven.next = null;
        ListNode listNode = new LinkedListdeleteDuplicates82LeetCode().deleteDuplicates(l1);
        while (listNode!= null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
