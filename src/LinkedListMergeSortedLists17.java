import common.ListNodeCommom;
/**
 *  @author LemonLin
 *  @Description :MergeSortedLists
 *  @date 2018/2/23-16:47
 *  题目描述
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * 解题思路比较简单，主要用到了递归的思路：
 *  互相比较两个链表的开始结点，使用一个pHeadMerge合并指针来指向合并后的链表
 *  主要考虑当两个链表中的其中一个链表比较结束之后该怎么处理
 */
public class LinkedListMergeSortedLists17 {
    public ListNodeCommom Merge(ListNodeCommom list1, ListNodeCommom list2) {
        if (list1 ==null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }

        //新建一个pHeadMerge变量用来指向合并后的链表
        ListNodeCommom pHeadMerge=null;

        if (list1.val <list2.val){
            pHeadMerge = list1;
            pHeadMerge.next = Merge(list1.next,list2);
        }else {
            pHeadMerge = list2;
            pHeadMerge.next = Merge(list1,list2.next);
        }

        return pHeadMerge;
    }

    //测试代码
    public static void main(String[] args) {
        ListNodeCommom list1 = new ListNodeCommom(1);
        ListNodeCommom list2 = new ListNodeCommom(2);
        ListNodeCommom list3 = new ListNodeCommom(3);
        ListNodeCommom list4 = new ListNodeCommom(4);
        ListNodeCommom list5 = new ListNodeCommom(5);
        ListNodeCommom list6 = new ListNodeCommom(6);

        list1.next =list3;
        list3.next =list5;
        list2.next =list4;
        list4.next =list6;

        LinkedListMergeSortedLists17 test = new LinkedListMergeSortedLists17();
        ListNodeCommom pHead = test.Merge(list1,list2);
        //遍历合并后的链表
        while(pHead!=null){
            System.out.println(pHead.val);
            pHead = pHead.next;
        }
    }
}
