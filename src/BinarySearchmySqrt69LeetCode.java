import java.util.Scanner;
/**
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * 思路：用二分法
 * @author LemonLin
 * @Description :MathmySqrt69LeetCode
 * @date 19.9.12-10:32
 * 注意考虑x*x的保存情况需要用long类型。保留左边的值，舍弃小数用left-1
 */
public class BinarySearchmySqrt69LeetCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        System.out.println(new BinarySearchmySqrt69LeetCode().mySqrt2(x));
    }
    public int mySqrt2(int x) {
        long left = 0;
        long right  = x;
        long mid =0;
        long sqrt =0;
        while (left<=right){
            //这么写是为了防止left+right大数越界
            mid = left+(right-left)/2;
            sqrt = mid*mid;
            if (sqrt>x){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        //最后向左-1,因为while退出循环的条件是left>right
        return (int)left-1;
    }
    public int mySqrt(int x) {

        long left = 0;
        long right  = x;
        long mid =0;
        long sqrt =0;
        while (left<right){
            //注意这里是加法，同时需要+1，取右中位数,为啥，如果输入为9
           // 如果取中点为左中位数，你看到死循环发生在 left = 3， right = 4 的时候，此时区间只有 2
            // 个元素。这是为什么呢？
           // 此时索引区间 [3, 4] 的中位数为左中位数，即 mid = 3 ，此时 square = 9 < 9 不成立，进入
            // left = mid 这个分支，你发现问题了吗，区间不发生收缩，即下一轮循环的索引区间还是
            // [3, 4]，此时中位数还取左中位数，即 mid = 3 ，square = 9 < 9 不成立，又进入 left = mid
            // 这个分支，死循环就是这样产生的。
            mid = (right+left+1)/2;
            //因为这里的mid相乘，可能会超过int的范围，所以需要把数都设置为long,最
            // 后结果转回int即可
            sqrt = mid*mid;
            if (sqrt>x){
                right = mid-1;
            }else {
                //这里不能取mid+1,因为是向下取整
                left = mid;
            }
        }
        //最后向左取
        return (int)left;
    }
}
