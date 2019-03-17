/**
 *   @author LemonLin
 *   @Description :NumberOf1InBinary10
 *   @date 2018/2/25-15:34
 * 题目描述：
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 * 思路一：先判断整数二进制表示中最右边一位是不是1。接着把输入的整数右移一位，此时原来处于从右边
 * 数起的第二位被移到最右边了，再判断是不是1，这样每次移动一位，直到整个整数变成0为止。但是如果
 * 输入一个负数，把负数右移，数字会变成0xFFFFFFFF而陷入死循环。
 *
 *思路二：既然所求的数不能右移，那么可以把所求的数最右边的数字和1(二进制为000001)作与运算；
 * 此时把1二进制中的1左移一位，在与所求的数进行与，如此循环
 *
 * 小知识点：
 * while和if的条件判断语句是需要Boolean类型的，可以考虑加!= 0
 */
public class BitManipulationNumberOf1InBinary10 {
    public int NumberOf1(int n) {
        int count = 0;
        int flag = 1;
//PS:这里的circleN用来控制循环次数，如果直接用flag控制不太好，会使while运行多次无效的循环
//        PPS:后来调试过程种，发现不能这么操作，因为如果是负数的情况下，circleN这种做法就无法通过
//只能使用flag这样的直到达到int的上限就停止循环
        int circleN = n;
//        while (circleN !=0){
        while (flag !=0){
            if ((n&flag)!=0){
                count++;
            }
            flag = flag <<1;
//            circleN = circleN/2;
        }
        return count;
    }
    //测试代码
    public static void main(String[] args) {
        BitManipulationNumberOf1InBinary10 bitManipulationNumberOf1InBinary10 = new BitManipulationNumberOf1InBinary10();
        int numberOf1 = bitManipulationNumberOf1InBinary10.NumberOf1(9);
        System.out.println(numberOf1);
    }
}
