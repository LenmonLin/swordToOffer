/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * 示例 1：
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * @author LemonLin
 * @Description :StringReverseString
 * @date 19.6.25-17:17
 * 思路：此题比较简单，直接首尾指针想中间靠拢即可，O(1)空间可以设置一个char temp,别被必须原地
 * 修改输入数组给唬住了,以为一个char 都不能设置。
 */
public class StringReverseString {
    public void reverseString(char[] s) {
        int left=0;
        int right=s.length-1;
        while (left<right){
            char temp = s[left];
            s[left]=s[right];
            left++;
            s[right]=temp;
            right--;
        }
    }

    public static void main(String[] args) {
    }
}
