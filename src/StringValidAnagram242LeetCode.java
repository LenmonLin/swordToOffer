import java.util.HashMap;
/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * @author LemonLin
 * @Description :StringValidAnagram
 * @date 19.6.25-19:28
 * 思路：看清题目：异位词，其实就是一个单词打乱顺序了。解题思路用hashmap即可，第一次遍历
 * 存储出现的次数，第二次遍历目标词，如果次数小于0，或者不存在key，即返回false,第三次遍历是
 * 判断经过第二次之后value值是否为0，排除s="ab",t="a"的情况。
 */
public class StringValidAnagram242LeetCode {
    public boolean isAnagram(String s, String t) {
        HashMap hashMap = new HashMap();
        char [] chars=s.toCharArray();
        for (int i=0;i<chars.length;i++){
            if (hashMap.containsKey(chars[i])){
                hashMap.put(chars[i],(int)hashMap.get(chars[i])+1);
            }else {
                hashMap.put(chars[i],1);
            }
        }
        char[] chart = t.toCharArray();
        for (int j=0;j<chart.length;j++){
            if (hashMap.containsKey(chart[j])){
                hashMap.put(chart[j],(int)hashMap.get(chart[j])-1);
            }
            //这里是否包含应该写在前面，否则如果没有包含就先get，可能不存在的情况下会空指针异常
            if (!hashMap.containsKey(chart[j])||(int)hashMap.get(chart[j])<0){
                return false;
            }
        }
        for (int k=0;k<chars.length;k++){
            if ((int)hashMap.get(chars[k])!=0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s= "ab";
        String t="a";
        System.out.println(new StringValidAnagram242LeetCode().isAnagram(s, t));
    }
}
