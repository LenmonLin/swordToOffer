/**
* 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
* 说明:
* 1 ≤ m ≤ n ≤ 链表长度。
* 示例:
* 输入: 1->2->3->4->5->NULL, m = 2, n = 4
* 输出: 1->4->3->2->5->NULL
 * @author LemonLin
 * @Description :LinkedListreverseBetween92LeetCode
 * @date 20.2.7-15:54
 * 思路：主体思路和LeetCode206一样。只不过需要处理具体位置。
 * 先遍历到m,然后控制反转的次数n。
 */
public class LinkedListreverseBetween92LeetCode {
    /**
     * Definition for singly-linked list.
     * */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode preCur=null;
        ListNode cur = head;
        ListNode temp = null;
        //这个记录第m个结点之前一个结点的位置，后期反转之后的连接需要用到
        ListNode preM = null;
        //这个记录第m个结点,后期需要连接到第n+1个节点上。
        ListNode mNode = null;
        //初始情况下cur就指向了第一个结点,但是我们需要指向要反转
        // 结点开始结点的下一个结点，因为需要下一个结点指针指向前面结点
        for (int i=1;i<m;i++){
            if (i==m-1){
                preM =cur;
            }
            cur =cur.next;
        }
        mNode =cur;
        //上一个循环退出时，cur指向了待反转链表的起点位置
        //注意这里i初始化为m,不是0;
        //这里第一步preCur还是指向null，后期反转结束再进行连接
        for (int i=m;i<=n;i++){
            temp = cur.next;
            cur.next = preCur;
            //pre和cur节点都前进一位
            preCur =cur;
            cur =temp;
        }
        //请注意上个循环跳出的时候，preCur指向了第n个节点，cur指向了第n+1个节点
        //m=1要特殊处理，这个时候没有preM
        if (m==1){
            head= preCur;
        }else {
            preM.next =preCur;
        }
        mNode.next =cur;
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = new  ListNode(1);
        ListNode l1two = new  ListNode(2);
          ListNode l1three = new  ListNode(3);
          ListNode l1four = new  ListNode(4);
          ListNode l1five = new  ListNode(5);
        l1.next = l1two;
          l1two.next = l1three;
          l1three.next = l1four;
          l1four.next = l1five;
          l1five.next = null;

          int m =1;
          int n =5;
        ListNode listNode = new LinkedListreverseBetween92LeetCode().
                reverseBetween(l1, m, n);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
