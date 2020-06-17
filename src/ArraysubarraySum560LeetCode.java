import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * @author LemonLin
 * @Description :ArraysubarraySum560LeetCode
 * @date 20.2.11-10:26
 * 思路：考虑用滑动窗口法。
 * 再思考：不能用滑动窗口，因为这样至少数组要有序才可以。
 * bug1:
 * 最后执行的输入：
 * [1]
 * 0
 * java.lang.ArrayIndexOutOfBoundsException:
 * bug2:
 * 输入:
 * [1,2,3]
 * 3
 * 输出
 * 1
 * 预期结果
 * 2
 * bug3:
 * 输入:
 * [100,1,2,3,4]
 * 6
 * 输出
 * 0
 * 预期结果
 * 1
 * 参考：https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/dai-ni-da-tong-qian-zhui-he-cong-zui-ben-fang-fa-y/
 * https://segmentfault.com/a/1190000022853798
 * https://zhuanlan.zhihu.com/p/107778275
 * 那我把所有子数组都穷举出来，算它们的和，看看谁的和等于 k 不就行了。
 * 关键是，如何快速得到某个子数组的和呢，比如说给你一个数组 nums，让你实现一个接口
 * sum(i, j)，这个接口要返回 nums[i..j] 的和，而且会被多次调用，你怎么实现这个接口呢？
 * 因为接口要被多次调用，显然不能每次都去遍历 nums[i..j]，有没有一种快速的方法在 O(1)
 * 时间内算出 nums[i..j] 呢？这就需要前缀和技巧了。
 * 	前缀和： 从 第 0 项 到 当前项 的 总和
 * 如果用一个数组 prefixSum 表示：
 * prefixSum[x]：nums 的 第 0 到 第 x 项 的总和，可以推出
 * prefixSum[x] = nums[0] + nums[1] +…+nums[x]
 * 所以，nums 某一项 = 两个相邻前缀和之差：nums[x] = prefixSum[x] - prefixSum[x - 1]
 * 所以，nums 的 第 i 到 j 项 的总和:nums[i] +…+nums[j]=prefixSum[j] - prefixSum[i - 1]
 * 特别注意：
 * 	当只求num[0]的前缀和时，num[0]= prefixSum[0] - prefixSum[0 - 1]
 * 即num[0]= prefixSum[0] - prefixSum[ - 1]，所以要特别设置prefixSum[ - 1]
 * =0；这样等式才能成立。这个要特殊考虑！！
 *
 * 摈弃 prefixSum 数组，引入哈希表
 * 可以不用 prefixSum 数组吗？可以。
 * 因为我们不关心 前缀和 具体对应哪一项，只关心 前缀和 的值和 出现次数。
 * 所以出现过的前缀和，和对应的出现次数，存入 map 即可
 * 思路整理
 * map 存：
 * 键： 前缀和，从第 0 项到当前项的总和
 * 值： 这个 前缀和 值出现了几次
 * 遍历 nums 之前，我们让 -1 情况下的前缀和为 0，这样通式在边界情况也能适用map
 * 初始放入 0:1 键值对，即已经出现 1 次为 0 的前缀和遍历 nums 的每一项，求当前项
 * 的前缀和，存入 map 中之前没有存过，则存入，初始值为 1。之前存过，则对应值
 * +1，即出现次数 +1
 * 边存边查看 map ，如果 map 中已存在 key 为 —— 当前前缀和 - k
 * 说明存在 【之前出现过的前缀和】，它的值满足 【当前前缀和】-【之前求出的前缀和】
 * === k把 【之前出现过的前缀和】出现的次数，累加给 count
 * 一句话总结：
 * 根据 当前前缀和，在 map 中寻找【相减 === k】的 目标前缀和。目标前缀和是一个
 * 数值，出现这个数值可能不止 1 次，假设为 n 次，就等价于，找到 n 个连续子数组的求
 * 和 === k，遍历 nums 数组每一项，n 不断累加给 count，最后返回 count
 */
public class ArraysubarraySum560LeetCode {

    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> hashMap =new HashMap();
        int count =0;
        int sum =0;
        //这里应该联想到prefixSum数组的坐标在坐标0之前补充0，也就是（-1,0），虽
        // 然数组坐标不能为-1，但是可以这么理解，然后就导致了hashMap中0默认出现
        // 了1次。
        hashMap.put(0,1);
        for (int i =0;i<nums.length;i++){
            sum+=nums[i];
            if (hashMap.containsKey(sum-k)){
                count+=hashMap.get(sum-k);
            }
            hashMap.put(sum,hashMap.getOrDefault(sum,0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        int [] nums ={1,1,1};
        int k=1;
        System.out.println(new ArraysubarraySum560LeetCode().
                subarraySum(nums, k));
    }
}
