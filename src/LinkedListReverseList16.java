import common.ListNodeCommom;
/**
 *   @author LemonLin
 *  @Description :ReverseList16
 *  @date 2018/2/23-20:08
 * 题目描述
 * 输入一个链表，反转链表后，输出新链表的表头。
 *
 * 关于指针有一个注意点的地方：
 *      指针变量放右边有两种意思：
 *          一、赋值：表示右边的指针变量赋值给左边的指针变量
 *          二、指向：表示左边的指针变量指向右边的指针变量所指的结点
 * 解题思路：
 * 其实就是用三个指针指来指去的，没有什么难点以及盲点
 * 1、要用到三个指针，分别指向遍历的结点pNode,遍历的结点的前一个结点preNode,遍历结点的后
 * 一个结点pNextNode;
 * 2、还需要一个额外的指针变量指向原来链表的末尾结点作为反转后的头指针
 * 3、四个指针指来指去需要思考的指向顺序有点懵，需要仔细思考；
 */
public class LinkedListReverseList16 {
    public ListNodeCommom ReverseList(ListNodeCommom head) {
        ListNodeCommom pReversedHead = null;
        ListNodeCommom pNode = head;
        ListNodeCommom preNode = null;
        ListNodeCommom pNext = null;
        //为什么不用判断输入的head即pNode为不为空，因为返回的是pReversedHead，用的
        // 是while，如果head为空，返回的pReversedHead即为空
        while (pNode != null){
            pNext = pNode.next;
            if (pNext ==null){
                pReversedHead = pNode;
            }
            pNode.next = preNode;
            preNode = pNode;
            pNode = pNext;
        }
        return pReversedHead;
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

        LinkedListReverseList16 test = new LinkedListReverseList16();
        ListNodeCommom pHead = test.ReverseList(list1);
        //遍历反转后的链表
        while(pHead!=null){
            System.out.println(pHead.val);
            pHead = pHead.next;
        }
    }

}
