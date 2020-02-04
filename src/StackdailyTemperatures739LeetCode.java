/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才
 * 会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出
 * 应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在
 *  [30, 100] 范围内的整数。
 * @author LemonLin
 * @Description :StackdailyTemperatures739LeetCode
 * @date 20.2.4-17:01
 * 思路：自我感觉比较简单啊，直接遍历就完事了。时间复杂度高，需要改进。。。
 * bug1：
 * 输入:
 * [55,38,53,81,61,93,97,32,43,78]
 * 输出
 * [3,1,1,2,1,1,0,1,0,0]
 * 预期结果
 * [3,1,1,2,1,1,0,1,1,0]
 *
 */
public class StackdailyTemperatures739LeetCode {
    //执行结果：通过.显示详情
    //执行用时 :518 ms, 在所有 Java 提交中击败了5.02%的用户
    //内存消耗 :42.7 MB, 在所有 Java 提交中击败了42.22%的用户
    //需要改进。。。
    public int[] dailyTemperatures(int[] T) {
        int [] result = new int[T.length];
        int count=0;
        for (int i=0;i<T.length;i++){
            for (int j=i+1;j<T.length;j++){
                if (j<T.length&&T[i]>=T[j]){
                    count++;
                }else {
                    count++;
                    break;
                }
            }
            //解决bug1。
            if (T[i]>=T[i+count]){
                result[i]=0;
            }else {
                result[i]=count;
            }
            count=0;
        }
        return result;
    }

    public static void main(String[] args) {
        int [] T={55,38,53,81,61,93,97,32,43,78} ;
        int[] result = new StackdailyTemperatures739LeetCode().
                dailyTemperatures(T);
        for (int i=0;i<result.length;i++){
            System.out.print(result[i]);
        }
    }
}
