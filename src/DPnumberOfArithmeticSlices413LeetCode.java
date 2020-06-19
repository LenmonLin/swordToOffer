/**
 * 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * 例如，以下数列为等差数列:
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 以下数列不是等差数列。
 * 1, 1, 2, 5, 7
 * 数组 A 包含 N 个数，且索引从0开始。数组 A 的一个子数组划分为数组 (P, Q)，P 与 Q
 * 是整数且满足 0<=P<Q<N 。
 * 如果满足以下条件，则称子数组(P, Q)为等差数组：
 * 元素 A[P], A[p + 1], ..., A[Q - 1], A[Q] 是等差的。并且 P + 1 < Q 。
 * 函数要返回数组 A 中所有为等差数组的子数组个数。
 * 示例:
 * A = [1, 2, 3, 4]
 * 返回: 3, A 中有三个子等差数组: [1, 2, 3], [2, 3, 4] 以及自身 [1, 2, 3, 4]。
 * @author LemonLin
 * @Description :DPnumberOfArithmeticSlices413LeetCode
 * @date 20.4.20-22:20
 * 参考：https://leetcode-cn.com/problems/arithmetic-slices/solution/
 * deng-chai-shu-lie-hua-fen-by-leetcode/
 * 1、先思考暴力算法，因为是判断的子数列，所以子数列的首坐标需要一个循环，子数列的
 * 尾坐标需要一个循环。同时判断首坐标和尾坐标之间的数组是否是等差数列，暴力遍历，需要
 * 一个循环，所以是三重循环。
 * 2、如果已知[0,1,2]的等差数列个数，那么再加[0,1,2,3]应该是可以利用之前的结果的。
 *这里注意是算相邻的数，所以可以考虑dp[i]为以i结尾的等差数组的子数组的个数。然后
 * 再用动态规划
 */
public class DPnumberOfArithmeticSlices413LeetCode {
    public int numberOfArithmeticSlices(int[] A) {
        if(A.length<2)return  0;
        //设置dp[i]为以i结尾的等差数组的子数组的个数。
        int [] dp = new int[A.length];
        //sum存储以数组下标2,3,4,5...结尾的子数组的个数和。
        int sum =0;
        for(int i=2;i<A.length;i++){
            if (A[i-1]-A[i-2]==A[i]-A[i-1]){
                dp[i]=dp[i-1]+1;
                sum+=dp[i];
            }else {
                dp[i]=0;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] A={1, 2, 3, 4};
        System.out.println(new DPnumberOfArithmeticSlices413LeetCode().
                numberOfArithmeticSlices(A));
    }
}
