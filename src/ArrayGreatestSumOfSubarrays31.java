
/**
 * @author LemonLin
 * @Description :GreatestSumOfSubarrays31
 * @date 2018/4/12-20:51
 *题目描述
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式
 * 识别中,常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,
 * 是否应该包含某个负数,并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从
 * 第0个开始,到第3个为止)。给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至
 * 少是1)
 *
 * 解题思路：
 * 基本思想用在线法处理，设置两个变量来记录，currentSum记录在线过程中是否累加和大于0，
 * 如果大于0就继续累加，如果小于0就舍弃；
 * GreatSum用于记录最大的值,过程中和currentSum比较
 *
 * 要点二：
 * MIN_VALUE = 0x80000000 和 MAX_VALUE = 0x7fffffff 就是补码表示的Integer的最小值(-2^31)和
 * 最大值(2^31-1)。至于为什么采用补码表示，简单的说就是方便运算，
 * 80000000一共8位16进制，也就是32位的2进制，2进制写法位数太多了，不展开写了，这也是为什么用
 * 16进制表示的原因。
 * 详细可自行Google一下或找本基础教材翻一下。至于Integer的最大值最小值为什么是这两个数，
 * 这是因为Java语言规范规定int型为4字节，不管是32/64位机器，这就是其所宣称的跨平台的基础部分。
 */
public class ArrayGreatestSumOfSubarrays31 {

    public int FindGreatestSumOfSubArray(int[] array) {
        if(array.length==0||array==null){
            return 0;
        }
        int currentSum=0;

        //注意这里GreatSum不能设置为0，应该设置成一个极小的数，否则如果都是数组都是负数的话，
        //就返回0，这个是有问题的
        int GreatSum=Integer.MIN_VALUE;
        for (int i=0;i<array.length;++i){
            if (currentSum<0){
                //如果小于0，就舍弃前面currentSum的和，把当前数字作为currentSum的起点
                currentSum = array[i];
            }else {
                currentSum+=array[i];
            }
            if (currentSum>GreatSum){
                GreatSum=currentSum;
            }
        }
        return GreatSum;
    }

    public static void main(String[] args) {

        int [] array = {1,-2,3,10,-4,7,2,-5};
        ArrayGreatestSumOfSubarrays31 arrayGreatestSumOfSubarrays31 = new ArrayGreatestSumOfSubarrays31();
        int result = arrayGreatestSumOfSubarrays31.FindGreatestSumOfSubArray(array);
        System.out.println(result);
    }
}
