/**
 * @author LemonLin
 * @Description :StrToInt49
 * @date 2019/3/4-19:57
 *
 * 题目描述
 * 将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
 * 要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。
 *
 *
 * 解题思路：
 *
 * 1、首先想到是将正常的字符串"1234567890" 转换为1234567890 ，以"123"作为例子：
 * 当我们扫描到字符串的第一个字符'1'时，由于我们知道这是第一位，所以得到数字1。
 * 当扫描到第二个数字'2'时，而之前我们知道前面有一个1，所以便在后面加上一个数字2，那前面的1相当于10，
 * 因此得到数字：1*10+2=12。继续扫描到字符'3'，'3'的前面已经有了12，由于前面的12相当于120，加上后
 * 面扫描到的3，最终得到的数是：12*10+3=123。因此，此题的基本思路便是：从左至右扫描字符串，把之前
 * 得到的数字乘以10，再加上当前字符表示的数字。
 *
 * 2、考虑非法输入：
 * 2.1：非数字输入，返回0
 * 2.2：空指针输入，返回0
 * 2.3：正负号
 * 2.4：数字溢出问题
*      2.4.1、java中int的取值范围为-2147483648到+-2147483648。System.out.println(Integer.MAX_VALUE);
*      2.4.2、首先jdk中定义int占4个字节32位，32位就是jvm仅仅给分配32个格子的空间，用以存放数据。
*      2.4.3、计算机中用0和1存放数据。那么，32个格子中放满0或1的方法，有2的32次方种。
*      2.4.4、但是java中int有正负之分，所以32个格子中占用一个格子标识正负，仅仅能用31个格子来标识数值。
*      最后int能标识的最大/最小数字是：2的31次方即+/- 2147483648。取值范围即为二者之间
 */
public class StringStrToInt49 {

    public int StrToInt(String str) {

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
                return 0;
            }
            result = result *10 +(int)(chars[i]-'0');

        }

        result = (int)Math.pow(-1,symbol)*result;
        return result;
    }

    public static void main(String[] args) {
        StringStrToInt49 stringStrToInt49 = new StringStrToInt49();
        int result = stringStrToInt49.StrToInt("123");
        System.out.println(result);
    }
}
