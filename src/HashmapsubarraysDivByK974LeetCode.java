import java.util.HashMap;

/**
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 * 示例：
 * 输入：A = [4,5,0,-2,-3,1], K = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 K = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * 提示：
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 * @author LemonLin
 * @Description :HashmapsubarraysDivByK974LeetCode
 * @date 20.4.16-19:54
 * 思路：和LeetCode523很像，利用前缀和
 * bug1:
 * 输入：
 * [-1,2,9]
 * 2
 * 输出：
 * 1
 * 预期：
 * 2
 * 1、关于负数取余，可以参考：
 * https://ceeji.net/blog/mod-in-real/comment-page-1/#comments
 * 这里的处理方式都把它转换为正余数：
 * int key = (sum % K + K) % K;本来应该是sum % K + K，为啥还要%K，是为了把
 * 结果限定在0——K-1之间
 * 因为用了 nums[i]+nums[i+1]+....+nums[j-1] = {sum(后面的)-sum(前面的)}%k=0
 *  推出 sum(后面的)%k=sum(前面的)%k推导，所以只需要两个前缀和除以K的余数相等
 *  就满足题目要求，比如k=5,sum(后面的) = 9,sum(前面) = 4，那么
 *  {sum(后面的)-sum(前面的)}%5=0，sum(后面的) = 9%5=4，sum(前面) = 4%5=4
 *  满足条件。但是有一个特殊情况就是比如k=5,sum(后面的) = 9,sum(前面) = -6，
 *  {sum(后面的)-sum(前面的)}%5=0（9--6=15,15%5=0）按理说应该满足条件的，但是
 *  如果这样计算sum(后面的) = 9%5=4，sum(前面) = -6%5=-1，4!=-1,就不满足条件。
 *  但是实际情况是满足条件的。所以就是说负数sum要处理一下。
 * 2、关于暴力算法，应该设置sum[i]=nums[0]+nums[1]+...+nums[i-1]
 * 这么做是为了相减的时候处理方便：
 * sum[j]-sum[i]=nums[i]+nums[i+1]+....+nums[j-1];
 * 所以sum[0]空出来，还要求sum[A.length].
 * 3、nums[i]+nums[i+1]+....+nums[j-1] = sum(后面的)-sum(前面的).
 * 关于本来是用hashmap的思路：
 * nums[i]+nums[i+1]+....+nums[j-1] = {sum(后面的)-sum(前面的)}%k=0
 * 推出 sum(后面的)%k=sum(前面的)%k
 * 4、为啥只能用hashMap，不能用HashSet不记录次数不行吗：
 * 因为前面有可能有多个满足值等于sum(前面的)%k的结果，所以需要记录次数，而如果
 * 用set就只能记录一次，不满足需求。
 * 5、为啥开头要有hashmap.put(0,1)，关于put(0,1)因为假如K=7，sum(前面的) =7
 * 那么sum%K==0，按理说应该是满足条件的，但是如果没有存先前存(0,1),就无法计数。
 * 也就是这个式子 nums[i]+nums[i+1]+....+nums[j-1] = {sum(后面的)-sum(前面的)}%k=0
 * 推出 sum(后面的)%k=sum(前面的)%k，如果sum(前面的)%k!=0,那么要前后都出现
 * 一次不等于0的，才满足计数结果条件，如果sum(前面的)%k==0，那么第一次出现就应该
 * 满足计数结果条件。
 * 关于为啥要有hashmap.put(0,1)，可以参考LeetCode560
 */
public class HashmapsubarraysDivByK974LeetCode {
    //用上hashMap使时间复杂度下降为O(n)
    public int subarraysDivByK(int[] A, int K) {
        int result =0;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        int sum =0;
        hashMap.put(0,1);
        for (int i =1;i<=A.length;i++){
            sum +=A[i-1];
            int key = (sum % K + K) % K;
            int value = sum%K;
            if (hashMap.containsKey(key)){
                result+=hashMap.get(key);
                hashMap.put(key,hashMap.get(key)+1);
            }else {
                hashMap.put(key,1);
            }
        }
        return  result;
    }

    //O(n2)超时了
    public int subarraysDivByK1(int[] A, int K) {
        int result =0;
        //先记录前序和
        int [] sum =new int [A.length];
        //这里下标的选择从1开始，是为了方便处理。
        sum[0]=0;
        for (int i=1;i<A.length;i++){
            sum[i] =sum[i-1]+A[i-1];
        }

        //开始计算
        for(int start=0;start<A.length;start++){
            //注意这里用的是end<=,有个等号。
            for (int end=start;end<=A.length;end++){

                int total = sum[end]-sum[start];
                //k可能等于0，要排除一下
                if ((K!=0&&total%K==0)){
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [] A  = {-1,2,9};
        int K = 2;
        System.out.println(new HashmapsubarraysDivByK974LeetCode().subarraysDivByK(A, K));
    }
}
