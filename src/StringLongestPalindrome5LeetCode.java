/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * 思路：首先回文的意思是正着念和倒着念一样，如：上海自来水来自海上。两种方法;暴力法：
 *  设置两个变量，变量i遍历字符串，变量j以i为中心，想左右两边扩张的比较，比如i+j,i-j,j从1开始不断
 *  增加。这种解法要判断字符串的长度是奇数还是偶数。可以在字符串中的字符两边都添加#，这样不管
 *  字符串长度是奇数还是偶数，都变成奇数处理，比如babad可以变成#b#a#b#a#d#
 *  时间复杂度O（N*M）
 *  方法二：动态规划
 *  难度主要还是递推公式的推导，维护一个二维数组 dp，其中 dp[i][j] 表示字符串区间 [i, j]
 *  是否为回文串，当 i = j 时，只有一个字符，肯定是回文串，如果 i = j + 1，说明是相邻字符，
 *  此时需要判断 s[i] 是否等于 s[j]，如果i和j不相邻，即 i - j >= 2 时，除了判断 s[i] 和 s[j]
 *  相等之外，dp[i + 1][j - 1]，若为真，就是回文串，通过以上分析，可以写出递推式如下：
 *dp[i][j]    = 1                                                if i == j
 *               = s[i] == s[j]                                 if j = i + 1
 *               = s[i] == s[j] && dp[i + 1][j - 1]    if i-j>=2
 *   时间复杂度是O(n^2),空间复杂度是O(N^2)。
 *
 *   大致直白的解释一下遍历过程：比如"babad" ，
 *   i是轴，j只能存在i之前，代码中体现是
 *   for(int i ;i<s.length;i++)
 *       for(int j;j<i;j++)
 *          .....
 *   所以遍历情况是: 这里的二维数组下标别记错了，j是算横坐标，i算纵坐标
 *      i=0时，j=0遍历b,dp[0][0]=true;
 *      i=1时，j=0遍历ba,dp[0][1]=false;
 *                  j=1遍历a,dp[1][1]=true;
 *      i=2，j=0时，遍历bab,dp[0][2]=true;
 *              j=1时，遍历ab,dp[1][2]=flase;
 *              j=2时，遍历b,dp[2][2]=true;
 *      i=3时，j=0,遍历baba,dp[0][3]=true;
 *                  j=1,遍历aba,dp[1][3]=true;
 *                  j=2,遍历ba,dp[2][3]=false;
 *                  j=3,遍历a...以此类推;
 *   方法三：中心扩散法
 *   遍历每一个索引，以这个索引为中心，利用“回文串”中心对称的特点，往两边扩散，看最多能扩散多远。要
 *   注意一个细节：回文串的长度可能是奇数，也可能是偶数。
 * @author LemonLin
 * @Description :StringLongestPalindrome
 * @date 19.6.22-21:41
 */
public class StringLongestPalindrome5LeetCode {
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        //用来记录最长回文串的长度
        int length=0;
        //用来记录最长回文串下标
        int left=0,right=0;
        //这里的二维数组下标别记错了，j是算横坐标，i算纵坐标,使用的是上三角矩阵
        boolean[][] dp =new boolean[s.length()][s.length()];

        for ( int i=0;i<s.length();i++){
            for (int j=0;j<=i;j++){
                if (i==j){
                    dp[j][i]=true;
                }else if (j+1==i){
                    dp[j][i]=(s.charAt(i)==s.charAt(j));
                }else if (i-j>=2){
                    dp[j][i]=(s.charAt(i) == s.charAt(j)&&dp[j+1][i-1]);
                }
                //这个判断是为了记录最长回文子串的下标，然后用substring输入返回。
                if (dp[j][i]&&length<i-j+1){
                    length =i-j+1;
                    left = j;
                    right = i;
                }
            }
        }
        //beginIndex -- 起始索引（包括）, 索引从 0 开始,endIndex -- 结束索引（不包括）
        return s.substring(left,right+1);
    }

    public static void main(String[] args) {

        String s = "cb";
        System.out.println(new StringLongestPalindrome5LeetCode().longestPalindrome(s));
//        for (int i=0;i<s.length();i++){
//            for (int j=0;j<=i;j++){
//                System.out.println("i===="+s.charAt(i)+"j===="+s.charAt(j));
//                System.out.println(s.charAt(i)==s.charAt(j));
//            }
//        }
    }
}
