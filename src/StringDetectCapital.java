/**
 * 给定一个单词，你需要判断单词的大写使用是否正确。我们定义，在以下情况时，单词的大写用法是正确的：
 * 全部字母都是大写，比如"USA"。
 * 单词中所有字母都不是大写，比如"leetcode"。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
 * 否则，我们定义这个单词没有正确使用大写字母。
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Otherwise, we define that this word doesn't use capitals in a right way.
 * Example 1:
 * Input: "USA"
 * Output: True
 * Example 2:
 * Input: "FlaG"
 * Output: False
 * Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
 * 思路：题目比较简单，直接分成三种情况检测，
 *  当第一个、第二个字母是大写时，后面字母都是大写才是true;
 * 当第一个、第二个字母是小写时，后面字母都是小写才是true;
 * 当第一个字母是大写、第二个字母是小写时，后面字母都是小写才是true;
 * @author LemonLin
 * @Description :StringDetectCapital
 * @date 19.6.18-23:43
 */
public class StringDetectCapital {
    public boolean detectCapitalUse(String word) {
        if (word.length()==1)return true;
        char[] chars = word.toCharArray();
        if(word.length()==2){
            if (isCapitalLetter(chars[0])&&isLowerLetter(chars[1])
            ||isCapitalLetter(chars[0])&&isCapitalLetter(chars[1])
            ||isLowerLetter(chars[0])&&isLowerLetter(chars[1])){
                return true;
            }
            return false;
        }
        int i=2;
        int flag=1;
        if (isCapitalLetter(chars[0])&&isLowerLetter(chars[1])) {
            for (; i < chars.length; i++) {
                if (isLowerLetter(chars[i])) {
                    continue;
                } else {
                    flag = 0;
                }
            }
        }else if (isCapitalLetter(chars[0])&&isCapitalLetter(chars[1])){
            for (; i < chars.length; i++) {
                if (isCapitalLetter(chars[i])) {
                    continue;
                } else {
                    flag = 0;
                }
            }
        }else if (isLowerLetter(chars[0])&&isLowerLetter(chars[1])){
            for (; i < chars.length; i++) {
                if (isLowerLetter(chars[i])) {
                    continue;
                } else {
                    flag = 0;
                }
            }
        }else {
            return false;
        }
        if (flag==1)return true;
        return false;
    }
    public boolean isCapitalLetter(char ch){
        if (ch>='A'&&ch<='Z'){
            return true;
        }
        return false;
    }
    public boolean isLowerLetter(char ch){
        if (ch>='a'&&ch<='z'){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s= "cVe";
        System.out.println(new StringDetectCapital().detectCapitalUse(s));
    }
}
