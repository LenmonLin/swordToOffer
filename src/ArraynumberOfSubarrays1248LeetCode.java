import java.util.HashMap;

/**
 * 给你一个整数数组 nums 和一个整数 k。
 * 如果某个连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * 请返回这个数组中「优美子数组」的数目。
 * 示例 1：
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * 示例 2：
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * 示例 3：
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 * 提示：
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * @author LemonLin
 * @Description :ArraynumberOfSubarrays1248LeetCode
 * @date 20.4.21-11:30
 * 思路：用前缀和思想。
 * count大-count小=k,所以推出count大 -k = count小，所以hashMap的put要放的是count小，
 * 或者放count大-k也行，是第一轮。而containskey要判断的是count大-k，不能判断
 * count小，如果判断coun小，那没k什么事了。
 */
public class ArraynumberOfSubarrays1248LeetCode {
    public int numberOfSubarrays(int [] nums,int k){
        int result =0;
        //key 为奇数的个数，value为出现这个个数的次数
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        //初始条件是出现0个奇数一个，参考LeetCode560
        hashMap.put(0,1);
        //用来记录奇数个数的前缀和
        int count =0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]%2==1){
                count+=1;
            }
            if (hashMap.containsKey(count-k)){
                result+=hashMap.get(count-k);
            }
            hashMap.put(count,hashMap.getOrDefault(count,0)+1);
        }
        return result ;
    }

    public static void main(String[] args) {
        int [] nums ={2,2,2,1,2,2,1,2,2,2};
        int k = 2;
        System.out.println(new ArraynumberOfSubarrays1248LeetCode().
                numberOfSubarrays(nums, k));
    }
}
