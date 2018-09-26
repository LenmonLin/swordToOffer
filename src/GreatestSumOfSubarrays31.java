
/**
 * @author LemonLin
 * @Description :GreatestSumOfSubarrays31
 * @date 2018/4/12-20:51
 *
 * 基本思想用在线法处理，设置两个变量来记录，currentSum记录在线过程中是否累加和大于0，
 * 如果大于0就继续累加，如果小于0就舍弃；
 * GreatSum用于记录最大的值,过程中和currentSum比较
 *
 * 要点二：
 * MIN_VALUE = 0x80000000 和 MAX_VALUE = 0x7fffffff 就是补码表示的Integer的最小值(-2^31)和
 * 最大值(2^31-1)。至于为什么采用补码表示，简单的说就是方便运算，
 * 详细可自行Google一下或找本基础教材翻一下。至于Integer的最大值最小值为什么是这两个数，
 * 这是因为Java语言规范规定int型为4字节，不管是32/64位机器，这就是其所宣称的跨平台的基础部分。
 */
public class GreatestSumOfSubarrays31 {

    public int FindGreatestSumOfSubArray(int[] array) {
        if(array.length==0||array==null){
            return 0;
        }
        int currentSum=0;

        //注意这里GreatSum不能设置为0，应该设置成一个极小的数，否则如果都是数组都是负数的话，
        //就返回0，这个是有问题的
        int GreatSum=0x80000000;
        for (int i=0;i<array.length;++i){
            if (currentSum<0){
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
        GreatestSumOfSubarrays31 greatestSumOfSubarrays31 = new GreatestSumOfSubarrays31();
        int result = greatestSumOfSubarrays31.FindGreatestSumOfSubArray(array);
        System.out.println(result);
    }
}
