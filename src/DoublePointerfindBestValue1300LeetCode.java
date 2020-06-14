/**
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组
 * 中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差
 * 的绝对值最小）。
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。
 * 请注意，答案不一定是 arr 中的数字。
 * 示例 1：
 * 输入：arr = [4,9,3], target = 10
 * 输出：3
 * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 * 示例 2：
 * 输入：arr = [2,3,5], target = 10
 * 输出：5
 * 示例 3：
 * 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 * 输出：11361
 * 提示：
 * 1 <= arr.length <= 10^4
 * 1 <= arr[i], target <= 10^5
 * @author LemonLin
 * @Description :DoublePointerfindBestValue1300LeetCode
 * @date 20.6.14-14:55
 * 思路：参考：https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target/solution/er-fen-cha-zhao-by-liweiwei1419-2/
 * 这里要注意，只要是单调性问题，就可以考虑用二分法。
 * 这里观察发现需要寻找的值，具有单调性特点，所以使用二分法寻找。
 */
public class DoublePointerfindBestValue1300LeetCode {
    public int findBestValue(int[] arr, int target) {
        //求出最大值，因为所求值，最大只取到数组元素中的最大值
        int max =Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            if (arr[i]>max){
                max = arr[i];
            }
        }
       int left = 0;
        int right =max;
        while (left<=right){
            int mid = left +(right-left)/2;
            int sum = calculateSum(arr,mid);
            //sum 小于target 说明要让sum增大一些，所以left要去mid+1
            if (sum<target){
                left = mid+1;
            } else if (sum>target){
                right = mid-1;
            }else {
                left = mid;
                break;
            }
            if (left==right){
                break;
            }
        }
        //以上while循环结束之后，最后的结果应该是left;

        //这里已经求出结果在left附近。所以三个都判断一下即可
        int sum1 = calculateSum(arr,left-1);
        int sum2 = calculateSum(arr,left);
        int sum3 = calculateSum(arr,left+1);
        int temp =0;

        //这里返回最接近target的sum
        sum1 = Math.abs(target-sum1);
        sum2 = Math.abs(target-sum2);
        sum3 = Math.abs(target-sum3);
        temp = Math.min(sum1,Math.min(sum2,sum3));
        if (temp==sum1){
            return left-1;
        }else if (temp==sum2){
            return left;
        }else {
            return left+1;
        }
    }
    private int calculateSum(int[] arr,int threshold){
        int sum =0;
        for (int i = 0; i <arr.length ; i++) {
            sum+=Math.min(threshold,arr[i]);
        }
        return sum;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{2,3,5};
        int target = 10;
        System.out.println(new DoublePointerfindBestValue1300LeetCode().findBestValue(arr, target));
    }
}

