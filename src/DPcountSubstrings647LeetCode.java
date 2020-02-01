/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 * 示例 1:
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 注意:
 * 输入的字符串长度不会超过1000。
 * @author LemonLin
 * @Description :DPcountSubstrings647LeetCode
 * @date 20.2.1-11:26
 * 思路：据说中心拓展法速度更快。
 * 参考：https://leetcode-cn.com/problems/palindromic-substrings/solution/
 * hui-wen-zi-chuan-zhi-647-hui-wen-zi-chuan-medium-l/
 * 这里的动态规划思路设置dp[i][j]为字符串下标为i到j的字符串(包括i和j)是否为回文串。
 * 状态转移方程为：dp[i][j]=dp[i+1][j-1]&&s.charAt(i)==s.charAt(j);
 * 这里有个bug需要处理。就是状态转移方程中有，i+1,j-1，不能数组越界。
 * 就会 造成i+1,j-1，不能取到.可以发现i+1<=j-1推出 2<=j-i,对立条件是1>=j-i
 * 也就是字符串之间的距离是1或者0,0已经考虑了，如果字符串首尾距离为1，也就是
 * 说只有两个字符，那是否为回文串可以直接用s.charAt(i)==s.charAt(j)。
 * 还有一个问题：dp数组填写顺序为：
 * 1 2 4   7
 *    3 5   8
 *       6   9
 *          10
 *  而dp[i][j]是有dp[i+1][j-1]推出，可以发现dp[i+1][j-1]比dp[i][j]先填，所以不会造成
 *  dp[i+1][j-1]状态还未确定就写dp[i][j]的情况。
 */
public class DPcountSubstrings647LeetCode {
    public int countSubstrings(String s) {
        int n = s.length();
        //默认为false
        boolean[][] dp = new boolean[n][n];
        int result=0;
        //注意这里j是外循环，j放在i后面。因为i不能比j大。
        for (int j=0;j<n;j++){
            for (int i=0;i<=j;i++){
                //处理边界
                if (i==j){
                    dp[i][j]=true;
                }else {
                    if (i+1<=j-1){
                        dp[i][j]=dp[i+1][j-1]&&s.charAt(i)==s.charAt(j);
                    }else {
                        dp[i][j]=s.charAt(i)==s.charAt(j);
                    }
                }
                if (dp[i][j]){
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s="aaa";
        System.out.println(new DPcountSubstrings647LeetCode().countSubstrings(s));
    }
}
