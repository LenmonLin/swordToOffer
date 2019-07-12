import java.util.HashMap;
/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * 思路：滑动窗口法
  在滑动窗口类型的问题中都会有两个指针。一个用于延伸现有窗口的 right指针，和一个用于收缩窗口的
 left 指针。在任意时刻，只有一个指针运动，而另一个保持静止。我们通过移动right指针不断扩张窗口。
 当窗口包含全部所需的字符后，如果能收缩，我们就收缩窗口直到得到最小窗口。
 答案是最小的可行窗口。

 注意：滑动窗口只需记录窗口两个指针即可，不一定需要使用linkedlist之类的做实体窗口！

 举个例子，S = "ABAACBAB"，T = "ABC"。则问题答案是 "ACB"
 算法：
 1、初始，left指针和right指针都指向S的第一个元素.
 2、将 right 指针右移，扩张窗口，直到得到一个可行窗口，亦即包含T的全部字母的窗口。
 3、得到可行的窗口后，将left指针逐个右移，若得到的窗口依然可行，则更新最小窗口大小。
 4、若窗口不再可行，则跳转至 2。（这一步很重要，一直没想明白）
 遇到的问题：需要判断滑动窗口中是否包含t，这样要用到hashmap 的计数法,用一个变量cnt记录
 T中字符在S的滑动窗口中出现的次数。
 考虑用hashmap实现，
    1、遍历T，统计T串中字母的个数，而不是仅仅看某个字母是否在T串中出现。
    2、滑动窗口有窗口拓展过程：遍历S串，对于S中的字母，如果是T中的字符，映射hashmap
   中的value值如果大于0，cnt+1,(不能等于0，等于0，说明T中字符在S中出现过了)，然后不
  管cnt是否加一，都要把value减一，以此记录滑动窗口中多余出现T中字符的数量，当用左窗口缩
 小的时候可以加回来。
    3、当cnt被加满等于T的长度时，说明滑动窗口中已经包含T所有字母的子串。此时开始左窗口缩小
 看最小能缩小到什么程度，先更新最小子串长度，开始缩小滑动窗口，加个if中对于S中的字母，如果
 是T中的字符，此时hashmap对应的value值可能为负数，对应说明滑动窗口中有多余的T字符，如果
 value+1>0,说明没有多余字符，即将要把必要的T字符排除在滑动窗口之外，这时cnt减一，然后不
 管cnt是否减一，都要把value加一，以此记录滑动窗口把多余出现T中的字符排除到滑动窗口外。缩小
 到cnt不等于T的长度，重复2、3.
 * @author LemonLin
 * @Description :StringMinimumWindowSubstring76LeetCode
 * @date 19.6.23-17:31
 */
public class StringMinimumWindowSubstring76LeetCode {
    public String minWindow(String s, String t) {
            //设置flag ，是为了排除s=a,t=aa的情况
            boolean flag= false;
            int left = 0;
            int lengthOfMin = s.length();
            int minLeft=0;
            int minRight=0;
            HashMap<Character,Integer> hashMap = new HashMap();
            int cnt=0;
            //遍历T串
            for (char c : t.toCharArray())
                hashMap.put( c, hashMap.containsKey(c) ? hashMap.get(c)+1 : 1);
            //遍历S串
            for (int right=0;right<s.length();right++){
                char temp = s.charAt(right);
                if (hashMap.containsKey(temp)){
                    cnt =hashMap.get(temp) > 0 ? cnt+1 : cnt;
                    hashMap.put(temp,hashMap.get(temp)-1);
                }
                while (cnt==t.length()){
                    flag=true;
                    if(right-left+1<=lengthOfMin){
                        lengthOfMin = right-left+1;
                        minLeft = left;
                        minRight = right;
                    }
                    char c = s.charAt(left);
                    if (hashMap.containsKey(c)){
                        if (hashMap.get(c)+1>0) cnt--;
                        hashMap.put(c,hashMap.get(c)+1);
                    }
                    left++;
                }
            }
            if (!flag)return "";
            return s.substring(minLeft,minRight+1);
        }
    public static void main(String[] args) {
            String s ="AA";
            String t = "AA";
            System.out.println(new StringMinimumWindowSubstring76LeetCode().minWindow(s, t));
    }
}
