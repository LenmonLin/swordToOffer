import java.util.Stack;
/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 思路：利用栈来解决存储，比如存储顺序是({[ ,接下来如果是合法字符串的话，接下来必须是],而
 * 不是)或者}，所以不必担心顺序的问题，直接用栈顶出栈入栈就能解决问题。
 * @author LemonLin
 * @Description :StringValidParentheses
 * @date 19.6.10-23:32
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input
 * string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * Example 1:
 * Input: "()"
 * Output: true
 * Example 2:
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 * Input: "(]"
 * Output: false
 * Example 4:
 * Input: "([)]"
 * Output: false
 * Example 5:
 * Input: "{[]}"
 * Output: true
 */
public class StringValidParentheses20LeetCode {
    public boolean isValid(String s) {
        if (s==null)return true;
        Stack stack =new Stack();
        for(char c :s.toCharArray()){
            if (c=='('||c=='{'||c=='['){
                stack.push(c);
            }else if (c==')'&&!stack.empty()&&(char)stack.peek()=='('){
                stack.pop();
            }else if (c=='}'&&!stack.empty()&&(char)stack.peek()=='{'){
                stack.pop();
            }else if (c==']'&&!stack.empty()&&(char)stack.peek()=='['){
                stack.pop();
            }else {
                return false;
            }
        }
        if (stack.size()!=0){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s ="]{";
        System.out.println(new StringValidParentheses20LeetCode().isValid(s));
    }
}
