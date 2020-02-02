/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，
 * 返回移除后数组的新长度。
 *不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 示例 1:
 * 给定 nums = [1,1,1,2,2,3],
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * 你可以想象内部操作如下:
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 * @author LemonLin
 * @Description :DoublePointerremoveDuplicates80LeetCode
 * @date 20.2.2-17:49
 * 思路：用两个指针，这是很自然而然的想到的，但是写的时候就会发现很多bug,情况很
 * 复杂。看到一个符合思维的。
 * 参考：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 * solution/xie-ru-dian-bian-li-dian-si-lu-by-yuhhen/
 * 就是用一个slow指针记录要插入的位置，一个fast去遍历所有的数。这里的只能两个
 * 重复怎么解决，也是本题的关键，是把slow指针设置在起始位置为数组下标为2的地方。
 * 然后fast是跟slow-2对比，这样slow前面就能保证最多只有两个数字重复，很巧妙。
 */
public class DoublePointerremoveDuplicates80LeetCode {
    public int removeDuplicates(int[] nums) {
        if (nums.length<2)return nums.length;
        int slow =2;
        int fast=2;
        while (fast<nums.length){
            if (nums[fast]!=nums[slow-2]){
                nums[slow]=nums[fast];
                slow++;
                fast++;
            }else {
                fast++;
            }
        }
        return slow;
    }
}
