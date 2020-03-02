/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺
 * 序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串
 * 的「公共子序列」是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 * 示例 1:
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace"，它的长度为 3。
 * 示例 2:
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc"，它的长度为 3。
 * 示例 3:
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0。
 * 提示:
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 * @author LemonLin
 * @Description :DPlongestCommonSubsequence1143LeetCode
 * @date 20.3.2-10:13
 * 思路：参考：https://leetcode-cn.com/problems/longest-common-subsequence/
 * solution/dong-tai-gui-hua-zhi-zui-chang-gong-gong-zi-xu-lie/
 */
public class DPlongestCommonSubsequence1143LeetCode {
    public int longestCommonSubsequence(String text1, String text2) {
        int length1 = text1.length();
        int length2 = text2.length();
        int [][] dp = new int[length1+1][length2+1];
        for (int i=0;i<length1;i++){
            dp[i][0]=0;
        }
        for (int j=0;j<length2;j++){
            dp[0][j]=0;
        }
        for (int i=1;i<=length1;i++){
            for (int j=1;j<=length2;j++){
                //注意这里易错，这里要写i-1，因为dp[0][j]和dp[i][0]边界需要设置为0,所以
                // 二重循环的下标必须从1开始，那么对于字符串来说，charAt(0)才是第一个
                // 字符，而dp[1][1]计算的才是第一个字符，所以要字符串charAt(i-1)和dp[i]
                // 对应上。
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[length1][length2];
    }

    public static void main(String[] args) {
        String text1 ="abc";
        String text2 ="def";
        System.out.println(new DPlongestCommonSubsequence1143LeetCode()
                .longestCommonSubsequence(text1, text2));
    }
}
