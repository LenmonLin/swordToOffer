/**
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2)
 * 是右上角的坐标。如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边
 * 接触的两个矩形不构成重叠。给出两个矩形，判断它们是否重叠并返回结果。
 * 示例 1：
 * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * 输出：true
 * 示例 2：
 * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * 输出：false
 * 说明：
 * 两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。
 * 矩形中的所有坐标都处于 -10^9 和 10^9 之间。
 * @author LemonLin
 * @Description :ArrayisRectangleOverlap836LeetCode
 * @date 20.3.18-10:18
 * 思路：进行两个判断，如果rec2的左下角坐标在rec1的两个坐标之中，就返回true，或者
 * 如果rec2的右上角坐标在rec1的两个坐标之中，也返回true。
 * bug1:
* 输入:
* [2,17,6,20]
* [3,8,6,20]
* 输出
* false
* 预期结果
* true
 * 解决：只要横纵坐标其中一个在范围之内，另外一个大于某个坐标即可，不需要在范围之内。
 * bug2:
 * 输入:
 * [0,0,1,1]
 * [1,0,2,1]
 * 输出
 * true
 * 预期结果
 * false
 * 看似简单，还是有点思考量的：自己做的有不少bug,没去解决。
 * 参考：https://leetcode-cn.com/problems/rectangle-overlap/solution/
 * tu-jie-jiang-ju-xing-zhong-die-wen-ti-zhuan-hua-we/
 * 反向思考：矩形重叠是二维的问题，所以情况很多，比较复杂。为了简化问题，我们可以
 * 考虑将二维问题转化为一维问题。既然题目中的矩形都是平行于坐标轴的，我们将矩形
 * 投影到坐标轴上。
 * 矩形投影到坐标轴上，就变成了区间。稍加思考，我们发现：两个互相重叠的矩形，它们
 * 在 xx 轴和 yy 轴上投影出的区间也是互相重叠的。这样，我们就将矩形重叠问题转化成
 * 了区间重叠问题。
 * 因为区间重叠的情况会比较多和复杂，所以找出区间不重叠会简单一些。然后取反就可以。
 * 转换为一维之后，区间不重叠的条件是：
 * 两个区间分别是 [s1, e1] 和 [s2, e2] 的话，区间不重叠的两种情况就是 e1 <= s2 和 e2 <= s1。
 * 投影到y轴上就是：rec1[3]<=rec2[1], rec2[3]<=rec1[1]
 * 投影到x轴上就是：rec1[2]<=rec2[0],rec2[2]<=rec1[0]
 * 但是这里要注意，这投影到X轴和Y轴都生效才可以。所以需要先存下来，再与一下
 */
public class ArrayisRectangleOverlap836LeetCode {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        //默认重叠，因为if判断条件是不重叠。
        Boolean y =true;
        Boolean x = true;
        //这里要写||，因为只要一个满足，就代表不重叠。
        if(rec1[3]<=rec2[1]||rec2[3]<=rec1[1]){
            y=false;
        }
        if(rec1[2]<=rec2[0]||rec2[2]<=rec1[0]){
            x=false;
        }
        //为什么这里用&&，因为有一个轴不重叠，就不重叠，所以用有一个false，就结果为false，
        //所以考虑用&&
        return x&&y;
    }
    //自己写的有bug,暂时不管。
    public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
        if(rec2[0]>=rec1[0]&&rec2[0]<=rec1[2]&&rec2[1]<=rec1[3]){
            return true;
        }
        if(rec2[2]>=rec1[0]&&rec2[2]<=rec1[2]&&rec2[2]<=rec1[3]){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int [] rec1 ={2,17,6,20};
        int [] rec2 ={3,8,6,20};
        System.out.println(new ArrayisRectangleOverlap836LeetCode().
                isRectangleOverlap(rec1, rec2));
    }
}
