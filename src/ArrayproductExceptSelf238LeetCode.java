/**
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 
 * 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组
 * 不被视为额外空间。）
 * @author LemonLin
 * @Description :ArrayproductExceptSelf238LeetCode
 * @date 20.2.10-17:48
 * 思路：从左向右乘，除去自身元素，1,1,2,6。
 *          从右向左乘，除去自身元素, 24,12,4,1.
 *          然后对应的位置相乘即可得到结果。因为对空间复杂度有要求，所以result最开始
 *          可以存从左向右乘积，而从右向左乘需要用一个新的变量保存才可以。
 */
public class ArrayproductExceptSelf238LeetCode {
    public int[] productExceptSelf(int[] nums) {
        int [] result = new int[nums.length];
        for (int i=0;i<nums.length;i++){
            if (i-1==-1){
                result[i] = 1;
                continue;
            }
            // 此时数组存储的是除去当前元素左边的元素乘积
            result[i] =result[i-1]*nums[i-1];
        }
        //这个temp设计很巧妙，需要保存右边的乘积
        int temp =1;
        for (int j=nums.length-1;j>=0;j--){
            result[j] = result[j]*temp;
            temp=temp*nums[j];
        }
        return result;
    }

    public static void main(String[] args) {
        int [] nums ={1,2,3,4};
        int[] ints = new ArrayproductExceptSelf238LeetCode().productExceptSelf(nums);
        for(int i:ints){
            System.out.println(i);
        }
    }
}
