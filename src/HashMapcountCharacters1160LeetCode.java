import java.util.Arrays;
/**
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），
 * 那么我们就认为你掌握了这个单词。
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 * 示例 1：
 * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
 * 输出：6
 * 解释：
 * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
 * 示例 2：
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 * 提示
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * 所有字符串中都仅包含小写英文字母
 * @author LemonLin
 * @Description :HashMapcountCharacters1160LeetCode
 * @date 20.3.17-9:10
 * 思路：用hashMap存放chars的字符和对应出现的字符次数。遍历words中的每个单词，
 * 如果能够在hashMap中找到，并且字符次数不会减到小于0，就算能够构成单词，检测下
 * 一个单词时，重置hashMap。
 * 后来发现hashMap无法深拷贝，只能用数组。数组能够深拷贝。
 */
public class HashMapcountCharacters1160LeetCode {
    public int countCharacters(String[] words, String chars) {
        if (words==null||chars==null)return 0;
        int result =0;
        int [] hash = new int[26];
        for (int i=0;i<chars.length();i++){
            Character tempI = chars.charAt(i);
            hash[tempI-'a']+=1;
        }
        for (int i=0;i<words.length;i++){
            //复制数组重新用
            int [] copyHash = Arrays.copyOf(hash,hash.length);
            //设置count的目的是为了如果遇到不满足条件的word,result加到一半怎么还原，
            // 就是靠count
            int count =0;
            for (int j=0;j<words[i].length();j++){
                Character tempJ = words[i].charAt(j);
                if (copyHash[tempJ-'a']>0){
                    result++;
                    count++;
                    copyHash[tempJ-'a']-=1;
                }else {
                    result = result-count;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String [] words={"hello","world","leetcode"};
        String chars ="";
        System.out.println(new HashMapcountCharacters1160LeetCode().
                countCharacters(words, chars));
    }
}
