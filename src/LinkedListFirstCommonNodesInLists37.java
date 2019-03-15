import common.ListNodeCommom;
/**
 * @author LemonLin
 * @Description :FirstCommonNodesInLists37
 * @date 2018/5/5-20:00
 *题目描述
 * 输入两个链表，找出它们的第一个公共结点。
 * 解题思路：
 * 总体思路：1、首先要知道链表是单向链表。如果两个单向链表有公共的结点，那么两个链表从某一个结点开始，
 * 它们的next都指向同一个结点。
 * 2、先求出两个链表的长度，进而求出两个链表的长度差，然后先遍历长的链表长度差，然后同时出发，当
 * 遍历了同一个结点的时候，就是公共结点
 */
public class LinkedListFirstCommonNodesInLists37 {

    public ListNodeCommom FindFirstCommonNode(ListNodeCommom pHead1, ListNodeCommom pHead2) {

        int p1Length = getListLength(pHead1);
        int p2Length = getListLength(pHead2);

        int nLengthDif = p1Length -p2Length;
        ListNodeCommom pListHeadLong = pHead1;
        ListNodeCommom pListHeadShort = pHead2;

        //那么p2Length比较长
        if (nLengthDif<0){
            nLengthDif = Math.abs(nLengthDif);

            pListHeadLong = pHead2;
            pListHeadShort = pHead1;
        }

        //长的那个链表先移动把多出来的长度移动
        for (int i =0;i<nLengthDif;++i){
            pListHeadLong = pListHeadLong.next;
        }

        while (pListHeadLong !=null &&
                pListHeadShort != null&&
                pListHeadLong!=pListHeadShort){
            pListHeadLong =pListHeadLong.next;
            pListHeadShort = pListHeadShort.next;
        }

        //这个时候pListHeadLong指向的就是公共结点
        return pListHeadLong;
    }

    public int getListLength(ListNodeCommom pHead){
        int nLength =0;

        //注意因为是链表情况，所以要设置一个pNode,防止后面的操作过程中改变了链表的指针
        ListNodeCommom pNode = pHead;
        while (pHead!=null){
            ++nLength;
            pHead = pHead.next;
        }
        return nLength;
    }
    public static void main(String[] args) {

        ListNodeCommom lista1 = new ListNodeCommom(1);
        ListNodeCommom lista2 = new ListNodeCommom(2);
        ListNodeCommom lista3 = new ListNodeCommom(3);
        ListNodeCommom lista4 = new ListNodeCommom(4);
        ListNodeCommom lista5 = new ListNodeCommom(5);
        ListNodeCommom lista6 = new ListNodeCommom(6);
        ListNodeCommom lista7 = new ListNodeCommom(7);

        ListNodeCommom listb1 = new ListNodeCommom(11);
        ListNodeCommom listb2 = new ListNodeCommom(22);
        ListNodeCommom listb3 = new ListNodeCommom(33);
        ListNodeCommom listb4 = new ListNodeCommom(44);

        lista1.next = lista2;
        lista2.next = lista3;
        lista3.next = lista4;
        lista4.next = lista5;
        lista5.next = lista6;
        lista6.next = lista7;

        listb1.next = listb2;
        listb2.next = listb3;
        listb3.next = listb4;
        listb4.next = lista5;

        LinkedListFirstCommonNodesInLists37 linkedListFirstCommonNodesInLists37 = new LinkedListFirstCommonNodesInLists37();
        ListNodeCommom listNodeCommom = linkedListFirstCommonNodesInLists37.FindFirstCommonNode(lista1, listb1);
        System.out.println(listNodeCommom.val);
    }
}
