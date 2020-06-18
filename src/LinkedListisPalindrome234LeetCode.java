/**
* 请判断一个链表是否为回文链表。
* 示例 1:
* 输入: 1->2
* 输出: false
* 示例 2:
* 输入: 1->2->2->1
* 输出: true
* 进阶：
* 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * @author LemonLin
 * @Description :LinkedListisPalindrome234LeetCode
 * @date 20.4.6-22:23
 * 思路：主要难度在于O(n) 时间复杂度和 O(1) 空间复杂度，链表又不能从尾部开始倒着
 * 遍历，所以考虑在中间节点到末尾节点这一端反转，然后从头开始和中间节点开始遍历比较
 * 是否为回文链表。
 * bug1:
 * 输入:
 * [1,0,0]
 * 输出
 * true
 * 预期结果
 * false
 */
public class LinkedListisPalindrome234LeetCode {
    /**
     * Definition for singly-linked list.
     *      */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public boolean isPalindrome(ListNode head) {
        //只有一个节点或者有两个节点的情况下。
        if (head ==null || head.next==null){
            return true;
        }

        //只有两个结点的情况下
        if (head.next.next==null){
            if (head.val!=head.next.val){
                return  false;
            }else {
                return true;
            }
        }

        //先获取链表中间节点
        ListNode middleNode = middleNode(head);
        //反转链表并返回头结点。
        ListNode reverseNode = reverseList(middleNode);
        //这样可以一个从头开始遍历，一个从尾部开始遍历比较是否为回文链表
        while (head!=middleNode){
            if (head.val!=reverseNode.val){
                return false;
            }
            head = head.next;
            reverseNode = reverseNode.next;
        }
        return true;
    }

    public ListNode middleNode(ListNode head) {
        if (head == null)return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        //这里易错，应该用这几个一起判断，不能光判断fast.next !=null
        while (fast!=null&&fast.next!=null&&fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.next;
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

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(0);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(1);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = null;
        System.out.println(new LinkedListisPalindrome234LeetCode().isPalindrome(listNode1));
    }
}
