/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等
 * 于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 示例:
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 * @author LemonLin
 * @Description :DoublePointerpartition86LeetCode
 * @date 20.2.2-22:30
 * 思路：比较直接，可以用两个指针，slow指针指向要插入的位置，fast指针去寻找需要
 * 插入的节点。
 * bug1:
 * [1,1]
 * 2
 * 超出时间限制
 * 自己的解法实现太麻烦了，太乱了。官方题解的算法比较简单直接，同时哑结点的设计也
 * 十分巧妙。
 * 参考：https://leetcode-cn.com/problems/partition-list/solution/
 * fen-ge-lian-biao-by-leetcode/
 * 设置两个指针，分别控制两个链表，第一个链表比x小，比X大的值重新放在第二个链表上，
 * 都遍历完毕，直接把第二个链表接到第一个链表后面即可。
 */
public class DoublePointerpartition86LeetCode {
    /**
     * Definition for singly-linked list.
     *  */
   public static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }
    //自己的想法写的，有bug,可不看，留作存档。
    public ListNode partition2(ListNode head, int x) {
       int count=0;
       ListNode temp =head;
       while (temp!=null){
           temp=temp.next;
           count++;
       }
       if (count==1||count==0){
           return head;
       }
        ListNode slow = head;
        ListNode fast = head.next;
        ListNode prefast= head;
        while (fast!=null){
            if (fast.val>=x){
                prefast = prefast.next;
                fast=fast.next;
            }else {
//                if (slow.val<x){
//                    slow=slow.next;
//                    if (slow==fast){
//                        prefast=prefast.next;
//                        fast=fast.next;
//                    }
//                    continue;
//                }
                if(slow.val>=x&&slow==head){
                    prefast.next=fast.next;
                    fast.next=head;
                    head=fast;
                    fast=prefast.next;
                    continue;
                }
                prefast.next=fast.next;
                fast.next=slow.next;
                slow.next=fast;
                fast=prefast.next;
            }
        }
        return head;
    }

    public ListNode partition(ListNode head,int x){
       //设置哑结点
       ListNode beforeHead = new ListNode(0);
       ListNode afterHead = new ListNode(0);

       //设置两个链表
       ListNode before = beforeHead;
       ListNode after = afterHead;

       while (head!=null){
           //如果大于x,放入after链表中
           if (head.val>=x){
               after.next=head;
               after = after.next;
           }else {
               before.next = head;
               before = before.next;
           }
           head = head.next;
        }
       after.next=null;
       before.next=afterHead.next;
       return beforeHead.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode Two = new ListNode(4);
        ListNode Three = new ListNode(3);
        ListNode Four = new ListNode(2);
        ListNode Five = new ListNode(5);
        ListNode Six = new ListNode(2);
        head.next=Two;
        Two.next=Three;
        Three.next=Four;
        Four.next=Five;
        Five.next=Six;
        Six.next=null;
        ListNode partition = new DoublePointerpartition86LeetCode().
                partition(head, 3);
        while (partition!=null){
            System.out.println(partition.val);
            partition=partition.next;
        }
    }
}
