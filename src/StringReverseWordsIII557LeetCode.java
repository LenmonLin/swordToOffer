/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例 1:
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc" 
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 * 思路：
 * 主要要解决两个问题，第一：把字符串的每个单词分隔出来，用正则表达式法s.split("\\s+");
 * 第二：反转每个单词。解法：用首尾双指针法
 * @author LemonLin
 * @Description :StringReverseWordsIII557LeetCode
 * @date 19.7.31-10:58
 */
public class StringReverseWordsIII557LeetCode {
    public String reverseWords(String s) {
        String [] arr = s.split("\\s+");
        //用来把空格和反转后的单词拼接在一起
        StringBuilder result = new StringBuilder();
        for (int i =0;i<arr.length;i++){
            arr[i]=reverse(arr[i]);
            result.append(arr[i]);
            //如果不是最后一个单词，都在单词末尾加一个空格
            if (i!=arr.length-1){
                result.append(" ");
            }
        }
       return result.toString();
    }
    public String reverse(String word){
        int left =0;
        int right = word.length()-1;
        //因为字符串不能直接修改的，所以要转换为字符串数组
        char [] chars = word.toCharArray();
        while (left<right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right]=temp;
            left++;
            right--;
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String  s = "Let's take LeetCode contest";
        System.out.println(new StringReverseWordsIII557LeetCode().reverseWords(s));
    }
}
