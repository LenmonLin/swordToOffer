import java.util.ArrayList;
import java.util.HashMap;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素
 * 到内存中，你该怎么办？
 * @author LemonLin
 * @Description :Arrayintersect350LeetCode
 * @date 20.6.15-15:34
 * 思路：遍历nums1,用hashMap存储，key为对应的数字，value为对应数字出现的次数。
 * 遍历nums2,如果hashMap中存在对应的数字，同时value次数大于0，就算公共集合。
 * 进阶参考：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/solution/jin-jie-san-wen-by-user5707f/
 * 排序指针法和hashmap法
 */
public class Arrayintersect350LeetCode {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> hashMap = new  HashMap<>();
        for (int i:nums1){
            hashMap.put(i,hashMap.getOrDefault(i,0)+1);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for(int i:nums2){
            if (hashMap.containsKey(i)&&hashMap.get(i)>0){
                result.add(i);
                hashMap.put(i,hashMap.get(i)-1);
            }
        }
        int [] output = new int[result.size()];
        for (int i=0;i<result.size();i++){
            output[i]= result.get(i);
        }
        return output;
    }

    public static void main(String[] args) {

    }
}
