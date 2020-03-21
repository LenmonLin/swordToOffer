/**
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，
 * 从而可以得到恰好 z升 的水？
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * 你允许：
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * 示例 1: (From the famous "Die Hard" example)
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * 示例 2:
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 * @author LemonLin
 * @Description :MathcanMeasureWater365LeetCode
 * @date 20.3.21-9:06
 * 参考：https://leetcode-cn.com/problems/water-and-jug-problem/solution/
 * bao-li-shu-xue-by-powcai/
 * 数学方法：就是找ax + by = z，能否找到整数a, b使得方程有解。
 * 如果能找到整数解，也就是当且仅当z是a和b的最大公约数d的倍数。
 * 所以问题转换为求a和b的最大公约数d,并判断z是否是d的倍数。
 * bug1:
 * 输入0,0,0;
 */
public class MathcanMeasureWater365LeetCode {
    public boolean canMeasureWater(int x, int y, int z) {

        //是要用水杯放水的，比如如果x=1,y=2,z=4,肯定不够水杯放4升的水。水杯最多只
        // 能放3升的水
        if(x+y<z)return false;
        //处理bug1
        if (x==0&&y==0)return z==0;
        int temp = gcd(x,y);
        if (temp!=0&&z%temp ==0){
            return true;
        }
        return false;
    }
    //这里不用判断x和y哪个大，如果x大，y%x=y,就交换过来了，下一个循环，肯定是大数
    // 除以小数。同时这里要判断一下除数不能为0,如果除数为0，那么0和非零整数的最大公
    // 约数为该非零整数。比如0和2的最大公约数为2，因为0%2=0,2%2=0;
    public int gcd(int x,int y){
        return (x==0)?y:gcd(y%x,x);
    }
}
