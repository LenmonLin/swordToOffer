import common.ListNodeCommom;
import common.MakeLikedList;

/**
 * @author LemonLin
 * @Description :LinkedListEntryNodeOfLoop56
 * @date 2019/3/7-16:51
 *
 * 题目描述
 * 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
 *
 *思路：
 * 如果知道环中有n个结点，则用两个指针，p1从头开始，p2先走n步，然后再开始同时走，p2，p1重合，p1
 * 就走到环中入口。
 *
 * 现在要求环中有几个结点，用p3指针每次走一步，p4指针每次走两步，如果两个指针相遇，表明链表中存在环，
 * 相遇的结点一定在环中，可以从这个结点出发，一边继续向前移动一边计数，当再次回到这个结点时，得
 * 到环中结点数
 *
 */
public class LinkedListEntryNodeOfLoop56 {

    public ListNodeCommom EntryNodeOfLoop(ListNodeCommom pHead){
        if(pHead ==null|| pHead.next == null){
            return null;
        }
        int count = CountOfNode(pHead);

        ListNodeCommom p1 =pHead;
        ListNodeCommom p2 =pHead;

        //让p2先走环中的个数个步
        while (count!= 0){
            p2=p2.next;
            count--;
        }

        while (p1!=p2){
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    //计算环中结点个数
    public int CountOfNode(ListNodeCommom pHead){
        ListNodeCommom p3 = pHead.next;
        ListNodeCommom p4 = pHead.next.next;
        while (p3!=p4){
            p3 = p3.next;
            p4= p4.next.next;
        }
        int count =0;
        ListNodeCommom p5 = p3;
        do {
            p5= p5.next;
            count = count+1;
        }while (p3!=p5);
        return count;
    }

    public static void main(String[] args) {
        LinkedListEntryNodeOfLoop56 linkedListEntryNodeOfLoop56 = new LinkedListEntryNodeOfLoop56();
        MakeLikedList makeLikedList = new MakeLikedList();
        ListNodeCommom test = makeLikedList.LikedListNode();
        ListNodeCommom node = linkedListEntryNodeOfLoop56.EntryNodeOfLoop(test);
        System.out.println(node.val);
    }
}
