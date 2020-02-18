import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
*  给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空
*  格拆分为一个或多个在字典中出现的单词。
*  说明：
*  拆分时可以重复使用字典中的单词。
*  你可以假设字典中没有重复的单词。
*  示例 1：
*  输入: s = "leetcode", wordDict = ["leet", "code"]
*  输出: true
*  解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
*  示例 2：
*  输入: s = "applepenapple", wordDict = ["apple", "pen"]
*  输出: true
*  解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
*  注意你可以重复使用字典中的单词。
*  示例 3：
*  输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
*  输出: false
 * @author LemonLin
 * @Description :DPwordBreak139LeetCode
 * @date 20.2.18-10:15
 * 思路:参考：https://leetcode-cn.com/problems/word-break/solution/
 * xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--32/
 * 动态规划的做法：这里动态规划的表达式一次性无法完全解决问题，这也是本题的难点
 * 设置dp[i]表示字符串s[0,i]坐标能不能由wordDict组成。那么dp[s.length]=
 * dp[0] && wordDict.contains(s[0,len))
 *             || dp[1] && wordDict.contains(s[1,len))
 *             || dp[2] && wordDict.contains(s[2,len))
 *             ...
 *             || dp[len-1] && wordDict.contains(s[len - 1,len))
 * 这其中表达式包括 dp[i] && wordDict.contains(s[i,len)
 * 同时还需要一个循环。循环遍历dp[0]~dp[len]
 * 那么状态转移方程是：dp[1]=dp[0]&&wordDict.contains(s[0,1)
 *                               dp[2]=dp[0] && wordDict.contains(s[0,2))
 *                                          || dp[1] && wordDict.contains(s[1,2))
 *                               dp[3]=dp[0] && wordDict.contains(s[0,3))
 *                                          || dp[1] && wordDict.contains(s[1,3))
 *                                          || dp[2] && wordDict.contains(s[2,3))
 *    可以推出需要用到两个循环，第一个外循环控制dp[i],
 *    第二个内循环控制dp[i]=dp[0]&&...||
 *                                   dp[j]&&...||
 *                                   ......
 *                                   dp[i]...
 *    所以j<i;
 * 接下来看一看初始条件，因为表达式中其中有&&的关系，所以dp[0]默认应该设置为true。
 * 试想如果dp[0]=false，那么dp[1]肯定等于=false，以此类推，一直为false，肯定是
 * 错误的。所以dp[0]=true;
 */
public class DPwordBreak139LeetCode {
    public boolean wordBreak(String s, List<String> wordDict) {
        //把输入List转换为Set，后续容易操作用contains
        Set<String> wordDictSet=new HashSet(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0]=true;
        for (int i=1;i<=s.length();i++){
            for (int j=0;j<i;j++){
/** //为什么是这个写法，可以从这个例子看出来
*               dp[3]=dp[0] && wordDict.contains(s[0,3))
*                           || dp[1] && wordDict.contains(s[1,3))
*                          || dp[2] && wordDict.contains(s[2,3))
 *                          因为是或的关系，所以只要有一个是true，就可以覆盖之前的dp[i]
 *                          然后立马中断本次i位置的循环。
 *                          否则一直是false。
  */
                dp[i]=(dp[j]&&wordDictSet.contains(s.substring(j,i)));
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
//        String s ="catsandog";
        String s ="leetcode";
        List<String> wordDict =new ArrayList<>();
//        wordDict.add("cats");
//        wordDict.add("dog");
//        wordDict.add("sand");
//        wordDict.add("and");
//        wordDict.add("cat");
        wordDict.add("leet");
        wordDict.add("code");

        System.out.println(new DPwordBreak139LeetCode().wordBreak(s, wordDict));
    }
}
