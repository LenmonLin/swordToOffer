import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 
 * 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * @author LemonLin
 * @Description :HashMaptwoSum1LeetCode
 * @date 20.2.23-23:27
 * 思路：参考：https://leetcode-cn.com/problems/two-sum/solution/
 * liang-shu-zhi-he-by-leetcode-2/
 * 因为是查找两个数，所以第一遍遍历把数值和下标对应起来。第二遍遍历的时候通过
 * int rest = target - nums[i];转换成查找一个数的情况，只要数组中存在rest即可完成输出。
 */
public class HashMaptwoSum1LeetCode {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> hashMap = new HashMap();
        for (int i=0;i<nums.length;i++){
            hashMap.put(nums[i],i);
        }
        for (int i =0;i<nums.length;i++){
            int rest = target - nums[i];
            if (hashMap.containsKey(rest)&&hashMap.get(rest)!=i){
                return new int[]{i,hashMap.get(rest)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
