import java.util.LinkedList;
/**
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点
 * 只存储单个数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 进阶:
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * 示例:
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
 * @author LemonLin
 * @Description :LinkedListaddTwoNumbers445LeetCode
 * @date 20.2.8-21:06
 * 思路：考虑用两个栈先把链表存入，然后再读出来。问题就转换为和LeetCode2一样.
 * 注意结果是从大数在前，所以还需要一个栈存结果，然后再读出来串成链表
 */
public class LinkedListaddTwoNumbers445LeetCode {
    /**
     * Definition for singly-linked list.
     * */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        LinkedList<ListNode> stack1= new LinkedList<ListNode>();
        LinkedList<ListNode> stack2 = new LinkedList<ListNode>();
        while (l1!= null){
            stack1.addLast(l1);
            l1 = l1.next;
        }
        while (l2!= null){
            stack2.addLast(l2);
            l2 = l2.next;
        }
        return addTwoNumbersHelper(stack1,stack2);
    }
    private ListNode addTwoNumbersHelper(LinkedList<ListNode> stack1,
                                         LinkedList<ListNode> stack2) {
        LinkedList<ListNode> result = new LinkedList<ListNode>();
        int temp=0;
        //表示进位
        int carry =0;
        while (!stack1.isEmpty()&&!stack2.isEmpty()){
            temp =stack1.pollLast().val+stack2.pollLast().val+carry;
            carry = temp/10;
            ListNode tempNode =new ListNode(temp%10);
            result.addLast(tempNode);
        }
        while (stack1.isEmpty()&&!stack2.isEmpty()){
            temp = stack2.pollLast().val+carry;
            carry = temp/10;
            ListNode tempNode =new ListNode(temp%10);
            result.addLast(tempNode);
        }
        while (!stack1.isEmpty()&&stack2.isEmpty()){
            temp =stack1.pollLast().val+carry;
            carry = temp/10;
            ListNode tempNode =new ListNode(temp%10);
            result.addLast(tempNode);
        }
        //处理l1,l2一样长，但是最后一位还有进位的情况
        if (carry!=0){
            ListNode last = new ListNode(carry);
            result.addLast(last);
        }
        ListNode outPut=new ListNode(0);
        outPut.next = result.peekLast();
        while (!result.isEmpty()){
            ListNode listNode  = result.pollLast();
            listNode.next=result.peekLast();
        }
        return outPut.next ;
    }

    public static void main(String[] args) {
        ListNode l1 = new  ListNode(7);
        ListNode l1two = new  ListNode(2);
          ListNode l1three = new  ListNode(4);
          ListNode l1four = new  ListNode(3);
        l1.next = l1two;
//        l1two.next = null;
          l1two.next = l1three;
          l1three.next = l1four;
         // l1three.next = null;
          l1four.next = null;
        ListNode l2 = new  ListNode(5);
        ListNode l2two = new  ListNode(6);
        ListNode l2three = new  ListNode(4);
//        ListNode l2four = new  ListNode(4);
        l2.next = l2two;
       // l2.next = null;
        l2two.next = l2three;
        l2three.next = null;
//        l2three.next = l2four;
//        l2four.next = null;
        ListNode listNode = new LinkedListaddTwoNumbers445LeetCode().addTwoNumbers(l1, l2);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
