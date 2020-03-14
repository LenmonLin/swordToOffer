import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
/**
* 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
* 示例 1:
* 输入: nums = [1,1,1,2,2,3], k = 2
* 输出: [1,2]
* 示例 2:
* 输入: nums = [1], k = 1
* 输出: [1]
* 说明：
* 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
* 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * @author LemonLin
 * @Description :HeaptopKFrequent347LeetCode
 * @date 20.2.4-21:43
 * 思路:先用一个hashmap,存储nums中数组出现的频率，然后再用最小堆存储对应频率的
 * 数字。和LeetCode215有点像，因为这里是求前K高的，高对应着最小堆。因为这里是要求
 * 频率的高低，所以也就是需要对优先队列传入一个参数。
 *  PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1,n2)->
 *                 hashMap.get(n1)-hashMap.get(n2)
 *         );
 * bug1:
 * 输入:
 * [4,1,-1,2,-1,2,3]
 * 2
 * 输出
 * [-1,1]
 * 预期结果
 * [-1,2]
 * 解决方式：
 *
 */
public class HeaptopKFrequent347LeetCode {
    public List<Integer> topKFrequent(int[] nums, int k) {
        ArrayList<Integer> result= new ArrayList<>();
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        Integer temp =0;
        for (int i=0;i<nums.length;i++){
            if (hashMap.containsKey(nums[i])){
                temp = hashMap.get(nums[i]);
                temp+=1;
                hashMap.put(nums[i],temp);
            }else {
                hashMap.put(nums[i],1);
            }
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1,n2)->
                hashMap.get(n1)-hashMap.get(n2)
        );
        for (Integer digits:hashMap.keySet()){
            if (minHeap.size()<k){
                minHeap.add(digits);
            }else {
                if (hashMap.get(minHeap.peek())<hashMap.get(digits)){
                    minHeap.poll();
                    minHeap.add(digits);
                }
            }
        }
        while (minHeap.size()>0){
            result.add(minHeap.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums={4,1,-1,2,-1,2,3};
        int k=2;
        System.out.println(new HeaptopKFrequent347LeetCode().topKFrequent(nums, k));
    }
}