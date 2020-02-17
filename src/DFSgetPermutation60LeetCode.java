import java.util.ArrayList;
import java.util.Arrays;
/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * 输入: n = 4, k = 9
 * 输出: "2314"
 * @author LemonLin
 * @Description :DFSgetPermutation60LeetCode
 * @date 20.2.17-10:50
 * 思路：自己思考是列出所有的全排列，然后取第K个。这里有有更好的解法，就是剪枝，
 * 直接计算出第K个排列。
 * 参考:https://leetcode-cn.com/problems/permutation-sequence/solution/
 * hui-su-jian-zhi-python-dai-ma-java-dai-ma-by-liwei/
 *主要理解这个K如何控制，看代码中的注释
 */
public class DFSgetPermutation60LeetCode {
    public String getPermutation(int n, int k) {
        //计算阶乘，以备后用
        int [] factorial = new int[n+1];
        //判断是否遍历过，
        boolean[] used = new boolean[n+1];
        Arrays.fill(used,false);
        //factorial ={1,1,2,6,24,120...}
        //                 0.1.2.3.4.   5
        //这个怎么用，比如n=5，那就是1,2,3,4,5个数，先选了1，那后面就是四个数，以
        //1开头的就有4！=24种可能的全排列。那么假如k=26,那么k>24,第26个排列肯定
        //不是以1开头的，就利用for循环中的continue跳过。
        factorial[0]=1;
        for(int i=1;i<=n;i++){
            factorial[i]=factorial[i-1]*i;
        }
        ArrayList<Integer> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        dfs(n,k,result,0,used,factorial);
        for (int i=0;i<result.size();i++){
            stringBuilder.append(result.get(i));
        }
        return  stringBuilder.toString();
    }
    private void dfs(int n,int k,ArrayList<Integer> result,int index,boolean[] used,
                     int[] factorial){
        //index表示dfs深度
        //这里要写n,不能写k,
        if (n==index){
            return;
        }
        //这里为啥是n-1，因为初始就选了一个了，比如n=5，那就是1,2,3,4,5个数，先
        // 选了1，那后面就是四个数，为啥是减index，可以想到最开始index是0。代进去
        // 刚刚好。
        int cnt =factorial[n-1-index];
        for (int i=1;i<=n;i++){
            if (used[i]==true){
                continue;
            }
            if (cnt<k){
                k-=cnt;
                continue;
            }
            result.add(i);
            used[i]=true;
            dfs(n,k,result,index+1,used,factorial);
        }
    }

    public static void main(String[] args) {
        int n =4;
        int k=9;
        System.out.println(new DFSgetPermutation60LeetCode().getPermutation(n, k));
    }
}
