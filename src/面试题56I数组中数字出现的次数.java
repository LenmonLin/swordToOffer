/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个
 * 只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 * 限制：
 * 2 <= nums <= 10000
 * @author LemonLin
 * @Description :面试题56I数组中数字出现的次数
 * @date 20.4.28-10:44
 * 参考剑指offerArrayFindNumsAppearOnce40
 * 思路：首先我们考虑这个问题的一个简单版本：一个数组里除了一个数字之外，其他的数
 * 字都出现了两次。请写程序找出这个只出现一次的数字。
 *       这个题目的突破口在哪里？题目为什么要强调有一个数字出现一次，其他的出现两次？
 * 我们想到了异或运算的性质：任何一个数字异或它自己都等于0 。也就是说，如果我们从
 * 头到尾依次异或数组中的每一个数字，那么最终的结果刚好是那个只出现一次的数字，因
 * 为那些出现两次的数字全部在异或中抵消掉了。有了上面简单问题的解决方案之后，我们
 * 回到原始的问题。如果能够把原数组分为两个子数组。在每个子数组中，包含一个只出现
 * 一次的数字，而其它数字都出现两次。如果能够这样拆分原数组，按照前面的办法就是分
 * 别求出这两个只出现一次的数字了。我们还是从头到尾依次异或数组中的每一个数字，那
 * 么最终得到的结果就是两个只出现一次的数字的异或结果。因为其它数字都出现了两次，
 * 在异或中全部抵消掉了。由于这两个数字肯定不一样，那么这个异或结果肯定不为0，也就是
 * 说在这个结果数字的二进制表示中至少就有一位为1 。我们在结果数字中找到第一个为1的
 * 位的位置，记为第N 位。现在我们以第N 位是不是1 为标准把原数组中的数字分成两个子
 * 数组，第一个子数组中每个数字的第N 位都为1 ，而第二个子数组的每个数字的第N 位都
 * 为0 。现在我们已经把原数组分成了两个子数组，每个子数组都包含一个只出现一次的数字，
 * 而其它数字都出现了两次。因此到此为止，所有的问题我们都已经解决。
 *  需要考虑两个方面：
 *  1、isBit(int num,int indexBit)判断num的二进制表示中从某一端数起的indexBit位是不是1
 *  2、findFirstBitIs(int num)用来在整数num的二进制表示中找到最右边是1的位的坐标；
 *  考虑int的类型的取值范围了，如果int化成二进制，最多Integer.SIZE位
 */
public class 面试题56I数组中数字出现的次数 {
    public int[] singleNumbers(int[] nums) {
        if (nums==null ||nums.length<2){
            return new int[nums.length];
        }
        //先将所有的数字进行异或，得到两个只出现一次的数字的异或结果
        int temp = 0;
        for (int i =0; i<nums.length;i++){
            temp ^= nums[i];
        }
        //找到temp最右边是1的坐标；
        int indexOf1 = findFirstBitIs(temp);

        int [] result = new int[2];

        //把数组分成两部分进行异或
        for (int j=0;j<nums.length;j++){
            if (isBit(nums[j],indexOf1)){
                //第indexOf1是1的数互相异或，得到第indexOf1是1得不同数
                result[0]^=nums[j];
            }else {
                result[1]^=nums[j];
            }
        }
        return result;
    }
    public int findFirstBitIs(int num){
        int indexBit=0;
        while (((num&1)==0)&&(indexBit<(int)Integer.SIZE)){
            num=num>>1;
            indexBit++;
        }
        return indexBit;
    }
    //判断num在indexBit位置是否为1
    public boolean isBit(int num,int indexBit){
        num = num>>indexBit;//右移操作
        return (num&1) ==1;
    }
}
