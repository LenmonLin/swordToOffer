/**
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * 示例：
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 * @author LemonLin
 * @Description :DPsumRange303LeetCode
 * @date 19.12.17-16:46
 * 用前缀和思想，参考LeetCode560,
 *  如果用一个数组 prefixSum 表示：
 *  prefixSum[x]：nums 的 第 0 到 第 x 项 的总和，可以推出
 *  prefixSum[x] = nums[0] + nums[1] +…+nums[x]
 *  所以，nums 某一项 = 两个相邻前缀和之差：nums[x] = prefixSum[x] - prefixSum[x - 1]
 *  所以，nums 的 第 i 到 j 项 的总和:nums[i] +…+nums[j]=prefixSum[j] - prefixSum[i - 1]
 *  特别注意：
 *  	当只求num[0]的前缀和时，num[0]= prefixSum[0] - prefixSum[0 - 1]
 *  即num[0]= prefixSum[0] - prefixSum[ - 1]，所以要特别设置prefixSum[ - 1]
 *  =0；这样等式才能成立。这个要特殊考虑！！
 */
public class ArraysumRange303LeetCode {
    static class  NumArray {

        int [] preSum ;
        public NumArray(int[] nums) {
            //统计前缀和。
            preSum = new int[nums.length];
            for (int i = 0; i <nums.length ; i++) {
                if (i==0){
                    preSum[i]=nums[i];
                    continue;
                }
                preSum[i]=preSum[i-1]+nums[i];
            }
        }

        public int sumRange(int i, int j) {
            if (i==0){
                return preSum[j];
            }
            return preSum[j]-preSum[i-1];
        }
    }
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
    public static void main(String[] args) {
        int [] nums={-2, 0, 3, -5, 2, -1};
        int i=0;
        int j=2;
        System.out.println(new NumArray(nums).sumRange(i, j));
    }
}
