/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方
 * 和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果
 * 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 * 示例：
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * @author LemonLin
 * @Description :ArrayisHappy202LeetCode
 * @date 20.4.3-22:10
 * 思路：参考：https://leetcode-cn.com/problems/happy-number/solution/
 shi-yong-kuai-man-zhi-zhen-si-xiang-zhao-chu-xun-h/
* 方法：使用“快慢指针”思想找出循环：“快指针”每次走两步，“慢指针”每次走一步，当二者
 * 相等时，即为一个循环周期。此时，判断是不是因为1引起的循环，是的话就是快乐数，否
 * 则不是快乐数。

 *观察计算结果，如果满足最后的平方和为1，那么再次进行平方和计算，结果应该还是1，
 * 这样就形成了循环。所以寻找循环的点，利用快慢指针。
 */
public class ArrayisHappy202LeetCode {
    public boolean isHappy(int n) {
        int slow =n;
        int fast = n;
        slow = helper(slow);
        fast = helper(fast);
        fast = helper(fast);
        while (slow!=fast){
            slow = helper(slow);
            fast = helper(fast);
            fast = helper(fast);
        }
        return slow==1;
    }
    //平方数相加
    private  int helper (int  n){
        int sum =0;
        while (n>0){
            //取出末位的数
            int bit = n%10;
            //平方之后加到结果中
            sum+=bit*bit;
            //去除已经平方计算的末尾的数
            n=n/10;
        }
        return sum;
    }
}
