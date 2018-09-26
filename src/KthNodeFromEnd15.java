import common.ListNodeCommom;

/**
 * 解题思路：
 *  本题注意下要控制代码的鲁棒性问题，包括三个点：
 *   1、传入的指针可能为空指针；
 *   2、K值可能为0 ，0如果设定为无符号的数(即 unsigned )，那么0-1= 一个很大的数；可能要循环很多次
 *   3、需要寻找K个，其实可能链表没有k个元素；
 * @author LemonLin
 * @Description :KthNodeFromEnd15
 * @date 2018/2/23-20:32
 */
public class KthNodeFromEnd15 {

    public ListNodeCommom FindKthToTail(ListNodeCommom head, int k) {
        ListNodeCommom pHead = null;
        ListNodeCommom pBehind = null;
        if (head == null || k == 0){
            return null;
        }
        pBehind = head;
        for (int i =1;i<k;i++){
            //这里要注意考虑需要寻找K个，其实可能链表没有k个元素的问题
            if (pBehind.next==null){
                return null;
            }
            //这里弄错了，把pBehind= pBehind.next ;写成了pBehind.next = pBehind;
            pBehind = pBehind.next; //这种情况是左边的指针指向右边的
        }
        pHead = head;
        while (pBehind.next!=null){
            pHead = pHead.next;
            pBehind = pBehind.next;
        }
        return  pHead;
    }

    //测试代码
    public static void main(String[] args) {
        ListNodeCommom list1 = new ListNodeCommom(1);
        ListNodeCommom list2 = new ListNodeCommom(2);
        ListNodeCommom list3 = new ListNodeCommom(3);
        ListNodeCommom list4 = new ListNodeCommom(4);
        ListNodeCommom list5 = new ListNodeCommom(5);
        ListNodeCommom list6 = new ListNodeCommom(6);

        list1.next =list2;
        list2.next =list3;
        list3.next =list4;
        list4.next =list5;
        list5.next =list6;

        KthNodeFromEnd15 test = new KthNodeFromEnd15();
        ListNodeCommom pHead = test.FindKthToTail(list1,3);

        System.out.println(pHead.val);

    }
}
