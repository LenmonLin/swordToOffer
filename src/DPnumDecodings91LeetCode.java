/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * 示例 1:
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * @author LemonLin
 * @Description :DPnumDecodings91LeetCode
 * @date 20.1.26-22:24
 * 思路：参考https://www.bilibili.com/video/av42112212?from=search&seid=
 * 1866227033913330778这一部分只需要看递归部分的思路即可：1分20秒到6分06秒。
 * 参考2：https://leetcode-cn.com/problems/decode-ways/solution/
 * xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-3/关于代码部分。
 * 关于本题的难点易错点。
 * 1、这里说一下本题的递归也是树模型，为什么DP可以用一重循环而不是二重循环就解决问题，
 * 因为解题过程中用到的是s[i]与s[i+1]进行比较。这种同时比较两个数，两个数的下标还不一样。
 * 是不是很像二重循环：
 *         for (int i=2;i<=n;i++){
 *             for (int j=1;j<i;j++){
 *                 dp[i] = max3(dp[i],j*(i-j),j*dp[i-j]);
 *  中的同时使用i和j的方式。上面二重循环只是无意举的一个例子和本题没有关系，只是为
 *  了说明二重循环的问题，所以正是因为本题的一重循环中使用了s[i]与s[i+1]，同时有两
 *  个下标变化，代替了二重循环的作用。所以本题可以用一重循环就解决问题。
 *  2、再讲一下，本题为什么要用从后往前遍历的方式。这点可以看一下上述思路的B站的
 *  参考链接。可以发现递归的模式是，
 *                              要算还未解码的s(226)有几种解码方式
 *                                       /               \
 *                    2解码，未解码s(26)        22解码，未解码s(6)
 *                          /               \                   \
 *       22解码，未解码s(6)     226解码          226解码
 *                     /
 *                  226解码
 *可以看出，输入的是未解码s(226),递归往下一层一层未解码的能和输入对应组建减少s(26)、
 * s(6)，而动态规划是从下往上的计算过程，所以自然而然的要从后往前遍历，所以自然而然
 * 的设置 dp 数组， dp [ i ] 代表字符串 s [ i, s.len-1 ]，也就是 s从 i 开始到结尾的字符串。
 * 3、关于状态转移方程，举个示例2的例子：
 * 从后往前遍历，设置用一个 dp 数组， dp [ i ] 代表字符串 s [ i, s.len-1 ]，也就是 s
 * 从 i 开始到结尾的字符串。那么
 * 当i=2,时，s[i]=6,substring ="6";dp[2]=1;
 * 当i=1,时，s[i]=2,substring ="26";dp[1]=2;
 *      这个2是怎么得来，是2,6一种解码方式；26一种解码方式。所以"26"总体加起来有两
 *      种解码方式。这里容易想错的一种计算方法：6，可以解码一次，2可以解码一次。26
 *      又可以解码一次，所以最后结果为"26"可以解码三次。这里犯的错误是这种计算方式
 *      没有大局观，题目问的是给定的全部字符都解码成功了算一种方式。而不是每次一个
 *      字符可以解码算一次。也就是题目求的是种数，不是次数。注意区别。
 * 当i=0,时，s[i]=2,substring ="226";dp[0]=3;
 *    可以发现dp[0]是通过：dp[1]和dp[2]推出的。s[i,i+1]表示字符下标i和i+1组成的两个字符。
 *    这里的前提是dp[i+1],dp[i+2]都算出来的情况下：求dp[i]:
 *    s[i]能不能解码；s[i,i+1]能不能解码，两两排列组合，四种情况。
 *    1、对s[i]，s[i+1]进行判断，如果s[i,i+1]可以解码，但是s[i]不能解码那么dp[i]=dp[i+2];
 *   不存在这种情况，因为如果s[i,i+1]可以解码，说明，s[i]只能是1或者2，所以一定能解码。排除。
 *    2、如果s[i,i+1]不能解码，但是s[i]能解码，那么dp[i] = dp[i+1];
 *    3、如果s[i,i+1]可以解码，同时s[i]能解码，那么dp[i] = dp[i+1]+dp[i+2];
 *     4、如果s[i,i+1]不能解码，同时s[i] 也不能解码，那么dp[i]=0。说明s(i,len-1)字符串不
 *     能顺利解码，总的解码种类数为0，比如0123;虽然单独来说1,2之类的能解码，但是
 *     其中有0，不能解码，那么说明总体字符串0123不能全部都解码，所以种类数为0.
 *     所以就剩下三种情况：2,3,4.
 *bug1:
 * 输入：
 * "10"
 * 输出：
 * 2
 * 预期：
 * 1
 * 最后的做法是根据：https://leetcode-cn.com/problems/decode-ways/solution/
 * xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-3/关于代码部分。
 * bug2:
 * 输入:
 * "27"
 * 输出
 * 0
 * 预期结果
 * 1
 */
public class DPnumDecodings91LeetCode {
    public int numDecodings(String s) {
//        if (s.isEmpty()||s.charAt(s.length()-1)==0)return 0;
//        if (s.length()==1)return 1;
        int [] dp = new int[s.length()+1];
        //这个添加只有最开始的时候dp[i] =dp[i+2] + dp[i+1];会用到，也就是如果s[i,i+1]
        // 可以解码，同时s[i]能解码的情况；举例23，这个时候dp[s.length() - 1]=dp[1]=1;
        //dp[0]=dp[1]+dp[2]应该等于2，所以反推 dp[s.length()]=1；或者如果不这样处理，
        // 直接如果i=s.length()-2，同时如果s[i,i+1]可以解码，同时s[i]能解码的情况；dp[i]=2;即可
        dp[s.length()]=1;
        //最后一个数字不等于 0 就初始化为 1
        if (s.charAt(s.length() - 1) != '0') {
            dp[s.length() - 1] = 1;
        }else {
            dp[s.length() - 1] = 0;
        }
        for (int i=s.length()-2;i>=0;i--){
            char ci = s.charAt(i);
            char ci1 = s.charAt(i+1);
            //4、如果s[i,i+1]不能解码，同时s[i] 也不能解码，那么dp[i]=0。
            if (s.charAt(i) == '0'&&!isVaild(ci,ci1)) {
                dp[i] =0;
            // 3、如果s[i,i+1]可以解码，同时s[i]能解码，那么dp[i] = dp[i+1]+dp[i+2];
                // 因为前面已经判断了s.charAt(i) == '0'，所以到这个else if,肯定s.charAt(i)
                // 可以解码，不用再写出来。
            }else if (isVaild(ci,ci1)){
                dp[i] =dp[i+2] + dp[i+1];
            //  2、如果s[i,i+1]不能解码，但是s[i]能解码，那么dp[i] = dp[i+1];
            }else {
                dp[i]=dp[i+1];
            }
        }
        return dp[0];
    }
//    private boolean isValid(char c){
//        if (c!='0'){
//            return true;
//        }else {
//            return false;
//        }
//    }
    private boolean isVaild(char a,char b){
        int temp = (a-'0')*10+(b-'0');
        if (temp>=10&&temp<=26){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new DPnumDecodings91LeetCode().numDecodings("27"));
    }
}
