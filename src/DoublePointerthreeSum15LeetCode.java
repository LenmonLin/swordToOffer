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
