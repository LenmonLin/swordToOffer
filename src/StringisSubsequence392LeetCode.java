/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），
 * 而 s 是个短字符串（长度 <=100）。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对
 * 位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 * 后续挑战 :
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否
 * 为 T 的子序列。在这种情况下，你会怎样改变代码？
 * @author LemonLin
 * @Description :StringisSubsequence392LeetCode
 * @date 20.2.26-23:16
 * 思路：暴力解法，直接遍历s和t.这里要注意，s不能用循环，用的是外层变量来控制向下
 * 遍历。
 */
public class StringisSubsequence392LeetCode {
    public boolean isSubsequence(String s, String t) {
            if (s.length()==0)return true;
            int i=0;
            for (int j=0;j<t.length();j++){
                if (s.charAt(i)==t.charAt(j)){
                    i++;
                }
                if (i==s.length()){
                    return true;
                }
            }
        return false;
    }

    public static void main(String[] args) {
        String s ="axc";
        String t = "ahbgdc";
        System.out.println(new StringisSubsequence392LeetCode().isSubsequence(s, t));
    }
}
