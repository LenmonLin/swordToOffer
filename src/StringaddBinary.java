/**
 * @author LemonLin
 * @Description :StringaddBinary
 * @date 2019/6/2-21:41
 *
 * 题目：Given two binary strings, return their sum (also a binary string).
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * 翻译：
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * Example 1:
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 * 思路：
 * 考虑将字符串转换为数字进行处理。每个字符串从后往前遍历，一个一个字符进行处理，同时注意进位的处理
 * 1、用两个指针分别指向a和b的末尾，从后往前遍历，然后每次取出一个字符，转为数字，若无法取出字符
 * 则按0处理。
 * 2、定义进位carry，初始化为0，将从a,b取出的单个字符和carry进位三者加起来，对2取余即为当前位的数字，
 * 对2取商即为当前进位的值，记得最后还要判断下carry，如果为1的话，要在结果最前面加上一个1.
 *
 * 难点：获得每个索引位置的数值之后怎么拼接起来，比如从后往前获得的数值是1,0,1,1.怎么把这四个数字拼成
 * 1101
 * 解答：这里直接用stringbuilder的append方法，最后再reverse反转一下，感觉不是很优雅，暂时没想到其他
 * 办法
 */
public class StringaddBinary {
    public String addBinary(String a, String b) {
        //aindex bindex 记录字符串下标，因为从后往前遍历所以初始化坐标是字符串的长度减一
        int aindex=a.length()-1;
        int bindex=b.length()-1;
        int carry =0;
        //设置保存结果的字符串
        StringBuilder result = new StringBuilder();
        //用来记录从a,b字符串取出的单个字符的整数值
        int atemp ,btemp;
        //用来临时记录三者相加的和：
        int sum =0;
        while (aindex>=0 || bindex>=0){
            //减字符'0'是为了将字符转换为对应的整数
            if (aindex < 0){
                atemp =0;
            }else {
                atemp = a.charAt(aindex--)-'0';
            }
            if (bindex < 0){
                btemp =0;
            }else {
                btemp = b.charAt(bindex--)-'0';
            }
            sum = carry+atemp+btemp;
            result.append(sum%2);
            carry = sum/2;
        }
        if (carry ==1){
            result.append("1");
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1010";
        String b="1011";
        String result = new StringaddBinary().addBinary(a,b);
        System.out.println(result);
    }
}
