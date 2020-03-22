import java.util.Arrays;

/**
 * 给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
 * 返回使 A 中的每个值都是唯一的最少操作次数。
 * 示例 1:
 * 输入：[1,2,2]
 * 输出：1
 * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
 * 示例 2:
 * 输入：[3,2,1,2,1,7]
 * 输出：6
 * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
 * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
 * 提示：
 * 0 <= A.length <= 40000
 * 0 <= A[i] < 40000
 * @author LemonLin
 * @Description :ArrayminIncrementForUnique945LeetCode
 * @date 20.3.22-19:46
 * 思路：https://leetcode-cn.com/problems/minimum-increment-to-make-array
 * -unique/solution/ji-shu-onxian-xing-tan-ce-fa-onpai-xu-onlogn-yi-ya/
 * 把数组进行排序处理：然后如果前面一个元素A[i-1]大于等于A[i],就要让A[i]等于A[i-1]+1
 * 同时记录A[i]变更的数目，统计返回为最后的结果。为什么是A[i-1]大于等于A[i]，比如
 * 排序后有：1,1,2,2,3,7。第二个1变化之后为1,2,2,2,3,7。继续把2变化之后为：
 * 1,2,3,2,3,7这个是就造成了A[i]<A[i-1]的情况。
 */
public class ArrayminIncrementForUnique945LeetCode {
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        //因为A[0]为第一个数，肯定是单独的
        int move = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] >= A[i]) {
                int temp = A[i];
                A[i] = A[i - 1] + 1;
                move += A[i] - temp;
            }
        }
        return move;
    }

    public static void main(String[] args) {
        int [] A = {3,2,1,2,1,7};
        System.out.println(new ArrayminIncrementForUnique945LeetCode().
                minIncrementForUnique(A));
    }
}
