import java.util.LinkedList;
/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * @author LemonLin
 * @Description :GreedyremoveKdigits402LeetCode
 * @date 20.2.3-16:05
 * 思路：用栈可以解决：
 * 参考：
 * https://leetcode-cn.com/problems/remove-k-digits/solution/
 yi-diao-kwei-shu-zi-by-leetcode/
 * 注意几个特殊情况的处理：
 * 1、当我们离开主循环时，我们删除了 m 个数字，这比要求的要少，即（m<k）。
 * 在极端情况下，我们不会删除循环中单调递增序列的任何数字，即 m==0。在这种
 * 情况下，我们只需要从序列尾部删除额外的 k-m 个数字。
 * 2、一旦我们从序列中删除 k 位数字，可能还有一些前导零。要格式化最后的数字，我们
 * 需要去掉前导零。
 * 3、我们最终可能会从序列中删除所有的数字。在这种情况下，我们应该返回零，而不
 * 是空字符串。
 *bug1:
 * 输入:
 * "10"
 * 1
 * 输出
 * "10"
 * 预期结果
 * "0"
 * bug2:
 * 输入:
 * "100"
 * 1
 * 输出
 * 越界
 * 预期结果
 * "0"
 * bug3:
 * 输入:
 * "112"
 * 1
 * 输出
 * "12"
 * 预期结果
 * "11"
 * bug4:
 * 输入:
 * "1173"
 * 2
 * 输出
 * "117"
 * 预期结果
 * "11"
 * bug5:
* 输入:
* "1107"
* 1
* 输出
* "17"
* 预期结果
* "107"
 * bug6:
 * 输入:
 * "1234567890"
 * 9
 * 输出
 * "1"
 * 预期结果
 * "0"
 * //    所有的bug都是因为for里面的while循环没写出来
 * 这题调了一个小时，太尴尬了。
 */
public class GreedyremoveKdigits402LeetCode {
    public String removeKdigits(String num, int k) {
        //用队列比较好，因为后期还要从队列头部顺序输出结果
        LinkedList<Integer> queue = new LinkedList<>();
        //维护一个递增栈，当当前元素小于栈顶元素，则移掉栈顶元素。
        for (char c :num.toCharArray()) {
            while(queue.size() > 0 && k > 0 && queue.peekLast() >c-'0') {
                queue.removeLast();
                k -= 1;
            }
            queue.addLast(c-'0');
        }
        //处理356 ，k=1 这种情况
        while (k>0){
            queue.removeLast();
            k -= 1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        //处理前导0，0200这种情况，要把前面的0排除掉
        boolean leadingZero = true;
        for(Integer digit: queue) {
            if(leadingZero && digit == 0) continue;
            leadingZero = false;
            stringBuilder.append(digit);
        }
        //处理000这种情况，会被上面的前导零处理程序处理成""
        if (stringBuilder.toString().length()==0){
            return "0";
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String num = "356";
        int k=1;
        System.out.println(new GreedyremoveKdigits402LeetCode().removeKdigits(num, k));
    }
}
