import java.util.HashSet;
/**
 *编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * 思路：用两个指针，left从头开始遍历，right从后开始遍历，遇到元音字母进行交换。
 * 类似快速排序的算法流程。字符数组转换为字符串用String.valueOf(chars)方法；判
 * 断是否元音字母的方法，本题用了hashset来存放元音字母，用contains来判断。
 * @author LemonLin
 * @Description :DoublePointerreverseVowels345LeetCode
 * @date 19.6.14-11:07
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * Example 1:
 * Input: "hello"
 * Output: "holle"
 * Example 2:
 * Input: "leetcode"
 * Output: "leotcede"
 * Note:
 * The vowels does not include the letter "y".
 */
public class DoublePointerreverseVowels345LeetCode {
    public String reverseVowels(String s) {
        if(s==null)return null;
        char[] chars = s.toCharArray();
        int left =0;
        int right =chars.length-1;
        HashSet hashSet = new HashSet();
        hashSet.add('a');hashSet.add('A');
        hashSet.add('o');hashSet.add('O');
        hashSet.add('e');hashSet.add('E');
        hashSet.add('u');hashSet.add('U');
        hashSet.add('i');hashSet.add('I');
        while (left<right){
            while (left<right&&!hashSet.contains(chars[left])){
                left++;
            }
            while (left<right&&!hashSet.contains(chars[right])){
                right--;
            }
            if (left<=right){
                char temp = chars[left];
                chars[left]= chars[right];
                chars[right]=temp;
                left++;
                right--;
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String s=
//                "a."
                "aeou"
                ;
        System.out.println(new DoublePointerreverseVowels345LeetCode().reverseVowels(s));
    }
}
