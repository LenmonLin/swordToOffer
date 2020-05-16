import java.util.HashMap;

/**
 * 给定一个包含非负数的数组和一个目标整数 k，编写一个函数来判断该数组是否含有连续
 * 的子数组，其大小至少为 2，总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
 * 示例 1:
 * 输入: [23,2,4,6,7], k = 6
 * 输出: True
 * 解释: [2,4] 是一个大小为 2 的子数组，并且和为 6。
 * 示例 2:
 * 输入: [23,2,6,4,7], k = 6
 * 输出: True
 * 解释: [23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 * 说明:
 * 数组的长度不会超过10,000。
 * 你可以认为所有数字总和在 32 位有符号整数范围内。
 * @author LemonLin
 * @Description :HashmapcheckSubarraySum523LeetCode
 * @date 20.4.14-21:20、
 * 参考：https://github.com/labuladong/fucking-algorithm   前缀和技巧
 * 前缀和的思路是这样的，对于一个给定的数组 nums，我们额外开辟一个前缀和数组进行预处理：
 * int n = nums.length;
 * // 前缀和数组
 * int[] preSum = new int[n + 1];
 * preSum[0] = 0;
 * for (int i = 0; i < n; i++)
 *     preSum[i + 1] = preSum[i] + nums[i];
 * 这个前缀和数组 preSum 的含义也很好理解，preSum[i] 就是 nums[0..i-1] 的和。
 * 那么如果我们想求 nums[i..j] 的和，只需要一步操作 preSum[j+1]-preSum[i] 即可，
 * 而不需要重新去遍历数组了
 *
 * 关于使用HashMap使O(n)
 * 参考：https://leetcode-cn.com/problems/continuous-subarray-sum/solution/
 * lian-xu-de-zi-shu-zu-qiu-he-by-lenn123/
 * 我们要判断的是 (sum[j] - sum[i])%k 是否等于 0。
 * 根据 mod运算的性质，我们知道 (sum[j] - sum[i])%k = sum[j]%k - sum[i]%k
 * 故若想 (sum[j] - sum[i])%k =0，则必有 sum[j]%k = sum[i]%k。
 *bug1:
 * 输入:
 * [0,0]
 * 0
 * 输出
 * false
 * 预期结果
 * true
 *
 * bug2:
 * 输入:
 * [1,0]
 * 2
 * 输出
 * true
 * 预期结果
 * false
 */
public class HashmapcheckSubarraySum523LeetCode {
    //用前缀和思想和hashMap来改善O(n)
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> hashMap  = new HashMap<>();
        int sum =0;
        //除数为0 特殊处理
        if (k==0){
            for (int i =0;i<nums.length-1;i++){
                if (nums[i]==0&&nums[i]==nums[i+1]){
                    return true;
                }
            }
            return false;
        }
        //除数不为0
        for (int i =1;i<=nums.length;i++){
            sum +=nums[i-1];
            if (hashMap.containsKey(sum%k)){
                //为了确保子数组的大小至少为 2
                if (i-hashMap.get(sum%k)>=2){
                    return true;
                }
            }else {
                hashMap.put(sum%k,i);
            }
        }
        return false;
    }

    //用前缀和思想时间复杂度为O(n2)
    public boolean checkSubarraySum2(int[] nums, int k) {
        //先记录前序和
        int [] sum =new int [nums.length+1];
        sum[0]=0;
        //注意这里的写法Sum[i] 就是 nums[0..i-1] 的和，不包括nums[i]
        for (int i=1;i<=nums.length;i++){
            sum[i] =sum[i-1]+nums[i-1];
        }
        //开始计算
        for(int start=0;start<=nums.length-1;start++){
            for (int end=start+1;end<=nums.length;end++){
                //这里重点是要加上nums[start]，因为减去sum[start]的时候，nums[start]
                // 被减去了。
                int total = sum[end]-sum[start];
                //k可能等于0，要排除一下
                if (total==k||(k!=0&&total%k==0)){
                    return true;
                }
            }
        }
        return false;
    }

    //暴力算法，因为本题首尾下标都会变，所以是三重循环
    public boolean checkSubarraySum1(int[] nums, int k) {
        for(int start=0;start<nums.length-1;start++){
            for (int end=start+1;end<nums.length;end++){
                int sum =0;
                for (int i = start;i<=end;i++){
                    sum+=nums[i];
                }
                //k可能等于0，要排除一下
                if (sum==k||(k!=0&&sum%k==0)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int [] nums ={23,2,4,6,7};
//        int [] nums ={0,0};
        int [] nums ={1,0,0};
//        int k=6;
//        int k=0;
        int k=2;
        System.out.println(new HashmapcheckSubarraySum523LeetCode().
                checkSubarraySum1(nums, k));
    }
}
