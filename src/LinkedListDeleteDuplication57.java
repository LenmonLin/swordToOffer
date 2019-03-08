import common.ListNodeCommom;
import common.MakeLikedList;

/**
 * @author LemonLin
 * @Description :LinkedListDeleteDuplication57
 * @date 2019/3/8-10:45
 *
 * 题目描述
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 *
 *解题方法:
 *
 * 注意题干中的排序；
 *
 * 设置两个指针first 与last,其实可以first 指向不重复的结点，last指向重复的结点，first可以与first.next比较；
 * last可以与last.next比较；相当于四个指针的使用；
 *
 * 这种类型的题目可以设置头结点，以便方便碰到第一个、第二个结点就相同的情况。
 */
public class LinkedListDeleteDuplication57 {

    public ListNodeCommom deleteDuplication(ListNodeCommom pHead) {
        if (pHead==null||pHead.next ==null){
            return pHead;
        }

        ListNodeCommom head = new ListNodeCommom(0);

        head.next = pHead;
        ListNodeCommom first = head;
        ListNodeCommom last = head.next;

        while (last != null){
            if (last.next!=null&&last.val == last.next.val ) {
                //找最后一个相同的结点
                while (last.next != null && last.val == last.next.val) {
                    last = last.next;
                }
                first.next = last.next;
                last = last.next;
                //结点都不相同的情况下
            }else {
                first=first.next;
                last=last.next;
            }
        }
        //注意排除头结点
        return head.next;


    }

    public static void main(String[] args) {
        LinkedListDeleteDuplication57 linkedListDeleteDuplication57 = new LinkedListDeleteDuplication57();
        MakeLikedList makeLikedList = new MakeLikedList();
        ListNodeCommom pHead = makeLikedList.LikedListNode();
        ListNodeCommom p = linkedListDeleteDuplication57.deleteDuplication(pHead);
        while (p!=null){
            System.out.println(p.val);
            p=p.next;
        }
    }
}

