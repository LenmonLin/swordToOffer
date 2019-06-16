import java.util.HashSet;

/**
 * @author LemonLin
 * @Description :StringReverseVowelsofaString
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
 *编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * 思路：用两个指针，left从头开始遍历，right从后开始遍历，遇到元音字母进行交换。类似快速排序的算法流程。
 * 字符数组转换为字符串用String.valueOf(chars)方法；
 */
public class StringReverseVowelsofaString {
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

        boolean flag= false;
        while (left<right){
            //"ae"情况
            if (hashSet.contains(chars[0])||hashSet.contains(chars[s.length()-1])){
                flag=true;
            }
            while (left<right&&hashSet.contains(chars[left])==false){
                left++;
                if (hashSet.contains(chars[left])==true)flag=true;
            }
            while (left<right&&hashSet.contains(chars[right])==false){
                 right--;
                if (hashSet.contains(chars[right])==true)flag=true;
            }
            if (flag == true){
                char temp = chars[left];
                chars[left]= chars[right];
                chars[right]=temp;
                flag=false;
            }
            //if判断条件是排除"a."情况
            if (left<right){
                left++;
                right--;
            }
            //排除"aeio"情况，即连着元音元素出现的情况
            if (hashSet.contains(chars[left])||hashSet.contains(chars[right])){
                flag=true;
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String s= "a.";
        System.out.println(new StringReverseVowelsofaString().reverseVowels(s));
    }
}
