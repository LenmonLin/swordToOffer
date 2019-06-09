import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author LemonLin
 * @Description :StringBuddyStrings
 * @date 2019/6/5-22:25
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A
 * so that the result equals B.
 *翻译：
 * 给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返
 * 回 true ；否则返回 false 。
 *
 * Example 1:
 *
 * Input: A = "ab", B = "ba"
 * Output: true
 * Example 2:
 *
 * Input: A = "ab", B = "ab"
 * Output: false
 * Example 3:
 *
 * Input: A = "aa", B = "aa"
 * Output: true
 * Example 4:
 *
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 * Example 5:
 *
 * Input: A = "", B = "aa"
 * Output: false
 *
 * Note:
 * 0 <= A.length <= 20000
 * 0 <= B.length <= 20000
 * A and B consist only of lowercase letters.
 *
 * 思路：观察例子，特别例子2和3就会知道，必须交换有且只能一次相等才符合题意。
 * 如果两个字符串长度不相等，则肯定返回false；
 * 如果两个字符串长度相等：
 *      遍历两个字符串，用一个hash记录两个不相同字符串字符的下标，如果这个hash长度等于2，同时对应下标的字符
 *      相等，就符合题意，输出true;
 * 考虑特殊情况：
 *      Input: A = "aa", B = "aa" 返回也应该是true;
 *      假如起始时A和B就完全相等，那么只有当A中有重复字符出现的时候，才能返回 true。快速检测重复字符的方法
 *      就是利用 HashSet 的自动去重复功能，将A中所有字符存入 HashSet 中，若有重复字符，那么最终 HashSet
 *      的大小一定会小于原字符串A的长度。
 */
public class StringBuddyStrings {

    public boolean buddyStrings(String A, String B) {
        if(A.length() != B.length()){
            return false;
        }

        //只有当A中有重复字符出现的时候的解决方式
        HashSet hashSet = new HashSet();
        for (char a:A.toCharArray()) {
            hashSet.add(a);
        }
        if (A.equals(B)&&hashSet.size()<A.length())return true;

        ArrayList diff  = new ArrayList();
        for (int i =0;i<A.length();i++){
            if (A.charAt(i)==B.charAt(i)){
                continue;
            }else {
                diff.add(i);
            }
        }
        if (diff.size() ==2&&
                A.charAt((int) diff.get(0)) ==B.charAt((int)diff.get(1))&&
                A.charAt((int)diff.get(1))==B.charAt((int)diff.get(0))){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String  A = "ab";
        String B = "ca";
        boolean result = new StringBuddyStrings().buddyStrings(A,B);
        System.out.println(result);
    }
}
