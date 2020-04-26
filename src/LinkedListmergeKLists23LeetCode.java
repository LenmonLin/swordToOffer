import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * @author LemonLin
 * @Description :LinkedListmergeKLists23LeetCode
 * @date 20.4.26-18:01
 * 参考：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/
 * tan-xin-suan-fa-you-xian-dui-lie-fen-zhi-fa-python/
 * 方法1：全部节点放进最小堆中，然后再一个一个的取出来，组成链表。
 * 方法2：两两链表合并。
 * 方法3；用一个最小堆把同一列的节点放入堆中，然后取出，再添加一个。以此类推。
 * 难点：
 * 输入函数中的lists的每一个元素是一个链表节点，这个节点是头结点后面连着一串结点。
 */
public class LinkedListmergeKLists23LeetCode {
    /**
     * Definition for singly-linked list.
     *    */
     public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

    public ListNode mergeKLists(ListNode[] lists) {
         if (lists.length == 0)
             return null;
        PriorityQueue<ListNode>  queue  = new PriorityQueue<>(
                new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });
        ListNode dummy = new ListNode(-1);
        ListNode curNode = dummy;
        //直接把头节点全部加进来，就是把整个链表加进来
        for (int i=0;i<lists.length;i++){
            if (lists[i]!=null){
                queue.add(lists[i]);
            }
        }
        while (!queue.isEmpty()){
            ListNode node = queue.remove();
            curNode.next = node;
            //把当前链表的下一位后移。
            curNode = curNode.next;
            if (curNode.next!=null){
                queue.add(curNode.next);
            }
        }
        return dummy.next;
    }
}
