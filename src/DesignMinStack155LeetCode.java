import java.util.LinkedList;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * 示例:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * @author LemonLin
 * @Description :DesignMinStack155LeetCode
 * @date 20.5.12-11:44
 * 思路1：参考面试题59II队列的最大值，用两个队列，一个单调队列维持数据单调递减
 * 不能用思路1，会出问题，需要用同步栈：
 * 参考：https://leetcode-cn.com/problems/min-stack/solution/shi-yong-fu-zhu-zhan-tong-bu-he-bu-tong-bu-python-/
 */
public class DesignMinStack155LeetCode {
    /** initialize your data structure here. */
    //数据栈
    LinkedList<Integer> data;
    //同步栈
    LinkedList<Integer> helper;
    public DesignMinStack155LeetCode() {
        data = new LinkedList<Integer>();
        helper = new LinkedList<Integer>();
    }

    public void push(int x) {
        data.addFirst(x);
         if(helper.isEmpty()||helper.peekFirst()>=x){
            helper.addFirst(x);
        }else {
             helper.addFirst(helper.peekFirst());
         }
    }

    public void pop() {
        if (!data.isEmpty()){
            data.removeFirst();
            helper.removeFirst();
        }
    }

    public int top() {
        if(!data.isEmpty())
            return data.peekFirst();
        throw   new RuntimeException("栈中元素为空，此操作非法");
    }

    public int getMin() {
        if (helper.isEmpty())
            throw   new RuntimeException("栈中元素为空，此操作非法");
        return helper.peekFirst();
    }

    public static void main(String[] args) {
        DesignMinStack155LeetCode designMinStack155LeetCode = new DesignMinStack155LeetCode();
        designMinStack155LeetCode.push(-2);
        designMinStack155LeetCode.push(0);
        designMinStack155LeetCode.push(-3);
        System.out.println(designMinStack155LeetCode.getMin());
        designMinStack155LeetCode.pop();
        designMinStack155LeetCode.top();
        System.out.println(designMinStack155LeetCode.getMin());
    }
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
