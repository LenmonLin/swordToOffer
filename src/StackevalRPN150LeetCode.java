import java.util.LinkedList;
/**
 * 根据逆波兰表示法，求表达式的值。
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * 思路：如果知道逆波特兰的概念，理解题意比较简单。
 * 遍历字符串数组，遇到数字，进栈；
 * 遇到运算符，出栈两个数字进行运算，运算结果再存入栈中。
 * 如此循环。
 * @author LemonLin
 * @Description :StackevalRPN150LeetCode
 * @date 20.2.4-19:44
 */
public class StackevalRPN150LeetCode {
    public int evalRPN(String[] tokens) {
        LinkedList<String> stack = new LinkedList();
        int num1=0;
        int num2=0;
        for (int i=0;i<tokens.length;i++){
            if (tokens[i].equals("+")){
                num1=Integer.valueOf(stack.removeLast());
                num2 =Integer.valueOf(stack.removeLast());
                stack.addLast(String.valueOf(num1+num2));
            }else if (tokens[i].equals("-")){
                num1=Integer.valueOf(stack.removeLast());
                num2 =Integer.valueOf(stack.removeLast());
                //主要是num2-num1,不是num1-num2
                stack.addLast(String.valueOf(num2-num1));
            }else if (tokens[i].equals("*")){
                num1=Integer.valueOf(stack.removeLast());
                num2 =Integer.valueOf(stack.removeLast());
                stack.addLast(String.valueOf(num1*num2));
            }else if (tokens[i].equals("/")){
                num1=Integer.valueOf(stack.removeLast());
                num2 =Integer.valueOf(stack.removeLast());
                stack.addLast(String.valueOf(num2/num1));
            }else {
                stack.addLast(tokens[i]);
            }
        }
        return Integer.valueOf(stack.removeLast());
    }

    public static void main(String[] args) {
        String[] tokens =  {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(new StackevalRPN150LeetCode().evalRPN(tokens));
    }
}
