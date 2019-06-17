/**
 * @author LemonLin
 * @Description :StringAddStrings
 * @date 19.6.13-22:47
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1
 * and num2.
 * Note:
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * 注意：
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 * 思路：题目要求不能直接用Integer的库，那么考虑用取出字符串中的每个字符-'0'来转换为整数差来计算，
 * 从后往前遍历字符串，要考虑进位的问题。
 * 难点：获得每个索引位置的数值之后怎么拼接起来，比如从后往前获得的数值是2,3,5,1.怎么把这四个数字拼成
 *  1532
 *解答：这里直接用stringbuilder的append方法，最后再reverse反转一下，感觉不是很优雅，暂时没想到其他
 *   办法
 */
public class StringAddStrings {
    public String addStrings(String num1, String num2) {
        int index1=num1.length()-1,index2=num2.length()-1;
        int carry=0;
        StringBuilder stringBuilder = new StringBuilder();
        int temp1=0,temp2=0;
        int sum=0;
        while (index1>=0||index2>=0){
            //减字符'0'是为了将字符转换为对应的整数
            if (index1 < 0){
                temp1 =0;
            }else {
                temp1 = num1.charAt(index1--)-'0';
            }
            if (index2 < 0){
                temp2 =0;
            }else {
                temp2 = num2.charAt(index2--)-'0';
            }
            sum=carry+temp1+temp2;
            stringBuilder.append(sum%10);
            carry = sum/10;
        }
        if (carry ==1){
            stringBuilder.append("1");
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        String num1="122";
        String num2="23";
        System.out.println(new StringAddStrings().addStrings(num1, num2));
    }
}
