/**
 * 给定一个正整数数组 nums。
 * 找出该数组内乘积小于 k 的连续的子数组的个数。
 * 示例 1:
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 说明:
 * 0 < nums.length <= 50000
 * 0 < nums[i] < 1000
 * 0 <= k < 10^6
 * @author LemonLin
 * @Description :ArraynumSubarrayProductLessThanK713LeetCode
 * @date 20.6.25-16:50
 * 思路：参考：
 * https://leetcode-cn.com/problems/subarray-product-less-than-k/solution/cheng-ji-xiao-yu-kde-zi-shu-zu-by-eskimo6666/
 * https://leetcode-cn.com/problems/subarray-product-less-than-k/solution/javachao-xiang-xi-jie-xi-kan-bu-dong-lai-da-wo-by-/
 * 最开始想用前缀和的方法求连续子数组问题，发现乘积问题，很容易溢出，看了题解，这题
 * 是真的骚气。
 * 始终保证当前窗口中乘积小于k。当前窗口下的乘积小于k等价于当前窗口下子数组的各个
 * 连续子数组乘积也小于k。
 * 1.使用变量times存储个数
 * 2.我们让右指针不断前进，当窗口乘积小于k时，就让times+=窗口连续子数组个数
 * 3.当窗口乘积大于等于k时，我们就让乘积去除左指针对应的值，然后左指针右移，直至
 * 乘积小于k。
 * 那么窗口的连续子数组的个数是多少呢？
 * 以右指针为首增加子数组，[5,2]加6，增加[6]、[2,6]、[5,2,6]，个数即为R - L+ 1
 * 这个很R-L+1很巧妙，5,2,6,为什么只能算三个，因为5,2，在6加进来之前已经算过个数了
 * bug1:
 * [1,2,3]
 * k=0
 * 数组越界
 */
public class ArraynumSubarrayProductLessThanK713LeetCode {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        //记录个数
        int result =0;
        int temp = 1;
        int left =0;
        int right =0;
        while (right<nums.length){
            //乘积不断向右扩散
            temp=temp*nums[right];
            //如果乘积大于k,当前乘积除以left所指向的数
            while (temp>=k&&left<nums.length){
                temp=temp/nums[left];
                left++;
            }
            //这个公式是精髓，可以参考题解
            result +=right-left+1;
            right++;
        }
        //处理bug1
        if (result<0){
            result=0;
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums ={1,2,3};
        int k=0;
        System.out.println(new ArraynumSubarrayProductLessThanK713LeetCode().numSubarrayProductLessThanK(nums, k));
    }
}
