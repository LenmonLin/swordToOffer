import java.util.HashMap;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得
 *  A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整
 * 数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * 例如:
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 输出:
 * 2
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * @author LemonLin
 * @Description :HashMapfourSumCount454LeetCode
 * @date 20.4.10-22:31
 * 思路：https://leetcode-cn.com/problems/4sum-ii/solution/
 * chao-ji-rong-yi-li-jie-de-fang-fa-si-shu-xiang-jia/
 * 一.采用分为两组，HashMap存一组，另一组和HashMap进行比对。
 * 二.这样的话情况就可以分为三种：
 * 1.HashMap存一个数组，如A。然后计算三个数组之和，如BCD。时间复杂度为：
 * O(n)+O(n^3),得到O(n^3).
 * 2.HashMap存三个数组之和，如ABC。然后计算一个数组，如D。时间复杂度为：
 * O(n^3)+O(n),得到O(n^3).
 * 3.HashMap存两个数组之和，如AB。然后计算两个数组之和，如CD。时间复杂度为：
 * O(n^2)+O(n^2),得到O(n^2).
 * 三.根据第二点我们可以得出要存两个数组算两个数组。
 * 四.我们以存AB两数组之和为例。首先求出A和B任意两数之和sumAB，以sumAB为key，
 * sumAB出现的次数为value，存入hashmap中。
 * 然后计算C和D中任意两数之和的相反数sumCD，在hashmap中查找是否存在key为sumCD。
 * 算法时间复杂度为O(n2)。
 */
public class HashMapfourSumCount454LeetCode {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int result =0;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        int sum=0;
        for (int i=0;i<A.length;i++){
            for (int j =0;j<B.length;j++){
                sum = A[i]+B[j];
                hashMap.put(sum,hashMap.getOrDefault(sum,0)+1);
            }
        }
        for ( int i=0;i<C.length;i++){
            for (int j=0;j<D.length;j++){
                sum = C[i]+D[j];
                sum = -sum;
                if (hashMap.containsKey(sum)){
                    result +=hashMap.get(sum);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int []A ={1, 2};
        int []B ={-2,-1};
        int []C ={-1, 2};
        int []D ={ 0, 2};
        System.out.println(new HashMapfourSumCount454LeetCode().fourSumCount(A, B, C, D));
    }
}
