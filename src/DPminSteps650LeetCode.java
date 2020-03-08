/**
* 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
* Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
* Paste (粘贴) : 你可以粘贴你上一次复制的字符。
* 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能
 * 够打印出 n 个 'A' 的最少操作次数。
* 示例 1:
* 输入: 3
* 输出: 3
* 解释:
* 最初, 我们只有一个字符 'A'。
* 第 1 步, 我们使用 Copy All 操作。
* 第 2 步, 我们使用 Paste 操作来获得 'AA'。
* 第 3 步, 我们使用 Paste 操作来获得 'AAA'。
* 说明:
* n 的取值范围是 [1, 1000] 。
 * @author LemonLin
 * @Description :DPminSteps650LeetCode
 * @date 20.2.22-22:19
 * 思路：参考https://blog.csdn.net/qq_41855420/article/details/89370779
 * 本题的难点是这题不想常规的DP，很不常规，数学归纳法和递归法都很难想出来，有其特
 * 殊性：由于只能复制全部，或者粘贴剪切板，即如果num可以被复制出，只有两种可能，
 * 它是有一个一个单独的’A’组合而成，二是它的某个因子粘贴多次而得到。比如21，可以
 * 由7粘贴2次（之前就有一次7），或者由3粘贴6次（之前就有一个3），而3需要3次才
 * 能得到，7需要7次才能得到，故21至少需要9 + 1次（当你得到7或者3时，你需要进行
 * 一次复制才能进行粘贴）。
 */
public class DPminSteps650LeetCode {
    public int minSteps(int n) {
        int [] dp = new int[n+1];
        dp[1]=0;
        for (int i =2;i<=n;i++){
            //最大的次数是用一个一个单独的"A"组成
            dp[i]=i;
            //这里注意，j是从后往前遍历，优先找最大的因数，找到最大的因数，就可以退出，
            // 因为大的因数的次数一定比小的因数的次数少。
            for (int j =i-1;j>=2;j--){
                if (i%j==0){
                    //可以直接覆盖原始保存的i,一旦找到直接break退出即可
                    //这里的1表示一次复制。
                    dp[i]=dp[j]+(i-j)/j+1;
                    break;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n=6;
        System.out.println(new DPminSteps650LeetCode().minSteps(n));
    }
}
