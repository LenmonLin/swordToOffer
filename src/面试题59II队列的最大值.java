import java.util.LinkedList;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、
 * push_back 和 pop_front 的时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * 示例 1：
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 * 限制：
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 * @author LemonLin
 * @Description :面试题59II队列的最大值
 * @date 20.3.7-10:23
 * 参考：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/solution/
 * liang-chong-fang-fa-xiang-xi-ti-jie-by-yuanninesun/
 *思路：本题的难点是规则要求的需要O(1)处理方式，第一反应肯定是空间换时间。自己的
 *想法是根据类似LeetCode146构建一个HashMap做索引，底层用双向链表，因为这种结构
 *get,put都是O(1),所以可以把队列进行排序存放，原始队列取出的时候，就对应删除新结构
 *中的值，每次都返回新结构中的头结点就好。这样子做思路上倒是没有什么问题，就是太
 *过复杂了。题解是考虑用一个双向递减队列来保存最大值，这个思路我一直想不通的是，
 *这个递减队列进栈时从尾部把小于当前value的全部删除（因为用不到了），这个用不到很
 *难理解。会不会发生这个删除的值是某个时刻的最大值呢，这样不就出错了吗。但是不会。
 *比如进队例子1 5 3 4 2
 *递减队列只保存1 5 4 2，后来1被删除，5 4 2中途过程会不会发生删除了3，而队列中最大元素是3，不可能，因为按照进队顺序，整个过程都不会出现队列中最大元素是三的情况，也就是原始队列中的所有元素不是每个元素都有机会当最大元素。
 *可以把进队例子看做柱子高度：
 *    |
 *    |       |
 *    |   |   |
 *    |   |   |   |                    眼睛在这
 *|   |  |    |   |
 *1  5  3   4   2
 *然后找个人站在 2 的右边，向左看，随着队头元素出队，最大情况只可能是 1(当只有一个
 *元素入队时的情况)， 5， 4， 2 。发现3是不可能成为最大元素的，除非只剩下一个3.
 *因为当进队元素为1时，最大元素为1；
 *当进队元素为1,5时，最大元素为5；
 *当进队元素为1,5,3时，最大元素为5；这个时候不可能为3；
 *当进队元素为1,5,3,4时，最大元素为5；
 *当进队元素为1,5,3,4,2时，最大元素为5；
 *当出队1时，队列中剩5,3,4,2，最大元素为5；
 *当出队1，5时，队列中剩3,4,2，最大元素为4；
 *当出队1，5，3时，队列中剩4,2，最大元素为4；
 *当出队1，5，3，4，时，队列中剩2，最大元素为2；
 *所以总得来说，理论上用双端递减队列是可以行的通的。
 * 具体做法：
 * 用一个队列保存正常元素，另一个双向队列保存单调递减的元素
 * 入队时，第一个队列正常入队；第二个队列是递减队列，所以需要与之前的比较，从尾部
 * 把小于当前value的全部删除（因为用不到了）
 * 出队时，第一个队列正常出队；第二个队列的头部与出队的值作比较，如果相同，那么一
 * 起出队。
 * bug1:
 * ["MaxQueue","pop_front","pop_front","pop_front","pop_front","pop_front","push_back","max_value","push_back","max_value"]
 * [[],[],[],[],[],[],[15],[],[9],[]]
 */
public class 面试题59II队列的最大值 {

    LinkedList<Integer> queue1;
    LinkedList<Integer> queue2;
    public 面试题59II队列的最大值() {
        queue1 = new LinkedList();
        queue2 = new LinkedList();
    }

    public int max_value() {
        //没有最大值的话返回-1，解决bug1
        if (queue2.isEmpty())return -1;
        return queue2.peekFirst();
    }

    public void push_back(int value) {
        queue1.addLast(value);
        while (!queue2.isEmpty()&&queue2.peekLast()<value){
            queue2.removeLast();
        }
        queue2.addLast(value);
    }

    public int pop_front() {
        //没有最大值的话返回-1,说明这个队列中没有元素,解决bug1
        if (queue2.isEmpty())return -1;
        int out = queue1.removeFirst();
        if (queue2.peekFirst()==out){
            queue2.removeFirst();
        }
        return out;
    }
    /**
     * Your MaxQueue object will be instantiated and called as such:
     * MaxQueue obj = new MaxQueue();
     * int param_1 = obj.max_value();
     * obj.push_back(value);
     * int param_3 = obj.pop_front();
     */
}
