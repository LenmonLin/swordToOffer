/**
 * 题目描述
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are
 * Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 总体思路：
 *      从后向前复制字符串；（为何要从后往前，没有必要，也可以从前往后）
 *      先遍历一遍字符串，记录空格的数量和字符串的长度，确定最后生成的字符串的长度
 *      开辟一个新的字符串数组来存放字符串和空格，遍历第二遍，遇到空格就填%20，否则
 *      就是常规复制。
 *      java中遍历字符串使用的是charAt()方法定位每个字符
 * @author LemonLin
 * @Description :ReplaceBlank04
 * @date 2018/3/3-21:14
 */
public class StringReplaceBlank04 {
    public String replaceSpace(StringBuffer str) {
        // write your code here
        int countBlank=0;
        for (int i=0;i<str.length();++i){
            if (str.charAt(i)==' '){
                countBlank++;
            }
        }
        char[] chars = new char[countBlank*2+str.length()];
        //char[] a = string.toCharArray()
        //strLength是记录原字符串下标范围
        int strLength =str.length()-1;
        for (int j=chars.length-1;j>=0;--j){
            if (str.charAt(strLength)==' '){
                chars[j]='0';
                chars[j-1]='2';
                chars[j-2]='%';
                j-=2;
            }else {
                chars[j]=str.charAt(strLength);
            }
            --strLength;
        }
        //将一个char数组转换成String 不能用chars.toString()
        return String.valueOf(chars);
    }
    public static void main(String[] args) {
        StringBuffer string = new StringBuffer("Mrs john hello");
        StringReplaceBlank04 stringReplaceBlank04 = new StringReplaceBlank04();
        String test = stringReplaceBlank04.replaceSpace(string);
        for (int i=0;i<test.length();i++){
            System.out.println(test.charAt(i));
        }
    }
}
