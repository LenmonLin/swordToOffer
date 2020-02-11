/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 * @author LemonLin
 * @Description :LinkedListremoveNthFromEnd19LeetCode
 * @date 20.2.11-17:40
 * 参考剑指offer15
 * bug1:
* 输入:
* [1]
* 1
* 输出
* [1]
* 预期结果
* []
 */
public class LinkedListremoveNthFromEnd19LeetCode {
    /**
     * Definition for singly-linked list.
     *      */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //设置哑结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pHead = null;
        ListNode pBehind = null;
        if (head == null || n == 0){
            return null;
        }
        pBehind = head;
        for (int i =1;i<n;i++){
            //这里要注意考虑需要寻找K个，其实可能链表没有k个元素的问题
            if (pBehind.next==null){
                return null;
            }
            //这里弄错了，把pBehind= pBehind.next ;写成了pBehind.next = pBehind;
            pBehind = pBehind.next; //这种情况是左边的指针指向右边的
        }
        pHead = dummy;
        while (pBehind.next!=null){
            pHead = pHead.next;
            pBehind = pBehind.next;
        }
        //pHead指向一个要删除节点的前一个结点
        pHead.next =pHead.next.next;
        //解决bug1：不应该返回head
        return  dummy.next;
    }
}
