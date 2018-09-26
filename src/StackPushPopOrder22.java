import java.util.Stack;

/**
 * 怎么判断退出条件是关键？返回结果是stack.empty();如果为空，就匹配结束，是正确的顺序，
 * 如果不为空，就不是正确的弹出序列
 *
 * 如果栈为空，stack.peek()会报错,解决办法是先入栈，再用stack.peek();
 *
 * @author LemonLin
 * @Description :StackPushPopOrder22
 * @date 2018/3/15-14:36
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


//            System.out.println("打印出栈顶元素==="+peekPush);

//            System.out.println("打印出出队序列==="+popA[pushIndex]);

            while (!stack.empty()&&stack.peek()==popA[popIndex]){

                stack.pop();
                popIndex++;
            }
            //当相等的时候也要压入栈，
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
