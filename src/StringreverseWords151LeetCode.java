/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 进阶：
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 * 思路：从后往前遍历。遇到空格添加子串sub(index,i)到stringBuilder中。
 * @author LemonLin
 * @Description :StringreverseWords151LeetCode
 * @date 20.2.6-18:42
 */
public class StringreverseWords151LeetCode {
    public String reverseWords(String s) {
        if (s==null||s.length()==0)return s;
        StringBuilder stringBuilder = new StringBuilder();
        String s1 = s.trim();
        int index =0;
        boolean flag = false;
        int j=0;
        int i =s1.length()-1;
         while (i>=0){
            j=i;
            while (j>=0&&s1.charAt(j)!=' '){
                //flag标志第一个不是空格的字符
                if (!flag){
                    index =j;
                    flag = true;
                }
                j--;
            }
             //处理到第一个单词时，不需要加空格
             if (j==-1){
                 stringBuilder.append(s1.substring(0,index+1));
                 break;
             }
            stringBuilder.append(s1.substring(j+1,index+1));
            stringBuilder.append(" ");
            flag=false;
            //j减一是此刻j在空格位置上
            while (j>=0&&s1.charAt(j)==' '){
                j-=1;
            }
            i=j;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String s = "hello world!";
//                "the sky is blue";
        System.out.println(new StringreverseWords151LeetCode().reverseWords(s));
    }
}
