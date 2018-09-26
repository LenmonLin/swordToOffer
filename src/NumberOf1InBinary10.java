/**
 * 主要思路是使用左移和位与，这两个技能点
 * 右移的条件比较复杂，可能会造成无限循环，左移比较清晰一点
 * while和if的条件判断语句是需要Boolean类型的，可以考虑加!= 0
 *
 * @author LemonLin
 * @Description :NumberOf1InBinary10
 * @date 2018/2/25-15:34
 */
public class NumberOf1InBinary10 {
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
        NumberOf1InBinary10  numberOf1InBinary10 = new NumberOf1InBinary10();
        int numberOf1 = numberOf1InBinary10.NumberOf1(9);
        System.out.println(numberOf1);
    }
}
