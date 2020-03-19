import java.util.HashMap;
/**
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * 示例 1:
 * 输入:
 * "abccccdd"
 * 输出:
 * 7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * @author LemonLin
 * @Description :StringlongestPalindrome409LeetCode
 * @date 20.3.19-9:22
 * 思路：用一个hashmap中key存储字符，value存储字符个数。如果value是偶数，则最长
 * 回文串长度加value;如果是奇数，则最长回文串加value，奇数只能加一次,如果加过一次，
 * 第二次奇数只能加value-1(也就是加偶数)。
 */
public class StringlongestPalindrome409LeetCode {
    public int longestPalindrome(String s) {
        int result =0;
        //控制奇数个数只能加一次
        boolean flag =true;
        HashMap<Character,Integer> hashMap = new HashMap<>();
        for (int i=0;i<s.length();i++){
            hashMap.put(s.charAt(i),hashMap.getOrDefault(s.charAt(i),0)+1);
        }
        for (Character key:hashMap.keySet()){
            int value= hashMap.get(key);
            if (value%2==0){
                result+=value;
            }else {
                if (flag){
                    result+=value;
                    flag=false;
                }else {
                    result+=(value-1);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "assb";
        System.out.println(new StringlongestPalindrome409LeetCode().longestPalindrome(s));
    }
}
