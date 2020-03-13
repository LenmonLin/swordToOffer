import java.util.HashMap;

/**
* 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于
 * ⌊ n/2 ⌋ 的元素。
* 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
* 示例 1:
* 输入: [3,2,3]
* 输出: 3
* 示例 2:
* 输入: [2,2,1,1,1,2,2]
* 输出: 2
 * @author LemonLin
 * @Description :HashMapmajorityElement169LeetCode
 * @date 20.3.13-19:35
 * 思路比较清晰：遍历数组，用hashmap记录每个数出现的次数(key为数，value为对应数
 * 出现的次数)如果超过总数组长度的二分之一，就返回这个key
 */
public class HashMapmajorityElement169LeetCode {
    public int majorityElement(int[] nums) {
        if (nums==null||nums.length==0)return 0;
        HashMap<Integer,Integer>  hashMap  = new HashMap<>();
        for (int i=0;i<nums.length;i++){
                hashMap.put(nums[i],hashMap.getOrDefault(nums[i],0)+1);
                if (hashMap.get(nums[i])>nums.length/2){
                    return nums[i];
                }
        }
        return 0;
    }
}
