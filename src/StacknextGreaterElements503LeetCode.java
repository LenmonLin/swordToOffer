import java.util.LinkedList;

/**
* 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的
 *下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一
 * 个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
* 示例 1:
* 输入: [1,2,1]
* 输出: [2,-1,2]
* 解释: 第一个 1 的下一个更大的数是 2；
* 数字 2 找不到下一个更大的数；
* 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
* 注意: 输入数组的长度不会超过 10000。
 * @author LemonLin
 * @Description :StacknextGreaterElements503LeetCode
 * @date 20.3.30-20:53
 * 思路：参考：https://leetcode-cn.com/problems/next-greater-element-ii/solution/
 * dan-diao-zhan-jie-jue-next-greater-number-yi-lei-2/
 * 本题解法建立在LeetCode496之上，都是用单调栈。
 * 难点是循环数组怎么处理：
 * 解决：一般是通过 % 运算符求模（余数），获得环形特效。
 * 增加了环形属性后，问题的难点在于：这个 Next 的意义不仅仅是当前元素的右边了，有
 * 可能出现在当前元素的左边。
 * 解决：将原始数组 “翻倍”，就是在后面再接一个原始数组
 */
public class StacknextGreaterElements503LeetCode {
    public int[] nextGreaterElements(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        int length = nums.length;
        int [] result = new int[length];
        // 假装这个数组长度翻倍了，数组翻倍长度为2*length-1，不是2*(length-1),易错，
        // 随便找一个数带进去就知道。
        for (int i=2*length-1;i>=0;i--){
            while (!stack.isEmpty()&&stack.peekFirst()<=nums[i%length]){
                stack.removeFirst();
            }
            //这里不能像LeetCode496那样用原数组，因为nums[i%length]还没遍历到，如
            // 果现在赋值了，会导致下次读取的时候发生混乱。
            result[i%length] = stack.isEmpty()?-1:stack.peekFirst();
            stack.addFirst(nums[i%length]);
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums={1,2,1};
        int[] result = new StacknextGreaterElements503LeetCode().nextGreaterElements(nums);
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }
}
