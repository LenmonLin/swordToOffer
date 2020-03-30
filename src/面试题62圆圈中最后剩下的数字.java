/**
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。
 * 求出这个圆圈里剩下的最后一个数字。
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则
 * 删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * 示例 1：
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 * 输入: n = 10, m = 17
 * 输出: 2
 * 限制：
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 * @author LemonLin
 * @Description :面试题62圆圈中最后剩下的数字
 * @date 20.3.30-17:11
 * 思路:
 * 解法1：链表循环模拟；时间复杂度高
 * 解法2：数组%循环
 * 解法3，数学法：
 * 参考：https://www.jianshu.com/p/6ee5c7b21333
 * https://blog.csdn.net/u011500062/article/details/72855826
 * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-
 * shu-zi-lcof/solution/javajie-jue-yue-se-fu-huan-wen-ti-gao-su-ni-wei-sh/
 * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de
 * -shu-zi-lcof/solution/huan-ge-jiao-du-ju-li-jie-jue-yue-se-fu-huan-by-as/
 * 全文最重要的一点：只关心最终活着那个人的序号变化
 */
public class 面试题62圆圈中最后剩下的数字 {
    public int lastRemaining(int n, int m) {
        int result =0;
        //倒着往上求幸存的坐标。
        for(int i=2;i<=n;i++){
            result=(result+m)%i;
        }
        return result;
    }

    public static void main(String[] args) {
        int n =5;
        int m=3;
        System.out.println(new 面试题62圆圈中最后剩下的数字().lastRemaining(n, m));
    }
}
