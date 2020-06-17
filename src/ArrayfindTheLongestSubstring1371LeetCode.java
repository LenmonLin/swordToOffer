import java.util.HashMap;

/**
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，
 * 即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 * 示例 1：
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 * 示例 2：
 * 输入：s = "leetcodeisgreat"
 * 输出：5
 * 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 * 示例 3：
 * 输入：s = "bcbcbc"
 * 输出：6
 * 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u
 * 都出现了 0 次。
 * 提示：
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母。
 * @author LemonLin
 * @Description :ArrayfindTheLongestSubstring1371LeetCode
 * @date 20.5.20-20:58
 *参考：https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/solution/jiang-ti-mu-yi-bu-bu-deng-jie-zhuan-hua-fei-qi-ji-/
 * 可以说考察的内容非常非常多了，感觉可以是hard难度了，不过收获很多。
 * 1、引入 前缀和：将双变量转为单变量
 * [i, j]区间确定出一个子串，题目条件：[i, j]内出现的元音均为偶数
 * 变量有 2 个，如果找齐所有区间需要两层循环
 * 我们可不可以转为单变量？所有事情在一次遍历中完成
 * 其实这类“子串”、“子数组”问题，可以利用【前缀和相减】转化为：
 * [0, j]的元音次数 -− [0, i - 1] 对应的元音次数 == 偶数
 * 求 [0,x]的元音次数，变量就只有 1 个了
 * 2、。。。
 * 还是直接看上面的参考链接吧，这题考察的内容真的特别多。
 */
public class ArrayfindTheLongestSubstring1371LeetCode {
    public int findTheLongestSubstring(String s) {
        int res = 0;
        int state = 0; // 前缀区间的state状态
        HashMap<Character,Integer> vowel = new HashMap<>();
        // 对照表
        vowel.put('a',1);
        vowel.put('e',2);
        vowel.put('i',4);
        vowel.put('o',8);
        vowel.put('u',16);
        //这个hashMap存储的是key：前缀区间的状态，value为preSum数组下标，这个
        // 可以参考LeetCode560的理解。为啥要存下标，因为题目求得是最远距离，其实
        // 还有点像LeetCode523中用到的技巧。
        HashMap<Integer,Integer> map = new HashMap<>();// map存放各个前缀区间的state
        map.put(0,-1);
        int distance =0;
        for (int i = 0; i < s.length(); i++) { // 遍历str串
            char  temp = s.charAt(i); // 获取当前遍历的字符
            if (vowel.containsKey(temp)) { // 当前遍历的字符是元音
                state ^= vowel.get(temp); // 求出当前前缀区间的state
                if (!map.containsKey(state)) { // 当前state没存过
                    map.put(state,i);// 存入该state，和对应的位置
                }
            }
             distance= i - map.get(state); //求距离，即满足条件的子串长度
            res = Math.max(res, distance);// 试图刷新最长记录
        }
        return res;
    }
}
