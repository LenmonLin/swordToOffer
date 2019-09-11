import java.util.Scanner;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *  示例 2:
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * 思路一：转换为字符串正常的翻转思路，首尾各两个指针，往中间靠拢，这里主要是几个特殊的情况需要
 * 考虑，实例2，有负数，先把-号取出来，翻转之后再添加进去；实例3，如果输入的尾数
 * 是0，那么翻转之后要把前置0去掉。
 * 思路二：有个更好的思路，整数直接翻转，把输入的整数%10，得到最后一位，然后再*10，就变到十位
 * 以此类推，也解决了前导0和负号问题，主要是要解决翻转之后的超过2147483647的时候返回0的问题。
 * 溢出条件有两个，一个是大于整数最大值MAX_VALUE，另一个是小于整数最小值MIN_VALUE，设当前
 * 计算结果为ans，下一位为pop。
 * 从ans * 10 + pop > MAX_VALUE这个溢出条件来看
 * 当出现 ans > MAX_VALUE / 10 且 还有pop需要添加 时，则一定溢出
 * 当出现 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出，7是2^31 - 1的个位数
 * 从ans * 10 + pop < MIN_VALUE这个溢出条件来看
 * 当出现 ans < MIN_VALUE / 10 且 还有pop需要添加 时，则一定溢出
 * 当出现 ans == MIN_VALUE / 10 且 pop < -8 时，则一定溢出，8是-2^31的个位数
 *举例来说：
 * 假设范围为-2^9~2^9-1，即-512~511； x=125 将其倒转为521后，溢出，在计算到ans=52,pop=1
 * 因为ans>511/10 所以溢出
 * 还有一种情况 x=315,倒转后为513后，溢出在计算到ans=51，pop=3,因为ans=511/10且pop>1
 * 所以溢出
 * @author LemonLin
 * @Description :Mathreverse7LeetCode
 * @date 19.9.11-11:03
 */
public class Mathreverse7LeetCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println(new Mathreverse7LeetCode().reverse(x));
    }


    public int reverse(int x){
        int ans = 0;
        int pop =0;
        while (x!=0){
             pop= x%10;
            //ans为翻转后的数
            ans=ans*10+pop;
            //这里x>=10是为了保证X不是最后一位的时候判断
            if ((x>=10&&ans>Integer.MAX_VALUE/10)||(ans==Integer.MAX_VALUE&&pop>7)){
                return 0;
            }
            if ((x<=-10&&ans<Integer.MIN_VALUE/10)||(ans==Integer.MIN_VALUE&&pop<-8)){
                return 0;
            }
            x=x/10;
        }
        return ans;
    }

    //转换为字符串的写法极为笨重。pass
    public int reverse2(int x) {
        String string = String.valueOf(x);
        char [] chars = string.toCharArray();
        int left =0;
        int right =chars.length-1;
        int result =0;
        boolean rightZero = true;
        while (left<right){
            if (chars[left]=='-'){
                left++;
            }
            //判断尾部是否连续0
            while (rightZero==true&&chars[right]=='0'){
                rightZero=true;
                right--;
            }
            if (rightZero)
            rightZero = false;
            char temp = chars[left];
            chars[left++]=chars[right];
            chars[right--]=temp;
        }
        //这里可以直接处理-号
        result = Integer.valueOf(String.valueOf(chars));
        return result;
    }
}
