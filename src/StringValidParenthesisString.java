/**
 * @author LemonLin
 * @Description :StringValidParenthesisString
 * @date 19.6.17-23:45
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to
 * check whether this string is valid. We define the validity of a string by these rules:
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or
 * an empty string.
 * An empty string is also valid.
 * Example 1:
 * Input: "()"
 * Output: True
 * Example 2:
 * Input: "(*)"
 * Output: True
 * Example 3:
 * Input: "(*))"
 * Output: True
 * Note:
 * The string size will be in the range [1, 100].
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。
 * 有效字符串具有如下规则：
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 * 思路：
 * 既然星号可以当左括号和右括号，那么我们就正反各遍历一次，正向遍历的时候，我们把星号都当成
 * 左括号，此时用经典的验证括号的方法，即遇左括号计数器加1，遇右括号则自减1，如果中间某个时
 * 刻计数器小于0了，直接返回false。如果最终计数器等于0了，我们直接返回true，因为此时我们把
 * 星号都当作了左括号，可以跟所有的右括号抵消。而此时就算计数器大于0了，我们暂时不能返回
 * false，因为有可能多余的左括号是星号变的，星号也可以表示空，所以有可能不多，我们还需要
 * 反向遍历一下，这是我们将所有的星号当作右括号，遇右括号计数器加1，遇左括号则自减1，
 * 如果中间某个时刻计数器小于0了，直接返回false。遍历结束后直接返回true，
 * 这是为啥呢？此时计数器有两种情况，要么为0，要么大于0。为0不用说，肯定是true，为啥大
 * 于0也是true呢？因为之前正向遍历的时候，我们的左括号多了，我们之前说过了，多余的左括
 * 号可能是星号变的，也可能是本身就多的左括号。本身就多的左括号这种情况会在反向遍历时
 * 被检测出来，如果没有检测出来，说明多余的左括号一定是星号变的。而这些星号在反向遍历
 * 时又变做了右括号，最终导致了右括号有剩余，所以当这些星号都当作空的时候，左右括号都
 * 是对应的，即是合法的。你可能会有疑问，右括号本身不会多么，其实不会的，如果多的话，
 * 会在正向遍历中被检测出来
 *
 * 总结思路：正向遍历，把*当做左括号统计，如果左括号的数量大于右括号，继续反向遍历，否则返回false；
 * 反向遍历，把*当做右括号统计，如果左括号的数量大于右括号，返回false，否则返回true；
 * 因为如果正向遍历，左括号大于右括号，说明要么是左括号和*相加的数量多于右括号，要么是左括号的数量
 * 多于右括号，同时如果反向遍历，左括号小于右括号，说明要么是左括号小于右括号加*的数量，要么是左括
 * 号的数量小于右括号。如果正反遍历都是正数，那么只能是其中存在*这种情况。
 */
public class StringValidParenthesisString {
    public boolean checkValidString(String s) {
        char[] chars = s.toCharArray();
        int LeftParenthesis=0,RightParenthesis=0;
        for (int i=0;i<chars.length;i++){
            if (chars[i]=='('||chars[i]=='*'){
                LeftParenthesis++;
            }else {
                RightParenthesis++;
            }
            if (RightParenthesis>LeftParenthesis)return false;
        }
        LeftParenthesis=0;
        RightParenthesis=0;
        for (int j=chars.length-1;j>=0;j--){
            if  (chars[j]==')'||chars[j]=='*'){
                RightParenthesis++;
            }else {
                LeftParenthesis++;
            }
            if (LeftParenthesis>RightParenthesis)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "())";
        System.out.println(new StringValidParenthesisString().checkValidString(s));
    }
}
