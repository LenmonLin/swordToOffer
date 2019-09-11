import java.util.Scanner;

/**
 * 给定只含 "I"（增大）或 "D"（减小）的字符串 S ，令 N = S.length。
 * 返回 [0, 1, ..., N] 的任意排列 A 使得对于所有 i = 0, ..., N-1，都有：
 * 如果 S[i] == "I"，那么 A[i] < A[i+1]
 * 如果 S[i] == "D"，那么 A[i] > A[i+1]
 * 示例 1：
 * 输出："IDID"
 * 输出：[0,4,1,3,2]
 * 示例 2：
 * 输出："III"
 * 输出：[0,1,2,3]
 * 示例 3：
 * 输出："DDI"
 * 输出：[3,2,0,1]
 * 提示：
 * 1 <= S.length <= 1000
 * S 只包含字符 "I" 或 "D"。
 * 思路：通过三个实例可以看出，如果遇到I，就取[0,1,...,N]中最小的，如果遇到D，就取[0,1,...,N]
 * 中最大的，去完一个之后，[0,1,...,N]中就少一个。比如示例1：IDID，长度为4，所以
 * [0,1,2,3,4],遇到I，取出0，遇到D，取出4，遇到I，第一次0被取出了，最小现在是1
 * 取出1，遇到D，第二次取出的4，现在最大是3，取出3，同时根据题意接下去要取比3小的
 * @author LemonLin
 * @Description :MathdiStringMatch942LeetCode
 * @date 19.9.11-10:29
 */
public class MathdiStringMatch942LeetCode {
    public int[] diStringMatch(String S) {
        int N = S.length();
        int [] result = new int[N+1];
        int minCount =0;
        int maxCount =N;
        for (int i=0;i<N;i++){
            if (S.charAt(i)=='I'){
                result[i]=minCount;
                minCount++;
                //如果是最后一位要补一个数
                if (i==N-1){
                    result[i+1]=maxCount;
                }
            }else {
                result[i]=maxCount;
                maxCount--;
                if (i==N-1){
                    result[i+1]=minCount;
                }
            }

        }
        return result;
    }
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        String input =sc.nextLine();
        int[] result = new MathdiStringMatch942LeetCode().diStringMatch(input);
        for(int i=0;i<result.length;i++){
            System.out.print(result[i]);
        }
    }
}
