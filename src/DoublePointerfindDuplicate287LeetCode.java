/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），
 * 可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * @author LemonLin
 * @Description :DoublePointerfindDuplicate287LeetCode
 * @date 20.2.3-15:59
 * 思路：很特别的解法，把数组看做链表，骚气的不行。
 * 参考https://leetcode-cn.com/problems/find-the-duplicate-number/solution/
 * kuai-man-zhi-zhen-de-jie-shi-cong-damien_undoxie-d/
 */
public class DoublePointerfindDuplicate287LeetCode {
    public int findDuplicate(int[] nums) {
        int slow=0;
        int fast =0;
        while (true){
            //nums[nums[fast]]指针是快指针比较难理解，可以理解下标是指针，就是slow
            //数组元素也是指针就是，nums[fast],通常情况下，数组元素都比数组下标大，所
            // 以可以认为nums[fast]是快指针。
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow==fast){
                break;
            }
        }

        int finder =0;
        while (true){
            finder=nums[finder];
            slow = nums[slow];
            if (slow==finder){
                break;
            }
        }
        return finder;
    }
}
