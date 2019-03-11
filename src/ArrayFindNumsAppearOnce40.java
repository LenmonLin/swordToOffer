import javax.sound.midi.Soundbank;

/**
 * @author LemonLin
 * @Description :ArrayFindNumsAppearOnce40
 * @date 2019/3/11-11:38
 *
 * 题目描述
 * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
 *
 * 解题思路；
 * 首先我们考虑这个问题的一个简单版本：一个数组里除了一个数字之外，其他的数字都出现了两次。请写程序找
 * 出这个只出现一次的数字。
 *          这个题目的突破口在哪里？题目为什么要强调有一个数字出现一次，其他的出现两次？我们想到了异或运算的性
 *  质：任何一个数字异或它自己都等于0 。也就是说，如果我们从头到尾依次异或数组中的每一个数字，那么最终
 *  的结果刚好是那个只出现一次的数字，因为那些出现两次的数字全部在异或中抵消掉了。
 *          有了上面简单问题的解决方案之后，我们回到原始的问题。如果能够把原数组分为两个子数组。在每个子数
 *  组中，包含一个只出现一次的数字，而其它数字都出现两次。如果能够这样拆分原数组，按照前面的办法就
 *  是分别求出这两个只出现一次的数字了。我们还是从头到尾依次异或数组中的每一个数字，那么最终得到的结
 *  果就是两个只出现一次的数字的异或结果。因为其它数字都出现了两次，在异或中全部抵消掉了。由于这两个数
 *  字肯定不一样，那么这个异或结果肯定不为0 ，也就是说在这个结果数字的二进制表示中至少就有一位为1 。我
 *  们在结果数字中找到第一个为1 的位的位置，记为第N 位。现在我们以第N 位是不是1 为标准把原数组中的数字
 *  分成两个子数组，第一个子数组中每个数字的第N 位都为1 ，而第二个子数组的每个数字的第N 位都为0 。
 *  现在我们已经把原数组分成了两个子数组，每个子数组都包含一个只出现一次的数字，而其它数字都出现了两次。
 *  因此到此为止，所有的问题我们都已经解决。
 *
 *  需要考虑三个方面：
 *  1、isBit(int num,int indexBit)判断num的二进制表示中从某一端数起的indexBit位是不是1
 *  2、findFirstBitIs(int num)用来在整数num的二进制表示中找到最右边是1的位的坐标；考虑int的类型的取
 *  值范围了，如果int化成二进制，最多Integer.SIZE位
 *  3、FindNumsAppearOnce(int [] array,int num1[] , int num2[])，num1,num2分别为长度为1的数组。
 *  传出参数将num1[0],num2[0]设置为返回结果。思路就是利用异或的特性；
 */
public class ArrayFindNumsAppearOnce40 {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if (array==null ||array.length<2){
            return;
        }
        //先将所有的数字进行异或，得到两个只出现一次的数字的异或结果
        int temp = 0;
        for (int i =0; i<array.length;i++){
            temp ^= array[i];
        }

        //找到temp最右边是1的坐标；
        int indexOf1 = findFirstBitIs(temp);

        //把数组分成两部分进行异或
        for (int j=0;j<array.length;j++){
            if (isBit(array[j],indexOf1)){
                num1[0]^=array[j];
            }else {
                num2[0]^=array[j];
            }
        }
    }
    public int findFirstBitIs(int num){
        int indexBit=0;
        while (((num&1)==0)&&(indexBit<(int)Integer.SIZE)){
            num=num>>1;
            indexBit++;

        }
        return indexBit;
    }
    public boolean isBit(int num,int indexBit){
        num = num>>indexBit;
        return (num&1) ==1;
    }

    public static void main(String[] args) {
        ArrayFindNumsAppearOnce40 arrayFindNumsAppearOnce40 = new ArrayFindNumsAppearOnce40();
        int [] test ={2,4,3,6,3,2,5,5};
        int [] num1={0};
        int [] num2={0};
        arrayFindNumsAppearOnce40.FindNumsAppearOnce(test,num1,num2);
        System.out.println(num1[0]);
        System.out.println(num2[0]);
    }
}
