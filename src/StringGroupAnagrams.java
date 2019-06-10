import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author LemonLin
 * @Description :StringGroupAnagrams
 * @date 19.6.10-14:49
 * Given an array of strings, group anagrams together.
 * Example:
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 *给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 思路：
 *      全排列不同，但是字母组合内包含的字母是相同的，所以考虑集合的问题，将二十六个字母用hashmap进行
 *      数字映射(注意这里只能用素数映射，否则会出现1*6*1=2*3*1这种错误)，然后读入每个单词，如果乘积
 *      相同就分为一组。
 */
public class StringGroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length==0)return new ArrayList();
        String aToz = "abcdefghijklmnopqrstuvwxyz";
        int prime[] =new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67,
                71, 73, 79, 83, 89, 97, 101, 103};
        String str = "";
        HashMap charToInt = new HashMap();
        Map<Integer,List<String>> map=new HashMap<>();
        //26个字母用素数映射
        for (int i=1;i<=26;i++){
            charToInt.put(aToz.charAt(i-1),prime[i-1]);
        }
        for (int j =0;j<strs.length;j++){
            Integer multiplie = 1;
            //取出字符串数组中的每个字符串
            str = strs[j];
            for (int k=0;k<str.length();k++){
                multiplie = multiplie*(int)charToInt.get(str.charAt(k));
            }
            //题目要求返回的是嵌套数组，用hashmap把积作为key,目标字符串数组作为value
            if (map.containsKey(multiplie)){
                map.get(multiplie).add(str);
            }else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(multiplie,list);
            }
        }
        List<List<String>> result = new ArrayList<>(map.values()) ;
        return result;
    }

    public static void main(String[] args) {
        String[] test = new String[]{"ray","cod","abe","ned","arc","jar","owl","pop",
                "paw","sky","yup","fed","jul","woo","ado","why","ben",
                "mys","den","dem","fat","you","eon","sui","oct","asp","ago","lea","sow",
                "hus","fee","yup","eve","red",
                "flo","ids","tic","pup","hag","ito","zoo"};
        System.out.println(new StringGroupAnagrams().groupAnagrams(test));
    }
}
