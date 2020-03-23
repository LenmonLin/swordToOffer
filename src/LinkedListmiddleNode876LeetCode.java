/**
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 * 示例 1：
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 * 示例 2：
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 * 提示：
 * 给定链表的结点数介于 1 和 100 之间。
 * @author LemonLin
 * @Description :LinkedListmiddleNode876LeetCode
 * @date 20.3.23-10:17
 * 思路：用快慢指针，快指针fast走两步，慢指针slow走一步，两个指针同时从设置的哑结
 * 点出发。如果fast.next==null，那么就返回slow.next,次数slow指向的是中间节点的前
 * 一个结点。
 * 这里一个易错点，while (fast!=null&&fast.next!=null&&fast.next.next!=null){
 * 的循环判断条件，容易写漏
 */
public class LinkedListmiddleNode876LeetCode {
    /**
     * Definition for singly-linked list.
     *     */
     public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
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

    public static void main(String[] args) {
        ListNode listNode1= new ListNode(1);
        ListNode listNode2= new ListNode(2);
        ListNode listNode3= new ListNode(3);
        ListNode listNode4= new ListNode(4);
        ListNode listNode5= new ListNode(5);
//        ListNode listNode6= new ListNode(6);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
//        listNode5.next = listNode6;
        listNode5.next = null;
        ListNode listNode = new LinkedListmiddleNode876LeetCode().middleNode(listNode1);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
