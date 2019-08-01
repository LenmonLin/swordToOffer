/**
 *给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * 如果不存在最后一个单词，请返回 0 。
 * 解题思路：
 *      从后往前遍历，一边遍历一边记录长度，当遇到空格时输出长度，当遍历结束没有遇到空格，根据题
 *      意输出0。要注意考虑Input: "Hello "的情况，要先用trim 把两边空格消掉。再计算
 * @author LemonLin
 * @Description :StringLengthofLastWord
 * @date 2019/6/9-10:18
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word in the string.
 * If the last word does not exist, return 0.
 *Note: A word is defined as a character sequence consists of non-space characters only.
 *Example:
 * Input: "Hello World"
 * Output: 5
 */
public class StringLengthofLastWord58LeetCode {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        if (s == null)return 0;
        int result =0;
        for (int i=s.length()-1;i>=0;i--){
            if (Character.isSpaceChar(s.charAt(i))){
                break;
            }else {
                result ++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String test = "a ";
        int result = new StringLengthofLastWord58LeetCode().lengthOfLastWord(test);
        System.out.println(result);
    }
}
