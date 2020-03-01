import java.util.LinkedList;
/**
 * 使用队列实现栈的下列操作：
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和
 * is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个
 * 队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 * @author LemonLin
 * @Description :DesignMyStack225LeetCode
 * @date 20.3.1-10:50
 * 思路：简单难度的题，练手差不多，主要弄清楚peek不删除元素，poll删除元素的区别就可。
 */
public class DesignMyStack225LeetCode {

    LinkedList<Integer> queue=null;
    /** Initialize your data structure here. */
    public DesignMyStack225LeetCode() {
         queue = new LinkedList();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.addLast(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.removeLast();
    }

    /** Get the top element. */
    public int top() {
        return queue.peekLast();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
