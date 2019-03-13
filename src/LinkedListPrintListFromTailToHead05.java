import common.ListNodeCommom;
import common.MakeLikedList;

import java.util.ArrayList;

/**
 * @author LemonLin
 * @Description :LinkedListPrintListFromTailToHead05
 * @date 2019/3/13-10:46
 *
 * 题目描述
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 *
 * 解题思路：
 * 利用递归把链表节点从头到尾遍历，当遍历到最后一个节点时，递归一层一层往上返回，其实这之间就是栈存数
 * 的过程，如果链表特别长，数特别多的话，可能有栈不够用的风险，但是此题目前这个解法能够ac
 */
public class LinkedListPrintListFromTailToHead05 {

    //本题递归的解法毕竟巧妙，把ArrayList设置在函数的外面，注意体会
    ArrayList<Integer> integers=new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead(ListNodeCommom listNode) {
        if (listNode!=null){
            printListFromTailToHead(listNode.next);
            integers.add(listNode.val);
        }
        return integers;
    }

    public static void main(String[] args) {
        LinkedListPrintListFromTailToHead05 test = new LinkedListPrintListFromTailToHead05();
        MakeLikedList makeLikedList = new MakeLikedList();
        ListNodeCommom listNodeCommom = makeLikedList.LikedListNode();
        ArrayList<Integer> result = test.printListFromTailToHead(listNodeCommom);
        for (int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }
}
