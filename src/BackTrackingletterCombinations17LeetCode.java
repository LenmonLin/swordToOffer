import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * 思路：类似全排列，只是怎么解决输入23的长度为2，以及2,3所代表的字母的个
 * 数（长度为3），这两个长度的关系。
 * 答：这里不需要考虑为23的长度建立for循环，因为把她交给递归出口来解决，
 * 也就是 if (input.length()==temp.length())中的temp长度，而且注意这里回溯函数
 * 传入的是inputIndex+1，也就是这里考虑了23的长度，而 for (int i=0;i<letters.length();
 * i++){ 考虑的才是2,3所代表的字母的个数（长度为3），其实应该在回溯函数的时候
 * 传入2,3所代表的字母的个数(7的长度为4)，因为这个长度会变，有3有4，为什么没有传入，
 * 这里可以关注回溯函数传入了inputIndex+1，而这个其实就去对应寻找了hashmap，
 * 然后求了对应hashmap中的value字符串的长度。一言以蔽之，回溯函数传入inputIndex+1，
 * inputIndex+1经过转换获得了2，3所代表的字符的个数。
 * @author LemonLin
 * @Description :BackTrackingletterCombinations17LeetCode
 * @date 19.8.16-19:48
 */
public class BackTrackingletterCombinations17LeetCode {
    List<String>  result = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        Map<Character, String> phone = new HashMap<Character, String>();
        phone.put('2', "abc");
        phone.put('3', "def");
        phone.put('4', "ghi");
        phone.put('5', "jkl");
        phone.put('6', "mno");
        phone.put('7', "pqrs");
        phone.put('8', "tuv");
        phone.put('9', "wxyz");
        //判空
        if (digits.length() == 0)return new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        backtrack(digits,index,stringBuilder,phone);
        return result;
    }
    public void backtrack(String input, int inputIndex,
                          StringBuilder temp,Map phone) {
        //输入的数字字符串的长度如果等于组合的路径字符串长度，就添加进结果列表。
        if (input.length()==temp.length()){
            result.add(new String(temp));
            return;
        }
        if (inputIndex<=input.length()-1){
            Character character = input.charAt(inputIndex);
            String letters = (String) phone.get(character);
            for (int i=0;i<letters.length();i++){
                temp.append(letters.charAt(i));
                backtrack(input,inputIndex+1,temp,phone);
                //回溯需要StringBuilder删除最后一个字符
                temp.deleteCharAt(temp.length()-1);
            }
        }
    }

    public static void main(String[] args) {
        List arrayList = new BackTrackingletterCombinations17LeetCode().
                letterCombinations("23");
        for (int i=0;i<arrayList.size();i++){
            System.out.println(arrayList.get(i));
        }
    }
}
