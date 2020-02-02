import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整
 * 数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * @author LemonLin
 * @Description :DoublePointerthreeSumClosest16LeetCode
 * @date 20.2.2-16:51
 * 思路：和LeetCode15三数之和为0的求解思路很像，题目的接近要求考虑用与目标值target
 * 的绝对值之差即可。
 * bug1:
 * 输入:
 * [1,1,1,0]
 * -100
 * 输出
 * 3
 * 预期结果
 * 2
 * bug2:
 * [0,1,2]
 * 3
 * 超时
 */
public class DoublePointerthreeSumClosest16LeetCode {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) return 0;
        Integer result = 0;
        //解决bug1，这个不能放在while循环里面每次重新定义，应该是全局变量。
        int min=Integer.MAX_VALUE;
        //先排序方便处理
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length-1;

            while (left <right) {
                int tempMin =nums[i] + nums[left] + nums[right];
                if(min>Math.abs(target-tempMin)){
                    min=Math.abs(target-tempMin);
                    result=tempMin;
                    //这个部分要去掉，和LeetCode15不一样，这里不用去
                    // 重，因为min可能不是最小的。所以不能立马退出。
//                    left++;
//                    right--;
//                    continue;
                }
                //解决bug2,超时，这里需要添加=，而不是tempMin<target,相等情况下
                // 无法移动下标
                if (tempMin<=target){
                    left++;
                }
                if (tempMin>target){
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums={0,1,2};
        int target=3;
        System.out.println(new DoublePointerthreeSumClosest16LeetCode().
                threeSumClosest(nums, target));
    }
}
