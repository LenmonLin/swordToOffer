/**
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数
 * 都不少于 k 。输出 T 的长度。
 * 示例 1:
 * 输入:
 * s = "aaabb", k = 3
 * 输出:
 * 3
 * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2:
 * 输入:
 * s = "ababbc", k = 2
 * 输出:
 * 5
 * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * @author LemonLin
 * @Description :DivideConquerlongestSubstring395LeetCode
 * @date 20.6.13-15:06
 * 思路:参考：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/solution/javadi-gui-by-tzfun/
 * 分治方法，逆向思考：
 * 先统计出每个字符出现的频次。
 * 维护一对双指针，从首尾开始统计，从首尾往中间排除，一旦发现出现频次小于k的字符，
 * 以该字符为分割线，分别递归求其最大值返回。
 */
public class DivideConquerlongestSubstring395LeetCode {
    public int longestSubstring(String s, int k) {
        char [] chars = s.toCharArray();
        int left =0;
        int right = chars.length-1;
        if (chars.length ==0||k>chars.length)return 0;
        if (k<2)return chars.length;
        return helper(chars,k,left,right);
    }

    private int helper(char [] chars,int k,int left,int right){
        //递归出口
        if (right - left + 1 < k) return 0;

        //统计次数
        int [] times = new int [26];
        for (int i=left;i<=right;i++){
            times[chars[i]-'a']++;
        }

        //如果字符出现的次数小于K，同时字符子串长度K，要将坐标后移
        while (times[chars[left]-'a']<k&&right-left+1>=k){
            left++;
        }
        while (times[chars[right]-'a']<k&&right-left+1>=k){
            right--;
        }

        //对子串再进行递归处理
        for (int i=left;i<=right;i++){
            //如果字符出现的次数小于K，对于子串进行递归处理
            if (times[chars[i]-'a']<k){
                return Math.max(helper(chars,k,left,i-1),helper(chars,k,i+1,right));
            }
        }
        //递归出口之一，返回字符子串长度
        return right-left+1;
    }
}
