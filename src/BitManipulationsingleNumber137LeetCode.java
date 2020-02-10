/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找
 * 出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例 2:
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 * @author LemonLin
 * @Description :BitManipulationsingleNumber137LeetCode
 * @date 20.2.10-11:58
 * 思路：参考方法三：https://leetcode-cn.com/problems/single-number-ii/solution/
 * xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--31/
 */
public class BitManipulationsingleNumber137LeetCode {
    public int singleNumber(int[] nums) {
        int count =0;
        int result =0;
        //这里因为是一次性统计数组中所有某一位的1，所有数组要放在内循环。位数放在
        // 外循环，为啥是32位，因为无符号整型范围就是32位。
        for (int j=0;j<32;j++){
            for (int i=0;i<nums.length;i++){
                //>>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该
                // 数为负数，则右移后高位同样补0
                //这里有个问题纠结了一下，怎么取出一个数的末尾是否是1还是0，只要与1与
                // 即可，获得最后一位，比如3->11,1->01,11和01与，因为1前面默认为0，
                // 所以任何数与0与都是0，所以只会获得最后一位。
                if(((nums[i]>>>j)&1)==1){
                    count++;
                }
            }
            if (count%3!=0){
                //<<表示左移移，不分正负数，低位补0,主意这里拼接各位数用的是位或|
                result = result | (1<<j);
                count =0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,1,0,1,99};
        System.out.println(new BitManipulationsingleNumber137LeetCode().
                singleNumber(nums));
    }
}
