import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回
 * 这些子串的起始索引。
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * 说明：
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * 输出:
 * [0, 6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 * 输入:
 * s: "abab" p: "ab"
 * 输出:
 * [0, 1, 2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 * @author LemonLin
 * @Description :SlidingwindowfindAnagrams438LeetCode
 * @date 20.3.9-11:15
 * 思路：参考：https://mp.weixin.qq.com/s/nJHIxQ2BbqhDv5jZ9NgXrQ
 * 类似LeetCode76的模板代码，建议先看一下LeetCode76的解题注释再看本题会比较清晰
 */
public class SlidingwindowfindAnagrams438LeetCode {
    public List<Integer> findAnagrams(String s, String p) {
        int left =0;
        int right =0;
        HashMap<Character,Integer> pHash = new HashMap<>();
        HashMap<Character,Integer> window = new HashMap<>();

        int matchCount =0;
        List<Integer>  result = new ArrayList<>();
        //先遍历一边p字符串中的字符个数
        for (int i=0;i<p.length();i++){
            pHash.put(p.charAt(i),pHash.getOrDefault(p.charAt(i),0)+1);
        }
        while (right<s.length()){
            char temp = s.charAt(right);
            if (pHash.containsKey(temp)){
                window.put(temp,window.getOrDefault(temp,0)+1);
                if ((int)window.get(temp)==pHash.get(temp)){
                    matchCount++;
                }
            }
            right++;
            while (matchCount==pHash.size()){
                //这里的代码和LeetCode76的模板代码不一样，LeetCode76要找长度最小字
                // 符串，本题其实找的是长度相等的字符串，因为matchCount ==pHash.size()
                // 不能保证长度相等，只是保证s中的字符在滑动窗口的那一部分中，出现在字符
                // 串p中的字符的个数和p相等，不能保证滑动窗口中的长度等于p字符串的长度，
                // 所以这里还需要加多一个判断条件。
                if (right-left ==p.length()){
                    result.add(left);
                }
                char temp2 = s.charAt(left);
                if (pHash.containsKey(temp2)){
                    window.put(temp2,window.get(temp2)-1);
                    //这里还需要判断一下才能更新matchCount，因为可能滑动窗口中含有
                    // 重复字符
                    if (window.get(temp2)<pHash.get(temp2)){
                        matchCount--;
                    }
                }
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> anagrams = new SlidingwindowfindAnagrams438LeetCode().
                findAnagrams(s, p);
        for (int i=0;i<anagrams.size();i++){
            System.out.println(anagrams.get(i));
        }
    }
}
