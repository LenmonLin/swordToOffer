/**
 * 给定一个字符串， 判定它是否是回文（只统计字母、数字，其他字符请忽略）
 * @author LemonLin
 * @Description :StringValidPalindrome
 * @date 2019/5/9-16:40
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters
 * and ignoring cases.
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 * 定义空字符串也是有效的回文
 * Example 1:
 *      Input: "A man, a plan, a canal: Panama"
 *      Output: true
 *Example 2:
 *      Input: "race a car"
 *      Output: false
 * 解题思路：采用二分法，一个指针从左边遍历，一个从右边遍历，跳过非字母和非数字，当遍历到中点依
 * 然相同那就是回文。时间复杂度O(n), 空间复杂度O(1)
 */
public class StringValidPalindrome125LeetCode {
    public boolean isPalindrome(String s) {
        if(s.isEmpty()){
            return true;
        }
        char cHead,cTail;
        int head = 0, tail = s.length()-1;
        while (head<=tail){
            //全部转换为小写进行比较，另外可以利用String.charAt把字符串当做字符数组。
            cHead = Character.toLowerCase(s.charAt(head));
            cTail = Character.toLowerCase(s.charAt(tail));
            // 接下来的判断是需要跳过不是字符和数字的字符
            if (!isLetterOrDigit(cHead)){
                head++;
            }else if (!isLetterOrDigit(cTail)){
                tail--;
            }else {
                if (cHead== cTail){
                    head++;
                    tail--;
                }
                else return false;
            }
        }
        return true;
    }
    //判断字符是否是字母或者数字
    public boolean isLetterOrDigit(char c){
        if ((c >= 'A' && c <= 'Z')||(c >='a' && c<='z')||(c>='0'&&c<='9'))
            return true;
        return false;
    }
    public static void main(String[] args) {
        String test = "A man, a plan, a canal: Panama";
        String test2 =" ";
        boolean result = new StringValidPalindrome125LeetCode().isPalindrome(test2);
        System.out.println(result);
    }
}
