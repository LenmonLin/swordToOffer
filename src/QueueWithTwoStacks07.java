import java.util.Stack;
/**
 * @author LemonLin
 * @Description :QueueWithTwoStacks07
 * @date 2018/2/22-16:28
 *题目描述
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * 解题要点：
 * 1、要实现进队列和出队列；进队列没什么问题；
 * 2、出队列要将栈的先进后出变成队列的先进先出，难点在这，主要思路考虑将栈1中的元素存到栈2中，
 * 栈2再出栈，即能实现队列的先进先出功能；
 * 3、过程中要判断栈2是否为空，为空需要搬家，不为空，直接出栈即可；
 */
public class QueueWithTwoStacks07 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    //定义这个题目要求的总体的入队
    public void push(int node) {
        stack1.push(node);
    }
    //定义这个题目要求的总体的出队
    public int pop() {
        Integer popInterger ;
        if(stack2.empty()){
            if(stack1.empty()){
                return -1;//队列为空，不能出队
            }
            while (!stack1.empty()){
                Integer temp=stack1.pop();
                stack2.push(temp);
            }
            popInterger=stack2.pop();
        }else{
            popInterger= stack2.pop();
        }
        return popInterger;
    }
    //测试脚本
    public static void main(String[] args) {
        QueueWithTwoStacks07 test = new QueueWithTwoStacks07();
        test.push(1);
        test.push(2);
        test.push(3);
        Integer pop1=test.pop();
        System.out.println(pop1);
        Integer pop2=test.pop();
        System.out.println(pop2);
        test.push(4);
        Integer pop3=test.pop();
        System.out.println(pop3);
    }
}
