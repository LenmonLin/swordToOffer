/**
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 * 注意：整数序列中的每一项将表示为一个字符串。
 * 示例 1:
 * 输入: 1
 * 输出: "1"
 * 解释：这是一个基本样例。
 * 示例 2:
 * 输入: 4
 * 输出: "1211"
 * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也
 * 就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在
 * 一起，也就是 "1211"。
 * @author LemonLin
 * @Description :ArraycountAndSay38LeetCode
 * @date 20.4.2-22:28
 * 思路：https://leetcode-cn.com/problems/count-and-say/solution/38-bao-shu-
 * java-xun-huan-by-pphdsny/
 * 观察发现，读的方式是个数+值的模式：
 * 从4->5分析，将4个每一位拆开看（个数+数字），4=1211 => 1=11，2=12，11=21，
 * 所以5=111221
 *虽然是简单题，思路也是容易理解，但是代码不好写啊。
 * 可以观察到这个是迭代的形式，就是由1->2
 * 由2->3
 * 这里有两个循环所以不容易想明白，第一个循环是控制数字，数字是几就控制几次复制，
 * 把上一次的结果复制到全局变量stringBuilder。
 * 第二个内循环，是读取个数+数字的遍历。
 */
public class ArraycountAndSay38LeetCode {
    public String countAndSay(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        int count =1;
        stringBuilder.append('1');
        for (int i =1;i<n;i++){
            int j =0;
            StringBuilder tempBuilder = new StringBuilder();
            while (j<stringBuilder.length()){
                if(j+1<stringBuilder.length()&&
                        stringBuilder.charAt(j)==stringBuilder.charAt(j+1)){
                    count++;
                }else {
                    //添加数字加字符。
                    tempBuilder.append(count).append(stringBuilder.charAt(j));
                    count=1;
                }
                j++;
            }
            stringBuilder = tempBuilder;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int n =2;
        System.out.println(new ArraycountAndSay38LeetCode().countAndSay(n));
    }
}
