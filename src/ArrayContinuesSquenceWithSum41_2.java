import java.util.ArrayList;
/*
 * @author LemonLin
 * @Description :ContinuesSquenceWithSum41_2
 * @date 2018/5/15-16:44
 * 题目描述
 *     小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 *    但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 *    没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。、
 *    现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 *    输出描述:
 *       输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 * 基本思路：
 * 1、用两个指针分别记录头尾，然后如果两者之和小于sum,则后移尾指针，增加求和的数的数量，如果
 * 两者之和大于sum，则后移头指针，减少求和的数的数量。
 * 2、因为这个序列至少要有两个数字，则序列中至少有一个数是小于sum/2,否则都大于sum/2，则不可能由两个
 * 数组成，两个都大于sum/2的和一定大于sum,所以我们一直增加small到(1+sum)/2为止;
*/
public class ArrayContinuesSquenceWithSum41_2 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        if (sum<3)
            return new ArrayList<ArrayList<Integer>>();
        int small = 1;
        int big =2;
        int middle = (1+sum)/2;
        int curSum = small+big;
        ArrayList<ArrayList<Integer>> arrayList  = new ArrayList<ArrayList<Integer>>();
        while (small<middle){
            if (curSum == sum){
                AddContinuousSequence(small,big,arrayList);
            }//这里注意big和small都是向后移动，当前的curSum比给定的sum小的时候
            //big要后移，curSum包含的数变多，curSum增大
            while (curSum >sum && small <middle){
                curSum -=small;
                small++;
                if (curSum == sum){
                    AddContinuousSequence(small,big,arrayList);
                }
            }
            //当前的curSum比给定的sum大的时候，small往后移，那样curSum包含的数变少，curSum减小
            big++;
            curSum+=big;
        }
        return arrayList;
    }
    //添加到对应的二维数组中
    public void AddContinuousSequence(int small,int big,
                                      ArrayList<ArrayList<Integer>> arrayList){
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        for (int j=small ;j<=big; j++){
            arrayList1.add(j);
        }
        arrayList.add(arrayList1);
    }
    public static void main(String[] args) {
        ArrayContinuesSquenceWithSum41_2 arrayContinuesSquenceWithSum41_2 = new ArrayContinuesSquenceWithSum41_2();
        ArrayList<ArrayList<Integer>> arrayLists = arrayContinuesSquenceWithSum41_2.FindContinuousSequence(15);
        System.out.println("输出数组"+arrayLists);
    }
}
