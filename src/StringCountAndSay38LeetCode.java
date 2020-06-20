/**
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 * 注意：整数顺序将表示为一个字符串。
 * 示例 1:
 * 输入: 1
 * 输出: "1"
 * 示例 2:
 * 输入: 4
 * 输出: "1211"
 *思路：主要是题意不好理解：下一个数是对上一个数的描述，比方说 1211 里有 “ 1 个 1 ，
 *  1 个 2 ， 2
 * 个 1 ” ，那么 111221 就是它的下一个数。
 * 观察可以得出，遍历一个数字串，下一个数字串存储模式是，重复字符个数+该数字。
 * 比如1211，
 * 遍历1，遍历2,1与2不重复，下一个数字为11，
 * 遍历1,2与1不重复，下一个数字为12，链接1112
 * 遍历1，与上一个1重复，则下一个数字为21，链接111221
 * @author LemonLin
 * @Description :StringCountAndSay38LeetCode
 * @date 19.7.12-23:19
 * 参考：https://leetcode-cn.com/problems/count-and-say/solution/38-bao-shu-java-xun-huan-by-pphdsny/
 * 虽然是简单题，虽然题目一看就懂，但是想要写出来，总感觉哪里没有打通。写法上还是
 * 很有难度的。最外层循环用来控制坐标。内层通过对字符串不断的复制，来达到递推的效果。
 */
public class StringCountAndSay38LeetCode {
    public String countAndSay(int n) {
        String start = "1";
        for (int i=2;i<=n;i++){
            StringBuilder stringBuilder = new StringBuilder();
            int count = 1;
            char pre = start.charAt(0);
            for (int j=1;j<start.length();j++){
                char post = start.charAt(j);
                if (pre == post){
                    count++;
                }else {
                    stringBuilder.append(count).append(pre);
                    //这个赋值很巧妙
                    pre = post;
                    count =1;
                }
            }
            //当start=1时，不会进入内循环，会先执行这个语句。
            stringBuilder.append(count).append(pre);
            //重新赋值
            start = stringBuilder.toString();
        }
        return start ;
    }

    public static void main(String[] args) {
        System.out.println(new StringCountAndSay38LeetCode().countAndSay(2));
    }
}
