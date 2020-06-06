import java.util.HashSet;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * @author LemonLin
 * @Description :HashSetlongestConsecutive128LeetCode
 * @date 20.6.6-15:22
 * 思路：参考https://leetcode-cn.com/problems/longest-consecutive-sequence/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-4/
 * 翻译一下题目：
*  给一个数组，求出连续的数字最多有多少个，时间复杂度要求是 O(n)。
 *  解法一：暴力解法：
 *  首先想一下最直接的暴力破解。我们可以用一个 HashSet 把给的数组保存起来。然后
 *  再考虑数组的每个数，比如这个数是 n，然后看 n + 1 在不在 HashSet 中，然后再看
 *  n + 2 在不在，接下来 n + 3、n + 4 直到在 HashSet 中找不到，记录当前的长度。
 *  然后继续考虑下一个数，并且更新最长的长度。
 *  解法二：上边的暴力破解有一个问题就是做了很多没必要的计算，因为我们要找最长的
 *  连续数字。所以如果是数组 54367，当我们遇到 5 的时候计算一遍 567。遇到 4 又计
 *  算一遍 4567。遇到 3 又计算一遍 34567。很明显从 3 开始才是我们想要的序列。
 * 换句话讲，我们只考虑从序列最小的数开始即可。实现的话，当考虑 n 的时候，我们先
 * 看一看 n - 1 是否存在，如果不存在，那么从 n 开始就是我们需要考虑的序列了。否则
 * 的话，直接跳过。
 * 这个时间复杂度的话就是 O(n) 了。虽然 for 循环里套了 while 循环，但每个元素其实
 * 最多也就是被访问两次。比如极端情况 987654 ，98765 循环的时候都不会进入 while
 * 循环，只有到 4 的时候才进入了 while 循环。所以总共的话， 98765 也只会被访问两
 * 次，所以时间复杂度就是 O(n) 了。
 */
public class HashSetlongestConsecutive128LeetCode {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        //第一遍扫描，先把数据都存入hashset中
        for (int i=0;i<nums.length;i++){
            hashSet.add(nums[i]);
        }
        //记录临时的最长连续数的长度。
        int count=0;
        int result =0;
        for (int i=0;i<nums.length;i++){
            int temp = nums[i];
            //如果不存在temp-1,那么说明temp就是最小的数。
            if(hashSet.contains(temp)&&!hashSet.contains(temp-1)){
                while (hashSet.contains(temp)){
                    temp +=1;
                    count++;
                }
                result = Math.max(count,result);
                //复原临时count
                count =0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums = {100,4,200,1,3,2};
        System.out.println(new HashSetlongestConsecutive128LeetCode().longestConsecutive(nums));
    }
}
