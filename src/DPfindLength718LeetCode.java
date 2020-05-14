/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 示例 1:
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 * @author LemonLin
 * @Description :ArrayfindLength718LeetCode
 * @date 20.5.14-15:34
 * 思路1：暴力解法参考：自己写出来有bug，跳过
 * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/solution/wu-li-jie-fa-by-stg-2/
 * 思路2：用动态规划：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/solution/dong-tai-gui-hua-by-hai-gen/
 * bug1:
 * 输入:
 * [5,14,53,80,48]
 * [50,47,3,80,83]
 * 输出
 * 0
 * 预期结果
 * 1
 * bug2:
 * 输入:
 * [1,0,0,0,1]
 * [1,0,0,1,1]
 * 输出
 * 4
 * 预期结果
 * 3
 */
public class DPfindLength718LeetCode {

    //动态规划
    public int findLength2(int[] A, int[] B) {
        //设置dp[i][j]为A[0,..i-1]到B[0,...j-1]的最长公共子数组，为什么是i-1和j-1是因为dp
        // 初始化是把0用掉了，所以要后移一位。
        int [][] dp = new int[A.length+1][B.length+1];
        int result =0;
        for (int i =0;i<=A.length;i++){
            for (int j=0;j<=B.length;j++){
                if (i==0||j==0){
                    dp[i][j]=0;
                    continue;
                }
                //这里因为初始化是i=0和j=0用掉了，所以只能用i-1和j-1比较，
                if (A[i-1]==B[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    dp[i][j] =0;
                }
                if (dp[i][j]>result){
                    result = dp[i][j];
                }
            }
        }
        return result;
    }

    //暴力解法，有bug,跳过。
    public int findLength(int[] A, int[] B) {
        int result =0;
        for (int i=B.length-1;i>=0;i--){
            int tempLength = compare(A,0,B,i);
            if (tempLength>result){
                result = tempLength;
            }
        }
        for (int i=0;i<A.length;i++){
            int tempLength = compare(A,i,B,0);
            if (tempLength>result){
                result = tempLength;
            }
        }
        return result;
    }
    //计算传入的两个数组的最长公共子数组。
    private int compare(int [] A,int aStart, int [] B,int bStart){
        int commonLength = 0;
        int tempLength =0;
        int k = aStart;
        while (k<A.length){
            if (bStart<B.length&&A[k]==B[bStart]){
                k++;
                bStart++;
                tempLength++;
            }else if (bStart<B.length&&A[k]!=B[bStart]){
                k++;
                tempLength =0;
            }else if (bStart>=B.length){
                break;
            }
            if (tempLength>commonLength){
                commonLength = tempLength;
            }
        }
        return commonLength;
    }

    public static void main(String[] args) {
//        int[] A ={1,0,0,0,1};
////        int[] B ={1,0,0,1,1};
        int[] A ={5,14,53,80,48};
        int[] B ={50,47,3,80,83};
        System.out.println(new DPfindLength718LeetCode().findLength2(A, B));
    }
}
