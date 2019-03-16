import java.util.Stack;
/**
 * @author LemonLin
 * @Description :MInInStack21
 * @date 2018/3/12-21:20
 *题目描述
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 *
 *  解题思路：
 *  添加一个成员变量存放最小的元素，每次压入一个新元素进栈的时候，如果该元素比当前最小的元素还要小，
 *  则更新最小元素。但是有一个问题：如果当前最小的元素被弹出栈了，如何得到下一个最小的元素？
 *  所以我们发现仅仅添加一个成员变量存放最小元素是不够的，也就是当最小元素被弹出栈的时候，我们
 *  希望能够得到次小元素，所以需要一个辅助栈来保存至少两个数。
 *  具体实现：利用一个辅助栈来存放最小值（从左往右入站）
 *     栈  3，4，2，5，1
 *     辅助栈 3，3，2，2，1
 * 每入栈一次，就与辅助栈顶比较大小，如果小就入栈，如果大就入栈当前的辅助栈顶
 * 当出栈时，辅助栈也要出栈
 * 这种做法可以保证辅助栈顶一定都当前栈的最小值
 */
public class StackMinInStack21 {

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
        StackMinInStack21 stackMinInStack21 = new StackMinInStack21();
        stackMinInStack21.push(3);
        int min = stackMinInStack21.min();
        System.out.println("最小值应该为3===="+min);
        stackMinInStack21.push(4);
        min = stackMinInStack21.min();
        System.out.println("最小值应该为3====="+min);
        stackMinInStack21.push(2);
        min = stackMinInStack21.min();
        System.out.println("最小值应该为2====="+min);
        stackMinInStack21.push(3);
        min = stackMinInStack21.min();
        System.out.println("最小值应该为2====="+min);
        stackMinInStack21.pop();
        min = stackMinInStack21.min();
        System.out.println("最小值应该为2====="+min);
        stackMinInStack21.pop();
        min = stackMinInStack21.min();
        System.out.println("最小值应该为3====="+min);
        stackMinInStack21.pop();
        min = stackMinInStack21.min();
        System.out.println("最小值应该为3====="+min);
        stackMinInStack21.push(0);
        min = stackMinInStack21.min();
        System.out.println("最小值应该为0====="+min);
        return;
    }
}
