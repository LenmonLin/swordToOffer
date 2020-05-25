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
 *  解法2 ：参考LeetCode5的中心扩散法：更为易懂。
 * 参考 https://leetcode-cn.com/problems/palindromic-substrings/solution/python3-zhong-xin-kuo-zhan-fa-by-312shan/
 * https://leetcode-cn.com/problems/palindromic-substrings/solution/liang-dao-hui-wen-zi-chuan-de-jie-fa-xiang-jie-zho/
 */
public class DPcountSubstrings647LeetCode {

    //中心扩散法
    int result =0;
    public int countSubstrings4(String s) {
        for (int i=0;i<s.length();i++){
            //奇数个数的回文串中心
            palindrome(s,i,i);
            //偶数个数的回文串中心
            palindrome(s,i,i+1);
        }
        return result;
    }
    //left和right两个坐标向左右扩散
    public void palindrome(String s, int left, int right) {
        while (left>=0&&right<s.length()){
            if (s.charAt(left)!=s.charAt(right)){
                break;
            }else{
                left--;
                right++;
                result++;
            }
        }
    }

    //只是把遍历顺序修改了，有点参考516LeetCode的遍历顺序。但是还是需要判断一
    // 下i<j的条件。
    public int countSubstrings3(String s) {
        int n = s.length();
        //默认为false
        boolean[][] dp = new boolean[n][n];
        int result=0;
        //注意这里j是外循环，j放在i后面。因为i不能比j大。
        for (int i=0;i<n;i++){
            dp[i][i] = true;
            result++;
        }
        for (int i=n-2;i>=0;i--){
            for (int j=i+1;j<n;j++){
                if (i+1<j-1){
                    dp[i][j]=dp[i+1][j-1]&&s.charAt(i)==s.charAt(j);
                }else {
                    dp[i][j]=s.charAt(i)==s.charAt(j);
                }
                if (dp[i][j]){
                    result++;
                }
            }
        }
        return result;
    }

    //这种解法是有问题的，因为这里要注意子串和子序列的区别，子序列的话可以通过
    // dp[i+1][j-1]也即字符串间隔(i+1,j-1)的子序列数目暂存记录来推测dp[i][j]的数目，
    // 因为子序列可以删除中间若干个元素的。所以原来的数据可以暂存。而子串不可用
    // 原来dp[i+1][j-1]的数目来推测dp[i][j]的数目，因为如果(i+1,j-1)的子串数目为3，
    // 即使接下来[i]==[j],那么(i,j)的子串数目也不一定为4，因为(i+1,j-1)的子串数目为3，
    // 不一定代表(i+1)==(j-1)如果(i+1,j-1)这个串本身不是子串，那么[i]==[j]也不能
    // 在(i+1)==(j-1)为子串的基础上子串数量加一，因为(i,j)即使在[i]==[j]的情况下也不
    // 一定是回文串。所以解法2这种想法有问题，只能用解法1Boolean来判断，最后计算
    // 总数true有几个，那么子串就有几个。
    public int countSubstrings2(String s) {
        int n = s.length();
        int[][] dp = new int [n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++) {
                if (i==j){
                    dp[i][j] =1;
                }
                if (i>j){
                    dp[i][j]=0;
                }
            }
        }
        for (int i=n-2;i>=0;i--){
            for (int j=i+1;j<n;j++){
                if (s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+1;
                }else {
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }

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
        System.out.println(new DPcountSubstrings647LeetCode().countSubstrings3(s));
    }
}
