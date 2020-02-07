/**
* 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方
 * 式存储的，并且它们的每个节点只能存储 一位 数字。
* 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
* 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
* 示例：
* 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
* 输出：7 -> 0 -> 8
* 原因：342 + 465 = 807
 * @author LemonLin
 * @Description :LinkedListaddTwoNumbers2LeetCode
 * @date 20.2.7-15:48
 * 思路：相对直观，直接遍历然后处理位数相加即可。这里设置一个preResult记录整个遍历
 * 过程。
 * 这里存在几种情况：
 * l1,l2长度一模一样，preResult指向l1即可，把l1+l2的结果存到l1;
 * l1,比l2长，则，preResult继续指向l1即可。
 * l1,比l2短，则，preResult要切换指向l2即可。
 * bug1:
 * 输入:
 * [9,9]
 * [9]
 * 输出
 * [8,1]
 * 预期结果
 * [8,0,1]
 */
public class LinkedListaddTwoNumbers2LeetCode {
    /**
     * Definition for singly-linked list.
     * */
      public static  class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int temp=0;
        //表示进位
        int carry =0;
        ListNode preResult = new ListNode(0);
        preResult.next = l1;
        ListNode result = preResult.next;
        while (l1!=null&&l2!= null){
            temp =l1.val+l2.val+carry;
            carry = temp/10;
            l1.val =temp%10;
            l1=l1.next;
            l2 =l2.next;
            preResult = preResult.next;
            if (l1==null&&l2!=null){
                preResult.next=l2;
            }
        }
        // l1,比l2短，则，preResult要切换指向l2即可。
        while (l1==null&&l2!=null){
            temp = l2.val+carry;
            carry = temp/10;
            l2.val=temp%10;
            preResult = preResult.next;
            l2 =l2.next;
        }
        //l1,比l2长，则，preResult继续指向l1即可。
        while (l2==null&&l1!=null){
            temp = l1.val+carry;
            carry = temp/10;
            l1.val=temp%10;
            //解决bug1:
            preResult = preResult.next;
            l1 =l1.next;
        }
        //处理l1,l2一样长，但是最后一位还有进位的情况
        if (carry!=0){
            ListNode last = new ListNode(carry);
            preResult.next = last;
            last.next = null;
        }
        return result ;
    }

    public static void main(String[] args) {
          ListNode l1 = new  ListNode(9);
          ListNode l1two = new  ListNode(9);
//          ListNode l1three = new  ListNode(5);
//          ListNode l1four = new  ListNode(3);
          l1.next = l1two;
          l1two.next = null;
//          l1two.next = l1three;
//          l1three.next = l1four;
//          l1three.next = null;
//          l1four.next = null;
        ListNode l2 = new  ListNode(9);
//        ListNode l2two = new  ListNode(6);
//        ListNode l2three = new  ListNode(4);
//        ListNode l2four = new  ListNode(4);
//        l2.next = l2two;
        l2.next = null;
//        l2two.next = l2three;
//        l2three.next = null;
//        l2three.next = l2four;
//        l2four.next = null;
        ListNode listNode = new LinkedListaddTwoNumbers2LeetCode().addTwoNumbers(l1, l2);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
