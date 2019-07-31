/**
 * 给定一个非空的字符串，最多可以删除其中一个字符，再判断该字符串是否是回文串
 * @author LemonLin
 * @Description :StringValidPalindromeII
 * @date 2019/5/9-21:30
 * Given a non-empty string s, you may delete at most one character. Judge whether you can
 * make it a palindrome.
 * Example 1:
     *  Input: "aba"
     *  Output: True
 * Example 2:
     * Input: "abca"
     * Output: True
 *     Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is
 * 50000.
 * 解题思路：
 *      设置头尾两个指针head,tail分别指向头尾两个字符
 *      while(head<=tail){
 *          if(Atchar[head] == Atchar[tail]){
 *              head++,tail--;
 *          }else{
 *              删除其中一个字符
 *              if(Atchar[head] == Atchar[tail]){
 *                  head++,tail--;
 *              }else{
 *                  return false;
 *              }
 *          }
 *      }
 *   这里要注意head,或者tail的变化只能一次，
 */
public class StringValidPalindromeII680LeetCode {
    public boolean validPalindrome(String s) {
        int head = 0,tail = s.length()-1;
        //flag用来记录head或者tail变化了几次
        int flag=0;
        while (head<=tail){
            if (s.charAt(head)==s.charAt(tail)){
                head++;
                tail--;
                if (flag>1){
                    return false;
                }
            }else {
                flag++;
                return delOneChar(s,head,tail,0)||delOneChar(s,head,tail,1);
            }
        }
        return true;
    }
//因为如果直接传入不论是head++，还是tail--，都会直接影响到下一次调用delOneChar的值，所以用mark来记录
    public boolean delOneChar(String s, int head, int tail,int mark){
        if (mark == 0){
            head++;
        }else {
            tail --;
        }
        while (head <= tail){
            if (s.charAt(head)==s.charAt(tail)){
                head++;
                tail--;
            }else {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        String test =
                "lcuucul";
        boolean result = new StringValidPalindromeII680LeetCode().validPalindrome(test);
        System.out.println(result);
    }
}
