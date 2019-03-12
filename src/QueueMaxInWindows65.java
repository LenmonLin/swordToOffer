import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author LemonLin
 * @Description :QueueMaxInWindows65
 * @date 2019/3/12-20:09
 *题目描述
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及
 * 滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,
 * 5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,
 * 4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 *
 * 解题思路：
 * 滑动窗口应当是队列，但为了得到滑动窗口的最大值，队列序可以从两端删除元素，因此使用双端队列。
 *       原则：
 *      对新来的元素k，将其与双端队列中的元素相比较
 *      1）前面比k小的，直接移出队列（因为不再可能成为后面滑动窗口的最大值了!）,
 *      2）前面比k大的X，比较两者下标，判断X是否已不在窗口之内，不在了，直接移出队列
 * 队列的第一个元素是滑动窗口中的最大值
 * ps:应该在队列中存放数字在数组的下标，而不是数值，当一个数字的下标与当前处理的数字下标之差大于或者
 * 等于滑动窗口的大小时，这个数字已经从窗口中滑出，可以从队列中删除了。
 * 双端队列在Java中可以用LinkedList，因为可以对链表头部和尾部操作removeLast，addLast，removeFirst
 *
 */
public class QueueMaxInWindows65 {

    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (num == null) {
            return ret;
        }
        if (num.length < size || size < 1) {
            return ret;
        }

        LinkedList<Integer> indexDeque = new LinkedList<>();
        //先添加两个，因为刚开始的阶段下，还没读取到size个数的数值，必须先把size-1个数字填到滑动窗口中才可以。最后保存的也是几个填入的数
        //中最大数的下标的值
        for (int i = 0; i < size - 1; i++) {
            //双端队列非空，同时列表中的数字大于双端队列中保存的下标对应的数字，移除队列中的下标
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            //否则添加下标到队列中去
            indexDeque.addLast(i);
        }

        //当上面的for循环已经在双端队列中保存了size-1个数字
        for (int i = size - 1; i < num.length; i++) {
            while (!indexDeque.isEmpty() && num[i] > num[indexDeque.getLast()]) {
                indexDeque.removeLast();
            }
            indexDeque.addLast(i);
            //当一个数字的下标与当前处理的数字下标之差大于或者等于滑动窗口的大小时，这个数字已经从窗口中滑出，可以从队列中删除了
            if (i - indexDeque.getFirst() + 1 > size) {
                indexDeque.removeFirst();
            }
            //每个循环都加入双端队列中的头一个元素
            ret.add(num[indexDeque.getFirst()]);
        }
        return ret;
    }

    public static void main(String[] args) {
        int [] test={2,3,4,2,6,2,5,1};
        QueueMaxInWindows65 queueMaxInWindows65 = new QueueMaxInWindows65();
        ArrayList<Integer> arrayList = queueMaxInWindows65.maxInWindows(test, 3);
        for (int i=0;i<arrayList.size();i++){
            System.out.println(arrayList.get(i));
        }

    }
}
