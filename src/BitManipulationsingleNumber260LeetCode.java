/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
 * 找出只出现一次的那两个元素。
 * 示例 :
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * 注意：
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * @author LemonLin
 * @Description :BitManipulationsingleNumber260LeetCode
 * @date 20.2.10-17:11
 *参考：剑指offer40题
 */
public class BitManipulationsingleNumber260LeetCode {
    public int[] singleNumber(int[] nums) {
        if (nums==null ||nums.length<2){
            return nums;
        }
        //先将所有的数字进行异或，得到两个只出现一次的数字的异或结果
        int temp = 0;
        for (int i =0; i<nums.length;i++){
            temp ^= nums[i];
        }
        //找到temp最右边是1的坐标；
        int indexOf1 = findFirstBitIs(temp);
        //把数组分成两部分进行异或
        int result1 =0;
        int result2 =0;
        for (int j=0;j<nums.length;j++){
            if (isBit(nums[j],indexOf1)){
                //第indexOf1是1的数互相异或，得到第indexOf1是1得不同数
                result1^=nums[j];
            }else {
                result2^=nums[j];
            }
        }
        int [] result ={result1,result2};
        return  result;
    }
    private int findFirstBitIs(int num){
        int indexBit=0;
        while (((num&1)==0)&&(indexBit<(int)Integer.SIZE)){
            num=num>>1;
            indexBit++;
        }
        return indexBit;
    }
    //判断num在indexBit位置是否为1
    private boolean isBit(int num,int indexBit){
        num = num>>indexBit;//右移操作
        return (num&1) ==1;
    }

    public static void main(String[] args) {
        int [] nums={1,2,1,3,2,5};
        int[] ints = new BitManipulationsingleNumber260LeetCode().singleNumber(nums);
        for(int i:ints){
            System.out.println(i);
        }
    }
}
