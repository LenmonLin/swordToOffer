/**
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
 * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回
 * 原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 * 示例1:
 *  输入："aabcccccaaa"
 *  输出："a2b1c5a3"
 * 示例2:
 *  输入："abbccd"
 *  输出："abbccd"
 *  解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
 * 提示：
 * 字符串长度在[0, 50000]范围内。
 * @author LemonLin
 * @Description :面试题0106字符串压缩
 * @date 20.3.16-11:36
 * 思路：比较直白：遍历字符串，如果有重复的，就记录在数字里面。最后组成新的字符串。
 */
public class 面试题0106字符串压缩 {
    public String compressString(String S) {
        if (S==null)return null;
        int i=0;
        StringBuilder stringBuilder = new StringBuilder();
        while (i<S.length()){
            int count=1;
            while (i+1<S.length()&&S.charAt(i)==S.charAt(i+1)){
                count++;
                i++;
            }
            stringBuilder.append(S.charAt(i));
            stringBuilder.append(count);
            i++;
        }
        if (stringBuilder.length()>=S.length()){
            return S;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String S ="";
        System.out.println(new 面试题0106字符串压缩().compressString(S));
    }
}
