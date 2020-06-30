import java.util.LinkedList;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和
 * deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中
 * 没有元素，deleteHead 操作返回 -1 )
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 * @author LemonLin
 * @Description :剑指Offer09用两个栈实现队列
 * @date 20.6.30-16:17
 * 思路：参考：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/solution/mian-shi-ti-09-yong-liang-ge-zhan-shi-xian-dui-l-2/
 * 题目只要求实现 加入队尾appendTail() 和 删除队首deleteHead()
 * 两个函数的正常工作，因此我们可以设计栈 A 用于加入队尾操作，栈 B
 * 用于将元素倒序，从而实现删除队首元素。
 * 加入队尾 appendTail()函数： 将数字 val 加入栈 A 即可。
 * 删除队首deleteHead()函数： 有以下三种情况。
 *      当栈 B 不为空： B中仍有已完成倒序的元素，因此直接返回 B 的栈顶元素。
 *      否则，当 A 为空： 即两个栈都为空，无元素，因此返回 -1 。
 *      否则： 将栈 A 元素全部转移至栈 B 中，实现元素倒序，并返回栈 B 的栈顶元素。
 */
public class 剑指Offer09用两个栈实现队列 {
    LinkedList<Integer> stackA;
    LinkedList<Integer> stackB;
    public 剑指Offer09用两个栈实现队列() {
        stackA = new LinkedList<>();
        stackB = new LinkedList<>();
    }

    public void appendTail(int value) {
            stackA.addLast(value);
    }

    public int deleteHead() {
        if (!stackB.isEmpty()){
            return stackB.removeLast();
        }else if (stackA.isEmpty()){
            return -1;
        }else {
            while (!stackA.isEmpty()){
                stackB.addLast(stackA.removeLast());
            }
            return stackB.removeLast();
        }
    }
}
