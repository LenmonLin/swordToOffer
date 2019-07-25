/**
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * 思路：看似简单，其实还是有点思考空间的，解决办法是当遇到当前字符是空格，且下一个字符不是空格时，算
 * 一个单词，初始情况cnt为1，就是把句子分成两部分，第一个单词，和之后的部分。
 * 判断字符是否为空格用Character.isSpaceChar
 * @author LemonLin
 * @Description :StringcountSegments
 * @date 19.6.10-10:59
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence
 * of non-space characters.
 * Please note that the string does not contain any non-printable characters.
 * Example:
 * Input: "Hello, my name is John"
 * Output: 5
 */
public class StringcountSegments434LeetCode {
    public int countSegments(String s) {

        s = s.trim();
        if (s.equals(""))return 0;
        int cnt =1;
        for (int i=0;i<s.length();i++){
            if (Character.isSpaceChar(s.charAt(i))&&(!Character.isSpaceChar(s.charAt(i+1)))){
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        String test= "";
        int result = new StringcountSegments434LeetCode().countSegments(test);
        System.out.println(result);
    }
}
