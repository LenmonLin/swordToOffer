import java.util.HashMap;
import java.util.LinkedList;
/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 思路：暴力法：
 * 滑动窗口法：
 *基本思路是需要用一个队列 ，来当做窗口，存放不重复的子串，比如advdfc,从头遍历，
 *                                                                                                 队列中的元素         最长子串长度
 * 遍历a,队列中为空，进队                                                                             a                   1
 * 遍历d,队列中为a，无重复，进队                                                                 ad                  2
 * 遍历v,队列中为ad，无重复，进队                                                               adv                3
 * 遍历d,队列中为adv，有重复，首次出现d和d前面字符出队，出队ad，队尾加d    vd                  3
 * 遍历f,队列中为vd，无重复，进队                                                                vdf                 3
 * 遍历c,队列中为vdf，无重复，进队                                                               vdfc               3
 * 所以队列选啥比较好，暂定选linkedlist,需要插入删除的操作
 * @author LemonLin
 * @Description :DoublePointerlengthOfLongestSubstring
 * @date 19.6.23-17:19
 * 方法二：用hashmap代替队列的作用，因为队列的contains实现感觉不太直观，同时也
 * 是双指针，i指向滑动窗口的左边，j指向滑动窗口的右边。
 * bug1:
 * 输入:
 * " "
 * 输出
 * 0
 * 预期结果
 * 1
 * bug2:
 * 输入:
 * "abba"
 * 输出
 * 3
 * 预期结果
 * 2
 */
public class DoublePointerlengthOfLongestSubstring3LeetCode {
    //用hashmap代码更简洁一些，为什么用hashmap就不需要删除有重复字符的前面的字
    // 符，因为这里用了i<=j来控制滑动窗口，这样即使在hashmap里面，没有满足i<=j，
    // 那么也不能进行更新滑动窗口左边的i坐标，所以就无需考虑，如果不在滑动窗口内，但
    // 是在hashmap内的情况了，这种情况，已经通过i<=j排除掉了。
        public int lengthOfLongestSubstring2(String s){
            if (s ==null)return 0;
            //key放字符串中的每个字符，value放字符在字符串中的下标
            HashMap<Character,Integer> hashMap = new HashMap<>();
            int i=0;
            int result =0;
            for (int j=0;j<s.length();j++){
                //hashMap.get(s.charAt(j))>=i为了解决bug2，排除掉之前已经重复数字但
                // 是已经不在i和j直接的数了。hashMap.get(s.charAt(j))>=i这个是保证滑动
                // 窗口的i不能超过j ,否则肯定出问题。
                if (hashMap.containsKey(s.charAt(j))&&hashMap.get(s.charAt(j))>=i){
                    //这里重新更新了一下滑动窗口的左侧的坐标。
                    i=hashMap.get(s.charAt(j));
                    i++;
                }
                hashMap.put(s.charAt(j),j);
                result = Math.max(result,j-i+1);
            }
            return result;
        }

    public int lengthOfLongestSubstring(String s) {
        //这里add,remove方法是List接口的;offer，poll方法是Queue接口的，虽然两种方法使用得到
        //的结果都一样，但是这里是把Linkedlist当做队列使用，所以使用offer，poll更形象
        LinkedList linkedList = new LinkedList();
        //记录最长子串长度
        int lengthOfLongest =0;
        //临时记录子串长度，但不是最长
        int length=0;
        for (int i=0;i<s.length();i++){
            if (!linkedList.contains(s.charAt(i))){
                linkedList.offer(s.charAt(i));
                length++;
            }else {
                //删除首次出现重复字符之前的字符
                while ((char)linkedList.peekFirst()!=s.charAt(i)){
                    linkedList.pollFirst();
                    length--;
                }
                //删除首次出现的重复字符
                linkedList.pollFirst();
                length--;
                //队尾添加重复字符
                linkedList.offer(s.charAt(i));
                length++;
            }
            if (length>lengthOfLongest){
                lengthOfLongest=length;
            }
        }
        return lengthOfLongest;
    }

    public static void main(String[] args) {
        String s = "abba";
        System.out.println(new DoublePointerlengthOfLongestSubstring3LeetCode()
                .lengthOfLongestSubstring2(s));
    }
}
