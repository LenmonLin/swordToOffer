import java.util.HashSet;

/**
 * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串，
 *  比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。
 * 为了方便，所有26个英文字母对应摩尔斯密码表如下：
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.",
 * "--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，"cab" 可以写
 * 成 "-.-..--..."，(即 "-.-." + "-..." + ".-"字符串的结合)。我们将这样一个连接过程称作单词翻译。
 * 返回我们可以获得所有词不同单词翻译的数量。
 * International Morse Code defines a standard encoding where each letter is mapped to
 * a series of dots and dashes, as follows: "a" maps to ".-", "b" maps to "-...", "c" maps
 * to "-.-.", and so on.
 * For convenience, the full table for the 26 letters of the English alphabet is given below:
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",
 * ".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * Now, given a list of words, each word can be written as a concatenation of the Morse
 * code of each letter. For example, "cba" can be written as "-.-..--...", (which is the
 * concatenation "-.-." + "-..." + ".-"). We'll call such a concatenation, the transformation 
 * of a word.
 * Return the number of different transformations among all words we have.
 * Example:
 * Input: words = ["gin", "zen", "gig", "msg"]
 * Output: 2
 * Explanation:
 * The transformation of each word is:
 * "gin" -> "--...-."
 * "zen" -> "--...-."
 * "gig" -> "--...--."
 * "msg" -> "--...--."
 * There are 2 different transformations, "--...-." and "--...--.".
 * Note:
 * The length of words will be at most 100.
 * Each words[i] will have length in range [1, 12].
 * words[i] will only consist of lowercase letters.
 * 思路：先把给的单词拼接完之后再进行比较，比较用hashset进行存取，最后返回hashset的大小即可
 * 这里用注意几个点：
 * 1、摩斯电码与26个字母对应，可以不用hashmap,直接用string[] map数组存好之后，再用
 * map[char-'a']即可。
 * 2、每个对应之后的字符串存入stringBuilder之后应该用tostring转换之后才可以放入hashSet
 * @author LemonLin
 * @Description :StringUniqueMorseCodeWords
 * @date 19.6.18-23:50
 */
public class StringUniqueMorseCodeWords {
    public int uniqueMorseRepresentations(String[] words) {
        String [] map={".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.",
                "---",".--.", "--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        HashSet hashSet = new HashSet();
        for (String s:words){
            StringBuilder stringBuilder= new StringBuilder();
            for (char c:s.toCharArray()){
                stringBuilder.append(map[c-'a']);
            }
            hashSet.add(stringBuilder.toString());
        }
        return hashSet.size();
    }

    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println(new StringUniqueMorseCodeWords().uniqueMorseRepresentations(words));
    }
}
