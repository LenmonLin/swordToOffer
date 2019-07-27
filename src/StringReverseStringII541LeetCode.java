/**
*给定一个字符串和一个整数 k，你需要对从字符串开头算起的每个 2k 个字符的前k个字符进行反转。
 * 如果剩余少于 k 个字符，则将剩余的所有全部反转。如果有小于 2k 但大于或等于 k 个字符，
 * 则反转前 k 个字符，并将剩余的字符保持原样。
 *思路：主要是题目的理解要清楚，比如s="abcdefg",给定k=2;要反转的是ab,不反转的是2k=2*2=4,
 * 也就是第三，第四个不翻转，cd不翻转。然后接下去继续重复这个循环。
 * 翻转思路是设置两个首尾指针，用一个中间的变量来存，首尾互相交换,翻转的终止条件是，首尾指针想遇
 * @author LemonLin
 * @Description :StringReverseStringII
 * @date 19.6.17-22:47
 * Given a string and an integer k, you need to reverse the first k characters for every 2k
 * characters counting from the start of the string. If there are less than k characters left,
 * reverse all of them. If there are less than 2k but greater than or equal to k characters,
 * then reverse the first k characters and left the other as original.
 * Example:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 * Restrictions:
 * The string consists of lower English letters only.
 * Length of the given string and k will in the range [1, 10000]
 */
public class StringReverseStringII541LeetCode {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        boolean flag = false;
        //加if 解决 a,2的情况，即 If there are less than k characters left,reverse all of them.
        if (s.length()<k) {
            Reverse(chars,0,chars.length-1);
            flag = true;
        }
        if (flag == false){
            int cnt=0;
            int start=0,end=k-1;
            while (cnt<s.length()){
                    Reverse(chars,start,end);
                    start=start+2*k;
                    end = end+2*k;
                    //加if 解决 abcdefg,3的情况
                    if (s.length()-start<k){
                        Reverse(chars,start,s.length()-1);
                        break;
                     }
                    cnt=cnt+2*k;
            }
        }
        return String.valueOf(chars);
    }
    public void Reverse(char[] chars,int start,int end){
        char  temp= ' ';
        while (start<end){
            temp=chars[start];
            chars[start++]=chars[end];
            chars[end--]=temp;
        }
    }
    public static void main(String[] args) {
        String string = "abcdefghijklmn";
        System.out.println(new StringReverseStringII541LeetCode().reverseStr(string, 3));
    }
}
