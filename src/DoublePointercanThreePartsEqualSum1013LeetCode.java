/**
 * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
 * 形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] +
 * A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 就可以将数组三等分。
 * 示例 1：
 * 输出：[0,2,1,-6,6,-7,9,1,2,0,1]
 * 输出：true
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * 示例 2：
 * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
 * 输出：false
 * 示例 3：
 * 输入：[3,3,6,5,-2,2,5,1,-9,4]
 * 输出：true
 * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 * 提示：
 * 3 <= A.length <= 50000
 * -10^4 <= A[i] <= 10^4
 * @author LemonLin
 * @Description :DoublePointercanThreePartsEqualSum1013LeetCode
 * @date 20.3.11-9:11
 * 思路：根据题意理解，分成的数组下标都是连续的，所以处理起来方便很多，如果不连
 * 续，本题就有点难。
 * 逻辑就是先遍历一遍，记录总和，然后sum/3=target,左右两个指针从数组头尾开始遍历，
 * 如果等于target，同时左右两个指针还没交集，那么剩下的中间部分也等于target。返回
 * true。
 * bug1:
 * 输入:
 * [3,3,6,5,-2,2,5,1,-9,4]
 * 输出
 * false
 * 预期结果
 * true
 * bug2:
 * 超出时间限制
 * [6,1,1,13,-1,0,-10,20]
 * bug3:
 * [0,2,1,-6,6,7,9,-1,2,0,1]
 * java.lang.ArrayIndexOutOfBoundsException: Index 11 out of bounds for length 11
 * bug4:
 * 输入:
 * [3,3,6,5,-2,2,5,1,-9,4]
 * 输出
 * false
 * 预期结果
 * true
 * bug5:
 * 输入:
 * [1,-1,1,-1]
 * 输出
 * true
 * 预期结果
 * false
 * 思路：本题看似简单，其实很多坑。不好整。
 */
public class DoublePointercanThreePartsEqualSum1013LeetCode {
    public boolean canThreePartsEqualSum(int[] A) {
        if (A.length<=2) return false;
        int left  = 0;
        int right = A.length-1;
        int sum = 0;
        //第一次遍历记录总和，目的求出sum/3
        for (int i=0;i<=right;i++){
            sum+=A[i];
        }
        int target = sum/3;
        //为了处理bug5，如果默认为0的话，target就==leftSum了。
        int leftSum =A[left];
        int rightSum =A[right];
        //这里需要修改，否则如果写成left<right 可能只分成两部分。
        while (left+1<right){
            //这里需要判断left+1<right，加一很关键，说明left和right没有交集碰到，还需要
            // 有中间元素
            if (leftSum==target&&rightSum==target){
                return true;
            }
            //加left+1<right为了处理bug3，这里leftSum只能用!=,不能用<,因为可能是有
            // 负数先加。
            while (left+1<right&&leftSum!=target){
                //因为初始化leftSum和rightSum有值了，所以需要先加left.
                left++;
                leftSum+=A[left];
            }
            while (left+1<right&&rightSum!=target){
                right--;
                rightSum+=A[right];
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int [] A = {3,3,6,5,-2,2,5,1,-9,4};
        System.out.println(new DoublePointercanThreePartsEqualSum1013LeetCode().
                canThreePartsEqualSum(A));
    }
}
