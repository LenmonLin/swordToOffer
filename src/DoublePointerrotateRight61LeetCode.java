/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * @author LemonLin
 * @Description :DoublePointerrotateRight61LeetCode
 * @date 20.2.2-10:47
 * 思路：其实就是要找到要断开的节点位置，然后把原先第一个节点接到原先最后一个节点，
 * 在要断开的节点位置断开即可。考虑用双指针。快的指针从第一个节点开始先走k步，注意
 * 示例二，这里要到最后一个节点时要循环回去第一个节点，然后两个指针再开始同步走，
 * 如果快的指针到达最后一个节点，慢的指针就到达了要断开的位置。这里主要要处理输入
 * 的K很大的情况下，要把K通过求余稳定在一个循环之类，否则会发生时间超时情况
 * bug1:
 *[1,2,3]
 * 2000000000
 * 超出时间限制。
 */
public class DoublePointerrotateRight61LeetCode {
    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head==null)return null;

        //为了解决bug1,需要对k进行取余处理。求出链表节点的总长度，然后求余。如果数
        // 字太大容易超时，所以要把数字缩小至一个循环之类。
        ListNode temp = head;
        int count=0;
        while (temp.next!=null){
            temp=temp.next;
            count++;
        }
        count++;
        //求余后的k就把遍历缩小至一个循环之类。
        k=k%count;
        ListNode slow =head;
        ListNode fast = head;
        while (k>0){
            //循环遍历到头结点
            if (fast.next==null){
                fast=head;
            }else {
                fast=fast.next;
            }
            k--;
        }
        //开始快慢指针同时遍历，当fast到达尾节点，slow就到了要断开节点位置
        while (fast.next!=null){
                fast=fast.next;
                slow = slow.next;
        }
        //fast尾部链接到原来链表的头结点
        fast.next = head;
        //把slow的下个节点最为新链表的头结点
        head =slow.next;
        //把原链表从中间断开
        slow.next =null;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode Two = new ListNode(1);
        ListNode Three = new ListNode(2);
//        ListNode Four = new ListNode(4);
//        ListNode Five = new ListNode(5);
        head.next=Two;
        Two.next=Three;
        Three.next=null;
//        Three.next=Four;
//        Four.next=Five;
//        Five.next=null;
        int k=2000000000;
        ListNode result = new DoublePointerrotateRight61LeetCode().rotateRight(head, k);
        while (result.next!=null){
            System.out.println(result.val);
            result=result.next;
        }
        System.out.println(result.val);
    }
}
