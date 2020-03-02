/**
 * 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
 * 示例 1:
 * 输入:
 * "bbbab"
 * 输出:
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 * 示例 2:
 * 输入:
 * "cbbd"
 * 输出:
 * 2
 * 一个可能的最长回文子序列为 "bb"。
 * @author LemonLin
 * @Description :DPlongestPalindromeSubseq516LeetCode
 * @date 20.3.2-12:00
 * 思路：参考https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 * solution/zi-xu-lie-wen-ti-tong-yong-si-lu-zui-chang-hui-wen/
 */
public class DPlongestPalindromeSubseq516LeetCode {
    public int longestPalindromeSubseq(String s) {
        //设置dp[i][j]为：在子串 s[i..j] 中，最长回文子序列的长度为 dp[i][j];所以s[0,0]表明
        //第0个字符
        int dp[][] = new int[s.length()][s.length()];
        for (int i =0;i<s.length();i++){
            for (int j=0;j<s.length();j++){
                if (i==j){
                    dp[i][j]=1;
                }
                if (i>j){
                    dp[i][j]=0;
                }
            }
        }
        for (int i =s.length()-2;i>=0;i--){
            for (int j=i+1;j<s.length();j++){
                //这里为啥是i，对比1143题，为啥不是i-1,这个其实主要是由于dp的边界条件决定的
                //本题的初始条件dp[0][0]表示的是s.charAt(0)下标的情况。所以dp[i][j]和s.charAt(i)
                //以及s.charAt(j)是刚好对应上的。不需要调整，而1143题，dp[0][0]是表示text1
                // 以及text2都为空的情况，而不是为第0个字符的情况，所以需要在计算dp[i][j]时
                // 的那个循环中，将s.charAt(i-1)进行错位一个1.
                if (s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+2;
                }else {
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }

    public static void main(String[] args) {
        String s= "bbbab";
        System.out.println(new DPlongestPalindromeSubseq516LeetCode().
                longestPalindromeSubseq(s));
    }
}
