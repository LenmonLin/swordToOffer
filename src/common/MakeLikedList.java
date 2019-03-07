package common;

/**
 * @author LemonLin
 * @Description :MakeLikedList
 * @date 2019/3/7-17:29
 */
public class MakeLikedList {
    public  ListNodeCommom LikedListNode(){

        ListNodeCommom p1 = new ListNodeCommom(1);
        ListNodeCommom p2 = new ListNodeCommom(2);
        ListNodeCommom p3 = new ListNodeCommom(3);
        ListNodeCommom p4 = new ListNodeCommom(4);
        ListNodeCommom p5 = new ListNodeCommom(5);
        ListNodeCommom p6 = new ListNodeCommom(6);
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        p6.next = p3;
        return p1;

    }
}
