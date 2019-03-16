import java.util.Stack;

/**
 * @author LemonLin
 *  * @Description :StackPushPopOrder22
 *  * @date 2018/3/15-14:36
 *
 * 题目描述
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设
 * 压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的
 * 一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 *解题思路：借用一个辅助的栈，遍历压栈顺序，先讲第一个放入栈中，这里是1，然后判断栈顶元素是不是
 * 出栈顺序的第一个元素，这里是4，很显然1≠4，所以我们继续压栈，直到相等以后开始出栈，出栈一个元
 * 素，则将出栈顺序向后移动一位，直到不相等，这样循环等压栈顺序遍历完成，如果辅助栈还不为空，说明
 * 弹出序列不是该栈的弹出顺序。
 * 举例：
 * 入栈1,2,3,4,5
 * 出栈4,5,3,2,1
 * 首先1入辅助栈，此时栈顶1≠4，继续入栈2
 * 此时栈顶2≠4，继续入栈3
 * 此时栈顶3≠4，继续入栈4
 * 此时栈顶4＝4，出栈4，弹出序列向后一位，此时为5，,辅助栈里面是1,2,3
 * 此时栈顶3≠5，继续入栈5
 * 此时栈顶5=5，出栈5,弹出序列向后一位，此时为3，,辅助栈里面是1,2,3
 * ….
 * 依次执行，最后辅助栈为空。如果不为空说明弹出序列不是该栈的弹出顺序。
 *
 * 怎么判断退出条件是关键？返回结果是stack.empty();如果为空，就匹配结束，是正确的顺序，
 * 如果不为空，就不是正确的弹出序列
 * 如果栈为空，stack.peek()会报错,解决办法是先入栈，再用stack.peek();
 */
public class StackPushPopOrder22 {

    public boolean IsPopOrder(int [] pushA,int [] popA) {
        //需要考虑容错性
        if (pushA.length ==0|| popA.length == 0){
            return false;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int popIndex =0;//记录压入序列的数值
        for (int pushIndex = 0 ;pushIndex<pushA.length;++pushIndex){
            stack.push(pushA[pushIndex]);
            while (!stack.empty()&&stack.peek()==popA[popIndex]){
                stack.pop();
                popIndex++;
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        int[] pushA = {1,2,3,4,5};
        int[] popA={4,5,3,2,1};
        StackPushPopOrder22 pushPopOrder22 = new StackPushPopOrder22();
        boolean is = pushPopOrder22.IsPopOrder(pushA, popA);
        if (is){
            System.out.println("是正确的");
        }else {
            System.out.println("是错误的");
        }
    }
}
