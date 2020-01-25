import java.util.ArrayList;
import java.util.List;
/**
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 示例 2:
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 * @author LemonLin
 * @Description :BackTrackinggrayCode89LeetCode
 * @date 20.1.21-12:06
 * 思路：先把输入的数字，转换为多个0，比如输入3，转换为3位000，然后开始回溯遍历
 * 000,001,010,011...长度作为递归出口。再把遍历的结果转换为十进制存入最后结果。
 * 难点一：000,001,010，这个序列怎么生成，和全排列不同，全排列是给定了数值，然后
 * 进行排列组合，这个是只给定了位数。而且还是二进制的。
 * bug：返回重复的数据：00112233
 * 调试过程：把
 *     private void backTracking(int digit, int n,ArrayList temp){
 *         if (temp.size()>n){
 *             return;
 *         }
 *         if (temp.size()==n){
 *             result.add(twoTransferTen(temp));
 *         }
 *         temp.add(digit);
 *         backTracking(0,n,temp);
 *         backTracking(1,n,temp);
 *         temp.remove(temp.size()-1);
 *     }
 * 调成：
 *     private void backTracking(int digit, int n,ArrayList temp){
 *         if (temp.size()>n){
 *             return;
 *         }
 *         temp.add(digit);
 *         if (temp.size()==n){
 *             result.add(twoTransferTen(temp));
 *         }
 *         backTracking(0,n,temp);
 *         backTracking(1,n,temp);
 *         temp.remove(temp.size()-1);
 *     }
 * 出现了新问题。返回的是0123，这个是不对的，题目要求应该是0132，这个是格雷码的
 * 要求，每次只能变一个数字，总体方向上出现了问题。
 * 最终解法，参考https://leetcode-cn.com/problems/gray-code/solution/
 * xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--12/
 * 这里要注意几点，因为输出是十进制的，所以不用考虑先把输入十进制转二进制，再把
 * 二进制转十进制。这里需要用到一个符号<<,也就是1<<2(1左移2位，变成二进制100)
 * 1<<2+1<<2=8;因为加号还是十进制。
 * 所以本题还是没有用到回溯的方法解决。有点像动态规划。
 */
public class DPgrayCode89LeetCode {
    /*错误解法留存
    private ArrayList result = new ArrayList();
    public List<Integer> grayCode(int n) {
        backTracking(0,n,new ArrayList());
        backTracking(1,n,new ArrayList());
        return result;
    }
    private void backTracking(int digit, int n,ArrayList temp){
        if (temp.size()>n){
            return;
        }
        //这个顺序特别重要。别瞎放。不然得不到争取结果。
        temp.add(digit);
        if (temp.size()==n){
            result.add(twoTransferTen(temp));
        }
        backTracking(0,n,temp);
        backTracking(1,n,temp);
        temp.remove(temp.size()-1);
    }
    //二进制转十进制
    private int  twoTransferTen(ArrayList<Integer> temp){
        int tenDigit =0;
        for (int i=0;i<=temp.size()-1;i++){
            if (i==temp.size()-1){
                tenDigit+=temp.get(i);
            }
            else {
                if (temp.get(i)==1){
                    tenDigit+=Math.pow(2,temp.size()-1-i);
                }
            }
        }
        return tenDigit;
    }
    */
    public List<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList();
        //初始添加当n=0时，结果result=0;
        result.add(0);
        for (int i=0;i<n;i++){
            //需要加上的数
            int add=1<<i;
            //倒序加上需要加的数，得到结果
            for (int j=result.size()-1;j>=0;j--){
                result.add(result.get(j)+add);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n=3;
        List<Integer> integers = new DPgrayCode89LeetCode().grayCode(n);
        for(int i=0;i<integers.size();i++){
            System.out.print(integers.get(i));
        }
    }
}
