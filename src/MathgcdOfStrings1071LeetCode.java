/**
 * 对于字符串 S 和 T，只有在 S = T + ... + T（T 与自身连接 1 次或多次）时，我们才
 * 认定 “T 能除尽 S”。返回最长字符串 X，要求满足 X 能除尽 str1 且 X 能除尽 str2。
 * 示例 1：
 * 输入：str1 = "ABCABC", str2 = "ABC"
 * 输出："ABC"
 * 示例 2：
 * 输入：str1 = "ABABAB", str2 = "ABAB"
 * 输出："AB"
 * 示例 3：
 * 输入：str1 = "LEET", str2 = "CODE"
 * 输出：""
 * 提示：
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 * str1[i] 和 str2[i] 为大写英文字母
 * @author LemonLin
 * @Description :MathgcdOfStrings1071LeetCode
 * @date 20.3.12-20:20
 *思路是利用辗转相除法：
 * public int gcd(int m, int n){
 *     return (m==0)?n:gcd(n%m, m);
 * }
 * 但是怎么联系起来，是个难题：
 * 如果它们有公因子 abc，那么 str1 就是 mm 个 abc 的重复，str2 是 nn 个 abc 的重
 * 复，连起来就是 m+nm+n 个 abc，好像 m+nm+n 个 abc 跟 n+mn+m 个 abc 是
 * 一样的。所以如果 str1 + str2 === str2 + str1 就意味着有解。
 * 我们也很容易想到 str1 + str2 !== str2 + str1 也是无解的充要条件。
 * 关键是这个无解的判断。有解就是用辗转相除法求出长度，然后接就是str1(0,长度)
 * 注意这里字符串相拼接对比只能用：(str1+str2).equals(str2+str1)
 */
public class MathgcdOfStrings1071LeetCode {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1+str2).equals(str2+str1)){
            return "";
        }
        return str1.substring(0,gcd(str1.length(),str2.length()));
    }
    public int gcd(int m, int n){
        return (m==0)?n:gcd(n%m, m);
    }
}
