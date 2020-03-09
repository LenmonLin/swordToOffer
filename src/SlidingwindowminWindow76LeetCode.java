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
 * 模板化改进：参考：https://mp.weixin.qq.com/s/nJHIxQ2BbqhDv5jZ9NgXrQ
 * 模板代码：
 *                                  string s, t;
 *                                  // 在 s 中寻找 t 的「最小覆盖子串」
 *                                  int left = 0, right = 0;
 *                                  string res = s;
 *                                  // 先移动 right 寻找可行解
 *                                  while(right < s.size()) {
 *                                      window.add(s[right]);
 *                                      right++;
 *                                      // 找到可行解后，开始移动 left 缩小窗口
 *                                      while (window 符合要求) {
 *                                          // 如果这个窗口的子串更短，则更新结果
 *                                          res = minLen(res, window);
 *                                          window.remove(s[left]);
 *                                          left++;
 *                                      }
 *                                  }
 *                                  return res;
 */
public class SlidingwindowminWindow76LeetCode {
    public String minWindow2(String s, String t) {
        HashMap<Character,Integer> tHash = new HashMap<>();
        //记录s 上的windows
        HashMap<Character,Integer> windows = new HashMap<>();
        int left =0;
        int right = 0;
        //先用一个循环把字符串t中的字符以及次数用hashMap 记录下来备用
        for (Character c:t.toCharArray()){
            //这个方法是如果hashmap中没有存在c,就数量置为0并加1，如果存在就取出value并加一
            tHash.put(c,tHash.getOrDefault(c,0)+1);
        }
        //用来记录滑动窗口中有多少字符个数是在t字符串的，最后如果这个个数等于t字符串
        // 长度就算满足条件，进入新的循环。同时更新最小子串的长度。(注意这里有个小bug，
        // 可能t字符串包含重复的字符，所以不能用t字符串的长度作为判断条件，应该用tHash
        // 的长度作为标准，这样就处理了重复的情况)
        int matchCount = 0;

        //用来更新最小的字符串长度，默认是s字符串长度
        int minLength =s.length();
        //用来保存最小字符串长度时的坐标，返回最后结果的时候需要用到坐标
        int minLeft=0, minRight =0;
        while (right<s.length()){
            char temp = s.charAt(right);
            //如果这个字符在tHash 中存在
            if (tHash.containsKey(temp)) {
                windows.put(temp, windows.getOrDefault(temp, 0) + 1);
                //如果滑动窗口中的字符和tHash中的字符个数相等，可以计算matchCount
                if ((int) windows.get(temp) == tHash.get(temp)) {
                    matchCount++;
                }
            }
            //这一步很关键，算模板代码,而且是在上述if括号的外面。
            right++;
            while (matchCount == tHash.size()){
                //更新最小字符串长度值,同时需要保存此刻的坐标以便最后返回结果使用，
                // 这里要有等号，否则如果s,和t相等长度，无法更新
                if (right-left<=minLength){
                    minLength = right-left;
                    minLeft = left;
                    minRight = right;
                }
                //接下来滑动窗口的左侧窗口要缩小，如果s[left]中的字符不属于t中的话，
                // 直接缩小左侧窗口；如果s[left]中的字符属于t的话，要更新window滑动
                // 窗口中的value值
                char temp2 = s.charAt(left);
                if (tHash.containsKey(temp2)){
                    windows.put(temp2,windows.get(temp2)-1);
                    //这里还需要判断一下才能更新matchCount，因为可能滑动窗口中含有
                    // 重复字符
                    if (windows.get(temp2)<tHash.get(temp2)){
                        matchCount--;
                    }
                }
                left++;
            }
        }
        return s.substring(minLeft,minRight);
    }

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
            String s =
                    "AA"
//                    "ADOBECODEBANC"
                    ;
            String t =
                    "AA"
//                    "ABC"
                    ;
            System.out.println(new SlidingwindowminWindow76LeetCode().minWindow2(s, t));
    }
}
