/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘
 * 积，它们的乘积也表示为字符串形式。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，
 * 它们的乘积也表示为字符串形式。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * @author LemonLin
 * @Description :Stringmultiply43LeetCode
 * @date 20.2.6-18:36
 * 参考：https://mp.weixin.qq.com/s/bpqKRjdCcEN9OMpJR6vQHg
 * 思路1：考虑模拟最直接的小学学的手动竖式乘法。这里需要用到字符串相加的算法。
 * 以示例1为例：
*                              1 2 3
*                              4 5 6
*                         ---------------
*                              7 3 8
*                           6 1 5
*                        4 9 2
*                     ------------------
*                       5 6 0 8 8
 * 思路2：思路1看似直白，但是很多细节需要处理，特别是要写字符串加法函数，难度陡增，
 * 真的很难搞，有没有更加直白的解法，没那么多细节：可以把乘法做进一步的拆分：
 *                             1 2 3    下标设置为 i
 *                             4 5 6    下标设置为 j
 *                        ---------------
 *                               1 8
 *                            1 2
 *                         0 6
 *                            1 5
 *                         1 0
 *                      0 5
 *                         1 2
 *                      0 8
 *                   0 4
 *                    ------------------
 *                   0 5 6 0 8 8
 *  result      [ ] [][][] [][]
 *  有个公式：num1[i]和num2[j]的乘积对应的就是result[i+j]和res[i+j+1]这两个位置。
 *  bug1:
 */
public class Stringmultiply43LeetCode {
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        //两数相乘结果的位数最多就是两个乘数的位数相加
        int [] result = new int[num1.length()+num2.length()];

        StringBuilder stringBuilder = new StringBuilder();
        //从个位往前进行计算。
        //第二个数的坐标放在外循环，这样要注意下
        for (int j =num2.length()-1;j>=0;j--){
            for(int i=num1.length()-1;i>=0;i--){
                int n1 = num1.charAt(i)-'0';
                int n2 = num2.charAt(j)-'0';
                //两数相乘
                int mul= n1*n2;
                int p1 = i+j;
                int p2 = i+j+1;

                //这里有个难点，新的乘积到结果位的过程需要把结果位的p1和p2加入到新的
                // 乘积上，首先先处理把新的乘积和结果位的p2相加，然后相除之后才把商和
                // 结果位的p1相加。debug一遍容易懂，很难描述

                //存放临时的两数的和：这里要注意要把上一轮乘积的首位p1加上，而上一轮乘
                // 积的首位p1等于本轮乘积的p2,这个容易错误，
                int sumTemp = mul+result[p2];
                result[p2] = sumTemp%10;
                //注意这里要用加号，因为之前其实有计算结果。
                result[p1] += sumTemp/10;
            }
        }
        //循环结束之后，result中就是存的结果，但是开头可能有0，需要处理，而且需要转
        // 换为字符串返回；
        int i=0;
        //这里保存的在result中的末位的下标
        int last = num1.length()-1+num2.length()-1+1;
        while (i<=last&&result[i]==0){
            i++;
        }
        while (i<=last){
            stringBuilder.append(result[i]);
            i++;
        }
        return stringBuilder.toString();
    }
    //没写出来，太多细节了。
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int digit1 =0;
        int digit2 =0;
        for (int i =num2.length()-1;i>=0;i--){
            int n2 = num2.charAt(i)-'0';
            for(int j=num1.length()-1;j>=0;j--){
                int n1 = num1.charAt(j)-'0';
                digit1 = 0;

            }
        }
        return num1;
    }
    private String addStrings(String num1, String num2) {
        int index1=num1.length()-1,index2=num2.length()-1;
        int carry=0;
        StringBuilder stringBuilder = new StringBuilder();
        int temp1=0,temp2=0;
        int sum=0;
        while (index1>=0||index2>=0){
            //index1<0的判断是为了num1位数小于num2的情况下，把少的位数前面补0；这样方便计算
            //比如输入num1=12,num2=224;则处理之后为012,224;
            if (index1 < 0){
                temp1 =0;
            }else {
                //减字符'0'是为了将字符转换为对应的整数
                temp1 = num1.charAt(index1--)-'0';
            }
            if (index2 < 0){
                temp2 =0;
            }else {
                temp2 = num2.charAt(index2--)-'0';
            }
            //carry记录的是进位；
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
        String num1 ="123";
        String num2 ="456";
        System.out.println(new Stringmultiply43LeetCode().multiply2(num1, num2));
    }
}
