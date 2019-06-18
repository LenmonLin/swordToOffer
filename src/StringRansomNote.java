import java.util.HashMap;

/**
 * @author LemonLin
 * @Description :StringRansomNote
 * @date 19.6.17-23:11
 * Given an arbitrary ransom note string and another string containing letters from all the
 * magazines, write a function that will return true if the ransom note can be constructed
 * from the magazines ; otherwise, it will return false.Each letter in the magazine string
 * can only be used once in your ransom note.
 * Note:
 * You may assume that both strings contain only lowercase letters.
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 *给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串ransom能不能
 * 由第二个字符串magazines里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。)
 * 注意：
 * 你可以假设两个字符串均只含有小写字母。
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 *思路：可以用hashmap 变量magazine，存放出现的次数，再遍历一边ransom,如果出现次数少于0，
 * 或者没有出现的key,就返回false;
 */
public class StringRansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote==null||magazine==null)return false;
        HashMap hashMap = new HashMap();
        for (int i=0;i<magazine.length();i++){
            if (hashMap.containsKey(magazine.charAt(i))){
                Integer integer= (Integer) hashMap.get(magazine.charAt(i))+1;
                hashMap.put(magazine.charAt(i), integer);
            }else {
                hashMap.put(magazine.charAt(i),1);
            }
        }
        for (int j=0;j<ransomNote.length();j++){
            if (hashMap.get(ransomNote.charAt(j))!=null&&
                    (Integer)hashMap.get(ransomNote.charAt(j))>0){
                Integer value = (Integer) hashMap.get(ransomNote.charAt(j)) - 1;
                hashMap.put(ransomNote.charAt(j),value);
            }
            else {
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String ransomNote = "dehifb";
        String magazine = "hhjdgjbhahaagihhhhhajjibjffhijehda";
//        String ransomNote = "aa";
//        String magazine = "aab";
        System.out.println(new StringRansomNote().canConstruct(ransomNote, magazine));
    }
}
