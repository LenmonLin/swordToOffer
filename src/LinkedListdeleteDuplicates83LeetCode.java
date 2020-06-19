/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * @author LemonLin
 * @Description :LinkedListdeleteDuplicates83LeetCode
 * @date 20.4.17-19:15
 * 思路：参考LeetCode82的解法，用一个全局指针遍历，用一个临时指针temp专门处理
 * 重复元素的循环。这样更具模板性。
 * 不推荐用deleteDuplicates2的解法，用两个不同指针记录重复和不重复的结点，这样的解法
 * 解决不了LeetCode82
 */
public class LinkedListdeleteDuplicates83LeetCode {
    /**
     * Definition for singly-linked list.
     *    */
     static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }
     //推荐这种写法，能够同时解决LeetCode82和LeetCode83，这种解法的思路不是用快慢
     // 指针，而是用一个cur指针控制全局变量，一个临时循环指针temp，来专门处理重复元素
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode temp = null;
        while (cur.next!=null&&cur.next.next != null){
            if (cur.next.val ==cur.next.next.val){
                temp = cur.next;
                while (temp.next!=null&&temp.val==temp.next.val){
                    temp = temp.next;
                }
                cur.next = temp;
            }else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    //虽然这种写法也可以通过，但是不具备普遍性，无法解决LeetCode82
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = head;
        ListNode slow = dummy;
        while (fast!=null){
            while (fast.next!=null&&fast.val==fast.next.val){
                fast = fast.next;
            }
            slow.next = fast;
            slow = fast;
            fast = fast.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;
        ListNode listNode = new LinkedListdeleteDuplicates83LeetCode().deleteDuplicates(listNode1);
        while(listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
