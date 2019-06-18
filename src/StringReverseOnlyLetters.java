/**
 * @author LemonLin
 * @Description :StringReverseOnlyLetters
 * @date 19.6.17-23:04
 * Given a string S, return the "reversed" string where all characters that are not a letter
 *  stay in the same place, and all letters reverse their positions.
 * Example 1:
 * Input: "ab-cd"
 * Output: "dc-ba"
 * Example 2:
 * Input: "a-bC-dEf-ghIj"
 * Output: "j-Ih-gfE-dCba"
 * Example 3:
 * Input: "Test1ng-Leet=code-Q!"
 * Output: "Qedo1ct-eeLg=ntse-T!"
 * Note:
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122 
 * S doesn't contain \ or "
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置
 * 发生反转。
 *
 * 思路：
 * 翻转思路是设置两个首尾指针，用一个中间的变量来存，首尾互相交换,翻转的终止条件是，首尾指针想遇
 */
public class StringReverseOnlyLetters {
    public String reverseOnlyLetters(String S) {
        char[] chars = S.toCharArray();
        Reverse(chars,0,S.length()-1);
        return String.valueOf(chars);
    }
    public void Reverse(char[] chars,int start,int end){
        char  temp= ' ';
        while (start<end){
            //加start<end的条件是为了排除"7_28]"都没有字母的情况
            while (!isAlpha(chars[start])&&start<end){
                start++;
            }
            temp=chars[start];
            while (!isAlpha(chars[end])&&start<end){
                end--;
            }
            chars[start++]=chars[end];
            chars[end--]=temp;
        }
    }
    public boolean isAlpha(char ch) {
        if ((ch>='a'&&ch<='z')||
        ch>='A'&&ch<='Z'){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String string="7_28]";
        System.out.println(new StringReverseOnlyLetters().reverseOnlyLetters(string));
    }
}
