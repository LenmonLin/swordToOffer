/**
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * @author LemonLin
 * @Description :DPminDistance72LeetCode
 * @date 20.2.29-16:31
 * 思路：参考：https://leetcode-cn.com/problems/edit-distance/solution/
 * bian-ji-ju-chi-mian-shi-ti-xiang-jie-by-labuladong/
 * 1、解决两个字符串的动态规划问题，一般都是用两个指针 i,j 分别指向两个字符串的最
 * 后，然后一步步往前走，缩小问题的规模。
 * 2、设置dp table,一般题目所求什么就设置什么。(除了300题，比较特殊)
 * 状态设置dp[i][j]为word1的长度为[0,i]和word2的长度为[0,j]的最小编辑距离。
 * 那么可以得出一张dp table
 *       h o r  s  e
 *       0,1,i,...
 * r  0
 * o  1
 * s  j
 *    ...
 * 3、用数学归纳法想问题，如果得到了dp[i-1][j-1]怎么得到dp[i][j]
 * 状态转移方程就是i,j从前往后遍历：
 * if w1[i-1] == w2[j-1]:
 *      dp[i][j] = dp[i-1][j-1]  //因为什么都不用做，所以是都往前移动
 * else:
 *     选min(插，删，换)
 *3、边界条件：
 *   如果是i=0,j=0,那么不用任何操作，所以dp[0][0]=0;
 *
 *   关于下标的理解：参考https://leetcode-cn.com/problems/edit-distance/solution/zi-di-xiang-shang-he-zi-ding-xiang-xia-by-powcai-3/
 *   讲一下我自己对状态转移方程的理解,麻烦大家看看我说得对不对有没有道理:
 * (一)、当word1[i]==word2[j]时,由于遍历到了i和j,说明word1的0~i-1和word2的0~j-1的匹配结果已经生成,
 * 由于当前两个字符相同,因此无需做任何操作,dp[i][j]=dp[i-1][j-1]
 * (二)、当word1[i]!=word2[j]时,可以进行的操作有3个:
 *       ① 替换操作:可能word1的0~i-1位置与word2的0~j-1位置的字符都相同,
 *            只是当前位置的字符不匹配,进行替换操作后两者变得相同,
 *            所以此时dp[i][j]=dp[i-1][j-1]+1(这个加1代表执行替换操作)
 *       ②删除操作:若此时word1的0~i-1位置与word2的0~j位置已经匹配了,
 *          此时多出了word1的i位置字符,应把它删除掉,才能使此时word1的0~i(这个i是执行了删除操作后新的i)
 *          和word2的0~j位置匹配,因此此时dp[i][j]=dp[i-1][j]+1(这个加1代表执行删除操作)
 *       ③插入操作:若此时word1的0~i位置只是和word2的0~j-1位置匹配,
 *           此时只需要在原来的i位置后面插入一个和word2的j位置相同的字符使得
 *           此时的word1的0~i(这个i是执行了插入操作后新的i)和word2的0~j匹配得上,
 *           所以此时dp[i][j]=dp[i][j-1]+1(这个加1代表执行插入操作)
 *       ④由于题目所要求的是要最少的操作数:所以当word1[i] != word2[j] 时,
 *           需要在这三个操作中选取一个最小的值赋格当前的dp[i][j]
 * (三)总结:状态方程为:
 * if(word1[i] == word2[j]):
 *       dp[i][j] = dp[i-1][j-1]
 * else:
 *        min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])+1
 * PS:大佬的代码中word1.charAt(i-1)==word2.charAt(j-1)的原因是:
 *      初始化DP Table时dp[i][0]和dp[0][j]已经填写完成,所以接下来填表需要从1开始,
 *      但是字符的比较需要从0开始,因此才这样子写
 */
public class DPminDistance72LeetCode {
    public int minDistance(String word1, String word2) {
        int length1= word1.length();
        int length2 = word2.length();
        int [][] dp = new int[length1+1][length2+1];
        //注意这里边界从1开始，
        for (int i=1;i<=length1;i++){
            dp[i][0]=i;
        }
        for (int j=1;j<=length2;j++){
            dp[0][j]=j;
        }
        //同时这里不考虑i=0和j=0的情况，因为没有意义。
        for (int i =1;i<=length1;i++){
            for (int j =1;j<=length2;j++){
                //注意这里比较的是i-1和j-1
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j]+1,
                            Math.min(dp[i][j-1]+1,dp[i-1][j-1]+1));
                }
            }
        }
        return dp[length1][length2];
    }

    public static void main(String[] args) {
        String word1="horse";
        String word2 ="ros";
        System.out.println(new DPminDistance72LeetCode().minDistance(word1, word2));
    }
}
