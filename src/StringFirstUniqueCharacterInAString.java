import java.util.LinkedHashMap;

/**
 * @author LemonLin
 * @Description :StringFirstUniqueCharacterInAString
 * @date 19.6.10-23:39
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * Examples:
 * s = "leetcode"
 * return 0.
 * s = "loveleetcode",
 * return 2.
 * Note: You may assume the string contain only lowercase letters.
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 思路：用HashMap解决战斗，第一次遍历，存储出现的次数，第二次遍历，输出第一个value值为1的key
 * 这里要注意HashMap 存入和取出是下标是不按照顺序存放的，所以应该改成LinkedHashMap
 */
public class StringFirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        LinkedHashMap<Character,Integer> map = new LinkedHashMap();
        for (int i=0;i<s.length();i++){
            if (map.containsKey(s.charAt(i))){
                Integer integer= map.get(s.charAt(i))+1;
                map.put(s.charAt(i), integer);
            }else {
                map.put(s.charAt(i),1);
            }
        }
        for (Character ch:map.keySet()){
            if (map.get(ch) ==1){
                return s.indexOf(ch);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "loveleetcode";
        System.out.println(new StringFirstUniqueCharacterInAString().firstUniqChar(s));
    }
}
