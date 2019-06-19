/**
 * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换
 * 成小写字母，之后返回新的字符串。
 * 题目中文写在前面是为了博客预览的时候能够一眼看见，不要再调注释顺序了
 * Implement function ToLowerCase() that has a string parameter str, and returns the
 * same string in lowercase.
 * Example 1:
 * Input: "Hello"
 * Output: "hello"
 * Example 2:
 * Input: "here"
 * Output: "here"
 * Example 3:
 * Input: "LOVELY"
 * Output: "lovely"
 * 思路：题目较为简单，方法一：用hashmap {a:A...}进行对应
 * 方法二：用(char)(chars[i]-'A'+'a'),来获得对应的转换
 * 主要问题出在String是不可改变的，需要改变其中的某个字符，可以考虑把String转换为char[],改完之后
 * 再转换为String即可
 * @author LemonLin
 * @Description :StringToLowerCase
 * @date 19.6.18-23:34
 */
public class StringToLowerCase {
    public String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for (int i=0;i<chars.length;i++){
            if (isCapitalLetter(chars[i])){
                chars[i]=(char)(chars[i]-'A'+'a');
            }
        }
        return String.valueOf(chars);
    }

    public boolean isCapitalLetter(char ch){
        if (ch>='A'&&ch<='Z'){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        String s ="LOVELY";
        System.out.println(new StringToLowerCase().toLowerCase(s));
    }
}
