import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在
 * 四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条
 * 件且不重复的四元组。
 * 注意：
 * 答案中不可以包含重复的四元组。
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 * @author LemonLin
 * @Description :DoublePointerfourSum18LeetCode
 * @date 20.2.1-22:42
 * 思路：和LeetCode15三数总体思想是一样的，只不过需要再加一个循环变量记录一个数。
 * 如何用两个循环记录遍历一维数组的不重复的组合。
 *  for (int i = 0; i <= nums.length - 3; i++) {
 *             for (int j=i+1;j<=nums.length - 2;j++){
 * bug1:
 * 输入:
 * [0,0,0,0]
 * 0
 * 输出
 * []
 * 预期结果
 * [[0,0,0,0]]
 */
public class DoublePointerfourSum18LeetCode {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) return new ArrayList<>();
        ArrayList result = new ArrayList();
        //先排序方便处理
        Arrays.sort(nums);
        //这里i的范围是因为i后面必须有三个数才能构成结果，同理，j后面必须有两个数才
        // 能构成结果。
        for (int i = 0; i <= nums.length - 3; i++) {
            for (int j=i+1;j<=nums.length - 2;j++){
                int left = j + 1;
                int right = nums.length-1;
                if (i-1>=0&&nums[i]==nums[i-1]||
                        //解决bug1,注意这里是j-1>=i+1不是j-1>=0,粗心了。
                        (j-1>=i+1&&nums[j]==nums[j-1]) ) {
                    continue;
                }
                while (left <right) {
                    if(nums[i] +nums[j] +nums[left] + nums[right] == target){
                        ArrayList temp = new ArrayList();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        result.add(temp);
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
                    if (nums[i] +nums[j]+ nums[left] + nums[right]<target){
                        left++;
                    }
                    if (nums[i] +nums[j]+ nums[left] + nums[right]>target){
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums={0,0,0,0};
//                {1, 0, -1, 0, -2, 2};
        int target =0;
        System.out.println(new DoublePointerfourSum18LeetCode().fourSum(nums, target));
    }
}
