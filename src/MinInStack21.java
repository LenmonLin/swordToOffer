import java.util.Stack;

/**
 * 解题思路：
 *  1、需要另外开一个栈，记录最新值
 * @author LemonLin
 * @Description :MInInStack21
 * @date 2018/3/12-21:20
 */
public class MinInStack21 {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> minStack2 = new Stack<Integer>();

    public void push(int node) {

//      如果插入第一个节点，则无需考虑同时入栈即可
        if (stack1.size()==0){
            stack1.push(node);
            minStack2.push(node);
            return;
        }
        //如果需要插入的节点A比栈1的顶点B大，则辅助栈插入栈1顶点A
        int stack1Top = minStack2.peek();
        stack1.push(node);

        if (node>stack1Top){
            minStack2.push(stack1Top);
            //如果需要插入的节点A比栈1的顶点B小，则辅助栈插入栈1顶点B
        }else {
            minStack2.push(node);
        }
    }

    //数据栈的栈顶只有大于辅助栈栈顶或者数据栈的栈顶等于辅助栈的栈顶
//    不管是哪种情况，数据栈和辅助栈的栈顶元素都需要出栈
    public void pop() {
            stack1.pop();
            minStack2.pop();
    }

    public int top() {
        return  stack1.peek();
    }

    public int min() {
        return minStack2.peek();
    }

    public static void main(String[] args) {

        MinInStack21 minInStack21 = new MinInStack21();
        minInStack21.push(3);
        int min = minInStack21.min();
        System.out.println("最小值应该为3===="+min);

        minInStack21.push(4);
        min = minInStack21.min();
        System.out.println("最小值应该为3====="+min);

        minInStack21.push(2);
        min = minInStack21.min();
        System.out.println("最小值应该为2====="+min);

        minInStack21.push(3);
        min = minInStack21.min();
        System.out.println("最小值应该为2====="+min);

        minInStack21.pop();
        min = minInStack21.min();
        System.out.println("最小值应该为2====="+min);

        minInStack21.pop();
        min = minInStack21.min();
        System.out.println("最小值应该为3====="+min);

        minInStack21.pop();
        min = minInStack21.min();
        System.out.println("最小值应该为3====="+min);

        minInStack21.push(0);
        min = minInStack21.min();
        System.out.println("最小值应该为0====="+min);

        return;
    }
}
