/**
 *
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * @author LemonLin
 * @Description :DPmaxProduct152LeetCode
 * @date 20.1.31-19:50
 * 思路：用动态规划，设置dp[i]数组,记录nums从0到i乘积最大。观察可以发现:
 * dp[0]=2
 * dp[1]=max(dp[0]*nums[1],num[1])=max(2*3,3)=6
 * dp[2]=max(dp[1]*nums[2],num[2])=max(6*-2,-2)=-2
 * dp[3]=max(dp[2]*nums[3],num[3])=max(-2*4,4)=4
 * 求出最大为6;
 * 这样做有个bug1:(没考虑到负负得正)
 * 输入:
 * [-2,3,-4]
 * 输出
 * 3
 * 预期结果
 * 24
 * 解决方法：通过记录整个数组减号的个数，同时记录最小值（负数），如果偶数个负数
 * 就把dp[i]存入负负得正的那个较大的值。
 *
 * 提交过程中还出现了这个测试过不了：
 * bug2
 * 输入:
 * [-4,-3]
 * 输出
 * 0
 * 预期结果
 * 12
 * 解决：把dp[0]的初始化放到循环里面。
 * 又出现了bug3:
 * 输入:
 * [2,-5,-2,-4,3]
 * 输出
 * 20
 * 预期结果
 * 24
 * 自己奔溃了，看了题解：可以参考：https://leetcode-cn.com/problems/
 * maximum-product-subarray/solution/
 * xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--36/
 * 这个和自己的思路最接近。
 */
public class DPmaxProduct152LeetCode {
    //自己写的有bug ,先留下罪证，bug3没解决
    public int maxProduct2(int[] nums) {
        int dp [] = new int[nums.length];
        //初始化边界值，以便后期根据初始值开始迭代,加了记录-号，初始化就不能写在这里了
        //dp[0]=nums[0];
        int result=nums[0];
        int minResult =0;
        int minusCount =0;
        //特殊情况特殊考虑
        for (int i=0;i<nums.length;i++){

            if (nums[i]<0){
                minusCount++;
            }
            //当减号个数是除了0之外的偶数时，执行
            if (minusCount%2==0&&minusCount!=0){
                minResult =Math.max(minResult*nums[i],nums[i]);
                dp[i]=minResult;
            }else {
                //初始边界情况特殊处理
                if (i==0){
                    minResult=nums[i];
                    dp[i]=nums[i];
                }
                else {
                    minResult = Math.min(dp[i-1]*nums[i],nums[i]);
                    dp[i]=Math.max(dp[i-1]*nums[i],nums[i]);
                }
            }
            if (dp[i]>result){
                result = dp[i];
            }
        }
        return result;
    }
    /**
     * 这里有一个很经典的例子int [] nums ={2,-5,-2,-4,3};
    *                                   观察dpMax={2,-5,20,8,24}
     *                                         dpMin={2,10,-2,-80,-240}
     *                                         注意这里以dpMax[1]举例，dpMax[1]=-5，而不是2，
     *                                         注意dpMax表示以第 i 个元素的结尾的子数组，乘积最
     *                                         大的值，也就是这个数组必须包含第 i 个元素。之前一直
     *                                         以为dpMax[1]不应该是{2，-5}的最大值吗，其实不是
     *                                         {2，-5}的最大值已经保存起来result。而dpMax[1]
     *                                         存的是以nums[1]为结尾的乘积的最大值，也就是-5乘以
     *                                         前面的数所能获得的最大值。也就是比较2*(-5),与-5。
     *                                         这样说可能还是不理解。举个dpMax[4]比较好理解:
     *                                         dpMax[4]=max{
     *                                             nums[4],（可以看做自己乘以1）
     *                                             nums[4]*nums[3],
     *                                             nums[4]*nums[3]*nums[2],
     *                                              nums[4]*nums[3]*nums[2]*nums[1],
     *                                             nums[4]*nums[3]*nums[2]*nums[1]*nums[0],
     *                                         }
     *                                         可以看出每个比较对象都要包含nums[4]才可以。
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int[] dpMax = new int[n];
        dpMax[0] = nums[0];
        int[] dpMin = new int[n];
        dpMin[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < n; i++) {
            dpMax[i] = Math.max(dpMin[i - 1] * nums[i], Math.max(dpMax[i - 1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMin[i - 1] * nums[i], Math.min(dpMax[i - 1] * nums[i], nums[i]));
            result = Math.max(result, dpMax[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums ={2,-5,-2,-4,3};
        System.out.println(new DPmaxProduct152LeetCode().maxProduct(nums));
    }
}
