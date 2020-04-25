import java.util.*;

/**
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * 示例 1：
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 输出: ["i", "love"]
 * 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 *     注意，按字母顺序 "i" 在 "love" 之前。
 * 示例 2：
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
 *     出现次数依次为 4, 3, 2 和 1 次。
 * 注意：
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
 * 输入的单词均由小写字母组成。
 * 扩展练习：
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 * @author LemonLin
 * @Description :HeaptopKFrequent692LeetCode
 * @date 20.4.25-20:27
 * 思路：和LeetCode347很像，但是怎么解决优先队列比较HashMap中的value的值的大小。
 * 这个很容易忘记。
 *但是本题还有不少细节需要扣的，如果不同的单词有相同出现频率，按字母顺序排序。需要增加这个：
 *  if (o1.getValue().equals(o2.getValue())){
*            return o2.getKey().compareTo(o1.getKey());
*    }
 */
public class HeaptopKFrequent692LeetCode {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String,Integer>  hashMap = new HashMap<>();
        for(int i=0;i<words.length;i++){
            hashMap.put(words[i],hashMap.getOrDefault(words[i],0)+1);
        }
        PriorityQueue<HashMap.Entry<String,Integer>> minHeap = new PriorityQueue<>(
                new Comparator<HashMap.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {

                if (o1.getValue().equals(o2.getValue())){
                    /**
                     *  注意这里是o2在前，也就是说如果字符串出现的次数相等的情况，比如i，love,
                     *  应该把love放在靠近最小堆顶的位置。因为比如示例二：
                     *              day
                     *              /       \
                     *          sunny    is
                     *          /
                     *       the
                     *  次数少的放在顶端，而输出要次数多的放在开头，所以输出需要堆顶元素倒着
                     *  放入数组。就变成[the,is,sunny,day]
                     *  那么如果相等次数相等情况下，应该是
                     *       love
                     *       /
                     *       i
                     *   因为要倒着输出，所以和字典序相反的排在靠近堆顶元素的位置。
                     *   这样倒着输出的时候才是正确的输出。
                     */
                    return o2.getKey().compareTo(o1.getKey());
                }
                return o1.getValue()-o2.getValue();
            }
        });
        for (HashMap.Entry<String,Integer> digits:hashMap.entrySet()){
            if (minHeap.size()<k){
                minHeap.add(digits);
            }else {
                //这里要非常注意，用的是最小堆的内部排序规则，因为频率相同也要进行排序
                if (minHeap.comparator().compare(digits,minHeap.peek())>0){
                    minHeap.remove();
                    minHeap.add(digits);
                }

            }
        }
        int count = minHeap.size();
        String [] result = new String[count];
        for (int i = count-1;i>=0;i--){
            result[i]=minHeap.remove().getKey();
        }
        return Arrays.asList(result);
    }

    public static void main(String[] args) {
        String [] words ={"i", "love", "leetcode", "i", "love", "coding"};
        int k =2;
        List<String> list = new HeaptopKFrequent692LeetCode().topKFrequent(words, k);
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}
