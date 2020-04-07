import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * @author LemonLin
 * @Description :DoublePointerthreeSum15LeetCode
 * @date 20.2.1-21:16
 * 思路：一个i遍历再在i后面加两个指针。总共三个变量即可确定规定和。
 * 这里题目的要求：答案中不可以包含重复的三元组。需要认真处理一下。
 * 这里关于重复三元组的处理，三个指针都需要处理：
 *  if (i-1>=0&&nums[i]==nums[i-1]) {
 *     continue;
 * }
 *while (left <right&&nums[left+1]==nums[left]){
 *   left++;
 * }
 *while (left<right&&nums[right]==nums[right-1]){
 *   right--;
 * }
 * 那么什么i是和i-1比较，left是和left+1比较，right又是right-1比较。
 * 这里是因为left=i+1,所以left不能和left-1比较，否则就会出现，如果目标下标为
 * 0,0,1,2,3,4，target = 4；
 * i=0; left=1,right  =5;
 * 这个时候下标组合0，1,5就会被跳过。所以i的比较应该往左边，left的比较应该往右边，
 * 这样这两个比较就不会重合，跳过应该有的解。right因为是从右边开始的，所以没人跟他
 * 重叠，所以right和right+1对比也可以，只要注意right+1边界问题即可。
 * 看了LeetCode18四数之和，其实还有一种写法，也可以写left与left-1对比，只要排除
 * left和i重叠情况即可。所以：
 * while (left <right&&nums[left+1]==nums[left]){
 *    left++;
 *  }改成一下写法也能通过：(添加了left-1>=i+1条件克制重复跳过)
 *  while (left <right&&left-1>=i+1&&nums[left]==nums[left-1]){
 *    left++;
 *  }
 * bug1:
* 输入:
* [0,0,0,0]
* 输出
* [[0,0,0],[0,0,0]]
* 预期结果
* [[0,0,0]]
 * bug2:
 * 输入:
 * [-2,0,0,2,2]
 * 输出
 * [[-2,0,2],[-2,0,2]]
 * 预期结果
 * [[-2,0,2]]
 */
public class DoublePointerthreeSum15LeetCode {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) return new ArrayList<>();
        ArrayList result = new ArrayList();
        //先排序方便处理
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length-1;
            //处理 bug1，第一种去重。因为nums[i-1]的时候已经判断了，所以num[i]
            //不需要判断，同时因为left=i+1,所以nums={-1,-1,-1,2}可以被记录的
            // 数组下标是0,1,3。而数组下标1,2,3就跳过了。
            if (i-1>=0&&nums[i]==nums[i-1]) {
                continue;
            }
            while (left <right) {
                if(nums[i] + nums[left] + nums[right] == 0){
                    ArrayList temp = new ArrayList();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    result.add(temp);
                    //这里为了处理bug2
                    while (left <right&&nums[left+1]==nums[left]){
                        left++;
                    }
                    while (left<right&&nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                    continue;
                }
                if (nums[i] + nums[left] + nums[right]<0){
                    left++;
                }
                if (nums[i] + nums[left] + nums[right]>0){
                    right--;
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int [] nums ={-1, 0, 1, 2, -1, -4};
//                {-1, 0, 1, 2, -1, -4};
        System.out.println(new DoublePointerthreeSum15LeetCode().threeSum(nums));
    }
}
