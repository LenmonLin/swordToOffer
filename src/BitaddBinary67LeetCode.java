/**
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * @author LemonLin
 * @Description :BitaddBinary67LeetCode
 * @date 20.6.23-11:19
 * 思路：从后往前遍历，对两个字符串末位进行相加，保留进位和下一轮循环的两个字符串
 * 的末位进行相加，如此往复。
 */
public class BitaddBinary67LeetCode {
    public String addBinary(String a, String b) {
        char [] charsA = a.toCharArray();
        char [] charsB = b.toCharArray();
        StringBuilder result = new StringBuilder();
        //进位
        int  temp = 0;
        //要返回的结果的末位
        int digit = 0;
        //字符串A的结果的末位
        int digitA= 0;
        //字符串B的结果的末位
        int digitB = 0;
        int i=charsA.length-1,j=charsB.length-1;
        while (i>=0||j>=0){
            if (i>=0){
                digitA= charsA[i]-'0';
            }else {
                digitA=0;
            }
            if (j>=0){
                digitB = charsB[j]-'0';
            }else {
                digitB =0;
            }
            digit=(digitA+digitB+temp)%2;
            result.append(digit);
            i--;
            j--;
            //本轮的进位
            temp = (digitA+digitB+temp)/2;
            //如果都遍历完毕，还有进位要添加剩下的进位
            if (i<0&&j<0&&temp!=0){
                result.append(1);
            }
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";
        System.out.println(new BitaddBinary67LeetCode().addBinary(a, b));

    }
}
