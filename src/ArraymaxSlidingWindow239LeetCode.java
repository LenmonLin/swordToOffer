import java.util.ArrayList;
import java.util.LinkedList;
/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右
 * 侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *   滑动窗口的位置                最大值
 * ---------------                  -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * @author LemonLin
 * @Description :ArraymaxSlidingWindow239LeetCode
 * @date 20.6.28-15:18
 * 思路：维护一个滑动窗口的单调栈。
 * 上述单调栈思路错误，这里要维护一个双向递减队列，
 * 参考面试题59 - II. 队列的最大值
 * 参考：https://leetcode-cn.com/problems/sliding-window-maximum/solution/dan-diao-dui-lie-by-labuladong/
 */
public class ArraymaxSlidingWindow239LeetCode {
    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        Makequeue makequeue = new Makequeue();
        for (int i = 0; i <nums.length ; i++) {
            //如果滑动窗口个数小于给定K-1的，可以不断进队，
            if (i<k-1){
                makequeue.push_back(nums[i]);
            }else {
                //当滑动窗口个数等于k-1时，应该不断进队一次，出队一次。
                makequeue.push_back(nums[i]);
                result.add(makequeue.max_value());
                makequeue.pop_front();
            }
        }
        int [] output = new int[result.size()];
        for (int i = 0; i <result.size() ; i++) {
            output[i]=result.get(i);
        }
        return output;
    }
    //参考面试题59||队列的最大值。
    public class Makequeue{

        LinkedList<Integer> queue1;
        LinkedList<Integer> queue2;
        public Makequeue() {
            queue1 = new LinkedList();
            queue2 = new LinkedList();
        }

        public int max_value() {
            //没有最大值的话返回-1，解决bug1
            if (queue2.isEmpty())return -1;
            return queue2.peekFirst();
        }

        public void push_back(int value) {
            queue1.addLast(value);
            while (!queue2.isEmpty()&&queue2.peekLast()<value){
                queue2.removeLast();
            }
            queue2.addLast(value);
        }

        public int pop_front() {
            //没有最大值的话返回-1,说明这个队列中没有元素,解决bug1
            if (queue2.isEmpty())return -1;
            int out = queue1.removeFirst();
            if (queue2.peekFirst()==out){
                queue2.removeFirst();
            }
            return out;
        }
    }

    public static void main(String[] args) {
        int [] nums ={1,3,-1,-3,5,3,6,7};
        int k =3;
        int[] ints = new ArraymaxSlidingWindow239LeetCode().maxSlidingWindow(nums, k);
        for (int i = 0; i <ints.length ; i++) {
            System.out.print(ints[i]);
        }
    }
}
