/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移
 * 除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 示例 1:
 * 给定 nums = [3,2,2,3], val = 3,
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * 注意这五个元素可为任意顺序。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * @author LemonLin
 * @Description :DoublePointerremoveElement27LeetCode
 * @date 20.4.2-21:49
 * 思路：https://leetcode-cn.com/problems/remove-element/solution/
 * hua-jie-suan-fa-27-yi-chu-yuan-su-by-guanpengchn/
 * 本题看似标的是简单题，但是很巧妙。思维定式从相等的角度考虑，其实本题应该从不相
 * 等的地方考虑。result记录的是删除重复值后的坐标。
 */
public class DoublePointerremoveElement27LeetCode {
    public int removeElement(int[] nums, int val) {
        //result记录的是不相等的位置。很难想到
        int result =0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=val){
                nums[result]=nums[i];
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums={0,1,2,2,3,0,4,2};
        int val = 2;
        System.out.println(new DoublePointerremoveElement27LeetCode().removeElement(nums, val));
    }
}
