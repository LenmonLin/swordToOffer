/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数
 * 字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续
 * 的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们
 * 对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串
 * 仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。
 * 如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * 示例 1:
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 * 示例 5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 * @author LemonLin
 * @Description :StringmyAtoi8LeetCode
 * @date 20.2.6-16:09
 * 思路：主要是如何处理大数问题，这里的思路是读取的字符串跟int最大值除以10比较，当
 * 字符串还没读取完毕已经比int最大值/10还大，那再读取下一位拼接，肯定比int最大值大。
 * 具体：result>Integer.MAX_VALUE/10
 *                     ||(result==Integer.MAX_VALUE/10&&
 *                     chars[i]-'0'>7)
 *后面为啥是大于7，因为最大值是2147483647，最后一位是7，大于这个也是大于最大数
 * bug1:
 * 输入:
 * "2147483648"
 * 输出
 * -2147483648
 * 预期结果
 * 2147483647
 */
public class StringmyAtoi8LeetCode {
    public int myAtoi(String str) {
        //利用-1的n次方来判断正负号，默认为0
        int symbol = 0;
        //start 来记录正负号的位置
        int start=0;
        if (str == null|| str.trim().equals("")){
            return 0;
        }
        char[] chars = str.trim().toCharArray();
        if (chars[0]=='+'){
            start =1;
        }
        if (chars[0]=='-'){
            start =1;
            symbol =1;
        }
        int result =0;
        for (int i= start;i<chars.length;i++){
            if (chars[i]>'9'||chars[i]<'0'){
                break;
            }
            //解决bug1:处理大数问题的关键
            if ((result>Integer.MAX_VALUE/10)
                    ||(result==Integer.MAX_VALUE/10)&&
                    chars[i]-'0'>7){
                return symbol==1?Integer.MIN_VALUE:Integer.MAX_VALUE;
            }
            result = result *10 +(chars[i]-'0');
        }
        result = (int)Math.pow(-1,symbol)*result;
        return result;
    }

    public static void main(String[] args) {
        String s =
                "224748364";
//                "2147483648";
//                "-91283472332";
//                "4193 with words";
        System.out.println(new StringmyAtoi8LeetCode().myAtoi(s));
    }
}
