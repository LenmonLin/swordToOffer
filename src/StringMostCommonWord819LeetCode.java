import java.util.HashMap;
/**
 * 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中
 * 的单词。题目保证至少有一个词不在禁用列表中，而且答案唯一。禁用列表中的单词用小写字母表示，
 * 不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
 *思路：
 * 考虑用hashmap处理，把单词作为key，value值是单词出现的次数。
 * 1、关于段落提取单词的思路，先把段落中所有的符号,.:!?';用空格替代，然后再用正则表达式把\\s+
 * 匹配任意空白字符作为分隔点，把段落分隔成单词形式。
 * 2、利用hashmap统计key出现的频率
 * 3、把banner中单词key把hashmap对应的value值置为0;
 * 4、取出此时最大的value值对应的key
 * @author LemonLin
 * @Description :StringMostCommonWord
 * @date 19.6.10-22:14
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list
 * of banned words.  It is guaranteed there is at least one word that isn't banned, and that the
 * answer is unique.Words in the list of banned words are given in lowercase, and free of
 * punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
 * Example:
 * Input:
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * Output: "ball"
 * Explanation:
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word
 * in the paragraph.
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"),
 * and that "hit" isn't the answer even though it occurs more because it is banned.
 * Note:
 * 1 <= paragraph.length <= 1000.
 * 0 <= banned.length <= 100.
 * 1 <= banned[i].length <= 10.
 * The answer is unique, and written in lowercase (even if its occurrences in paragraph
 * may have uppercase symbols, and even if it is a proper noun.)
 * paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 * There are no hyphens or hyphenated words.
 * Words only consist of letters, never apostrophes or other punctuation symbols.
 */
public class StringMostCommonWord819LeetCode {
    public String mostCommonWord(String paragraph, String[] banned) {
        String symbol = ",.:!?';";
        for (char i : symbol.toCharArray()) {
            paragraph = paragraph.replace(i, ' ');
        }
        paragraph = paragraph.toLowerCase();
        String [] arr = paragraph.split("\\s+");
        HashMap<String,Integer> hashMap = new HashMap();
        for(String ss : arr){
            if (hashMap.containsKey(ss)){
                Integer integer= hashMap.get(ss)+1;
                hashMap.put(ss, integer);
            }else {
                hashMap.put(ss,1);
            }
        }
        for(int i=0;i<banned.length;i++){
            hashMap.put(banned[i].toLowerCase(),0);
        }
        int max =0;
        String result="";
        for (String string:hashMap.keySet()){
            string = string.toLowerCase();
            if (hashMap.get(string)>max){
                max = hashMap.get(string);
                result = string;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        String pa ="Bob. hIt, baLl";
        String[] ba = new String[]{"bob", "hit"};
        System.out.println(new StringMostCommonWord819LeetCode().mostCommonWord(pa, ba));
    }
}
