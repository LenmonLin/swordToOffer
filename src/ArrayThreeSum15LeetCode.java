import java.util.List;
/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b
 * + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * 思路：首先对数组进行排序，排序后固定一个数nums[i]，再使用左右指针指向nums[i]后面的两端，
 * 数字分别为nums[L]和nums[R]，计算三个数的和sum判断是否满足为0，满足则添加进结果集
 * 如果nums[i]大于 0，则三数之和必然无法等于 0，因为排完序之后，num[L],num[R]都比num[i]
 * 大，即nums[i]大于 0，num[L],num[R]都大于0，三数之和必然无法等于 0，则如果结束循环
 * 如果nums[i] == nums[i-1]，则说明该数字重复，会导致结果重复，所以应该跳过
 * 当sum == 0 时，nums[L] == nums[L+1]则会导致结果重复，应该跳过，L++
 * 当sum== 0 时，nums[R] ==nums[R−1]则会导致结果重复，应该跳过，R−−
 * 时间复杂度：O(n^2)，n为数组长度
 * @author LemonLin
 * @Description :ArrayThreeSum15LeetCode
 * @date 19.8.1-16:11
 */
public class ArrayThreeSum15LeetCode {
    public List<List<Integer>> threeSum(int[] nums) {
        return  null;
    }

    public static void main(String[] args) {

    }
}
