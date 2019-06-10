/**
 * @author LemonLin
 * @Description :StringRepeatedSubstringPattern
 * @date 2019/6/9-17:13
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending
 * multiple copies of the substring together. You may assume the given string consists of lowercase
 * English letters only and its length will not exceed 10000.
 * Example 1:
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 *
 * Example 2:
 * Input: "aba"
 * Output: False
 *
 * Example 3:
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，
 * 并且长度不超过10000。
 * 思路：
 *  求出子字符串，再把它拼接起来，和原字符串对比，看是否相等。怎么求出子字符串这是个问题?
 *  用整除的方法，因为一个子字符串的长度一定能被原字符串整除。所以从1开始遍历到原字符串的一半的长度。
 *  判断每次遍历的长度能否被原字符串的长度整除，如果能整除，复制该长度的串判断是否能与原字符串相等。
 */
public class StringRepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        if (s==null|| s.equals(" "))return false;
        //用来记录重复个数
        int cnt =0;
        String substring = "";
        String copystring = "";
        for (int i=1;i<=s.length()/2;i++){
           if (s.length()%i==0){
               cnt = s.length()/i;
               substring = s.substring(0,i);
               //这里加了一层判断，如果最初的前两个子串不相等，就说明不是子串，就不用继续比较
               if (substring.equals(s.substring(i,2*i))){
                   copystring = substring;
                   while (cnt>1){
                       copystring =copystring+substring;
                       cnt--;
                   }
                   if (copystring.equals(s))return true;
               }
           }
        }
        return false;
    }

    public static void main(String[] args) {
        String test = "abcabcabcabc";
        boolean result = new StringRepeatedSubstringPattern().repeatedSubstringPattern(test);
        System.out.println(result);
    }
}
