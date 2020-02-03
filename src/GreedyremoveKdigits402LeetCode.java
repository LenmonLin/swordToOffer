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
 * yi-diao-kwei-shu-zi-by-leetcode/
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
 *
 * //    所有的bug都是因为for里面的while循环没写出来
 * 这题调了一个小时，太尴尬了。
 */
public class GreedyremoveKdigits402LeetCode {
    public String removeKdigits(String num, int k) {
//        if (num.length()<k) return null;
//        if (num.length()==k)return "0";
//        if (num.length()==2&&k==1){
//            if (num.charAt(0)-'0'>num.charAt(1)-'0'){
//                return num.substring(1,2);
//            }else {
//                return num.substring(0,1);
//            }
//        }
        //用队列比较好，因为后期还要从队列头部顺序输出结果
        LinkedList<Integer> queue = new LinkedList<>();
//    所有的bug都是因为for里面的while循环没写出来
        for(int i=0;i<num.length();i++) {
            while(queue.size() > 0 && k > 0 && queue.peekLast() > num.charAt(i)-'0') {
                queue.removeLast();
                k -= 1;
            }
            queue.addLast(num.charAt(i)-'0');
        }
        for(int i=0; i<k; ++i) {
            queue.removeLast();
        }
//        queue.addLast(num.charAt(0)-'0');
//        for (int i=1;i<num.length();i++){
//            if (k<=0){
//                queue.addLast(num.charAt(i)-'0');
//                continue;
//            }
//            if (num.charAt(i)-'0'>=queue.peekLast()){
//                queue.addLast(num.charAt(i)-'0');
//            }else {
//                queue.removeLast();
//                queue.addLast(num.charAt(i)-'0');
//                k--;
//            }
//        }
//        while (k>0&&queue.size()!=0){
//            queue.removeLast();
//            k--;
//        }
////        //如果都是递增数字
//        if (!flag){
//            return num.substring(0,num.length()-k);
//        }
        //删除了所有数字
        if (queue.size()==0){
            return "0";
        }
        boolean allZero=true;
        for (Integer integer:queue){
            if (integer !=0){
                allZero=false;
                break;
            }
        }
        if (allZero==true){
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        //处理前导零的问题。
        boolean leadingZero = true;
        for(Integer digit: queue) {
            if(leadingZero && digit == 0) continue;
            leadingZero = false;
            stringBuilder.append(digit);
        }

//        boolean zeroFlag = false;
//       while (!queue.isEmpty()){
//            if (!zeroFlag&&queue.peekFirst()==0){
//                queue.removeFirst();
//                if (queue.peekFirst()!=0){
//                    zeroFlag = true;
//                }
//                continue;
//            }
//            stringBuilder.append(queue.removeFirst());
//        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String num = "1234567890";
        int k=9;
        System.out.println(new GreedyremoveKdigits402LeetCode().removeKdigits(num, k));
    }
}
