import java.util.ArrayList;
import java.util.HashMap;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 * @author LemonLin
 * @Description :HashMapintersection349LeetCode
 * @date 20.5.9-22:56
 * 简单直白，可以跳过
 */
public class HashMapintersection349LeetCode {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i=0;i<nums1.length;i++){
            if (hashMap.containsKey(nums1[i])){
                continue;
            }
            hashMap.put(nums1[i],1);
        }
        for (int i=0;i<nums2.length;i++){
           if (hashMap.containsKey(nums2[i])&&hashMap.get(nums2[i])==1){
               result.add(nums2[i]);
               hashMap.put(nums2[i],0);
           }
        }
        int [] output = new int[result.size()];
        for (int i=0;i<result.size();i++){
            output[i]=result.get(i);
        }
        return output;
    }
}
