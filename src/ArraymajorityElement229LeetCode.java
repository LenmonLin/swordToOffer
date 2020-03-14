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
