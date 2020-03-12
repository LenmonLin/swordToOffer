import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * 示例 1:
 * 输入:
 * "tree"
 * 输出:
 * "eert"
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 * 输入:
 * "cccaaa"
 * 输出:
 * "cccaaa"
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 * 输入:
 * "Aabb"
 * 输出:
 * "bbAa"
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 * @author LemonLin
 * @Description :HeapfrequencySort451LeetCode
 * @date 20.2.4-21:45
 * 把频率通过hashmap统计之后传入优先队列，因为是需要降序排列，也就是先输出大的值，
 * 用大顶堆一个一个的输出。把排序的任务交给大顶堆。（这里需要区别降序排列和求前K大的
 * 数的区别，看着很容易混淆，降序排列用大顶堆，前K大的用最小顶堆）
 */
public class HeapfrequencySort451LeetCode {
    public String frequencySort(String s) {
        HashMap<Character,Integer> hashMap = new HashMap();
        for (char c:s.toCharArray()){
            hashMap.put(c,hashMap.getOrDefault(c,0)+1);
        }
        PriorityQueue maxHeap = new PriorityQueue((n1,n2)->
                hashMap.get(n2)-hashMap.get(n1));
        for (char c:hashMap.keySet()){
            maxHeap.add(c);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (maxHeap.size()>0){
            Character temp = (Character) maxHeap.poll();
            int count = hashMap.get(temp);
            while (count>0){
                stringBuilder.append(temp);
                count--;
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s ="Aabb";
        System.out.println(new HeapfrequencySort451LeetCode().frequencySort(s));
    }
}
