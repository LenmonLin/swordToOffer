/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * @author LemonLin
 * @Description :DoublePointertwoSum167LeetCode
 * @date 20.5.26-10:26
 * 思路：观察到是排序数组，所以可以用双指针，首尾指向。
 */
public class DoublePointertwoSum167LeetCode {
    public int[] twoSum(int[] numbers, int target) {
        int [] result = new int[2];
        int left =0;
        int right = numbers.length-1;
        while (left<=right&&left<numbers.length&&right>=0){
            if(numbers[left]+numbers[right]<target){
                left++;
            }else if (numbers[left]+numbers[right]>target){
                right--;
            }else {
                //因为本题规定数组下标是从1开始的，所以差一位
                result[0]=left+1;
                result[1]=right+1;
                break;
            }
        }
        return result;
    }
}
