import java.util.ArrayList;
import java.util.List;
/**
 * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: [3]
 * 示例 2:
 * 输入: [1,1,1,3,3,2,2,2]
 * 输出: [1,2]
 * @author LemonLin
 * @Description :ArraymajorityElement229LeetCode
 * @date 20.3.14-20:39
 * 思路：
 * https://leetcode-cn.com/problems/majority-element-ii/solution/
 * 169ti-sheng-ji-ban-xiang-jie-zhu-xing-jie-shi-tong/
 * https://leetcode-cn.com/problems/majority-element-ii/solution/
 * liang-fu-dong-hua-yan-shi-mo-er-tou-piao-fa-zui-zh/
 * 这道题用map方法去做很简单，但是题目描述要求要达到线性的时间复杂度，还有常量
 * 级的空间复杂度。所以得用的是摩尔投票法：
 * 首先我们得明确，n/k的众数最多只有k-1个，为什么呢？假设有k个众数，n/k * k=n,
 * 这k个元素都是众数，还要不同，怎么可能啊。那么对于这个题，超过n/3的数最多只能
 * 有3-1 = 2 个，
 * 摩尔投票法分为两个阶段：抵消阶段和计数阶段。
 * 抵消阶段：两个不同投票进行对坑，并且同时抵消掉各一张票，如果两个投票相同，则
 * 累加可抵消的次数；
 * 计数阶段：在抵消阶段最后得到的抵消计数只要不为0，那这个候选人是有可能超过一半
 * 的票数的，为了验证，则需要遍历一次，统计票数，才可确定。
 * 写代码三步走
 * 1、如果投A（当前元素等于A），则A的票数++;
 * 2、如果投B（当前元素等于B），B的票数++；
 * 3、如果A,B都不投（即当前与A，B都不相等）,那么检查此时A或B的票数是否减为0，如
 * 果为0,则当前元素成为新的候选人；如果A,B两个人的票数都不为0，那么A,B两个候选
 * 人的票数均减一。
 * bug1:
 * 输入:
 * [2,2,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9]
 * 输出
 * [9]
 * 预期结果
 * [9,3]
 */
public class ArraymajorityElement229LeetCode {
    public List<Integer> majorityElement(int[] nums) {
        ArrayList<Integer>  result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int num1 = nums[0];
        int num2 = 0;
        int count1 =0;
        int count2 =0;
        //第一轮遍历，摩尔投票的抵消阶段
        for (int i=0;i<nums.length;i++){
            if (num1 == nums[i]){
                count1++;
                continue;
            }
            if (num2 == nums[i]){
                count2++;
                continue;
            }
            if (count1 ==0){
                num1 = nums[i];
                //解决bug1,这里一定要置1，不是置0
                count1=1;
                continue;
            }
            if (count2 ==0){
                num2 = nums[i];
                //解决bug1,这里一定要置1，不是置0
                count2=1;
                continue;
            }
            count1--;
            count2--;
        }
        count1=0;
        count2=0;
        //这里还需要遍历一边，因为题目没有像169题保证一定有。
        for (int i=0;i<nums.length;i++){
            if (nums[i]==num1){
               count1++;
               continue;
            }
            if (nums[i]==num2){
                count2++;
                continue;
            }
        }
        if (count1>nums.length/3){
            result.add(num1);
        }
        if (count2>nums.length/3){
            result.add(num2);
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums = {2,2,9,3,9,3,9,3,9,3,9,3,9,3,9,3,9};
        System.out.println(new ArraymajorityElement229LeetCode().majorityElement(nums));
    }
}
