import java.util.LinkedList;
/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重
 * 复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总
 * 是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出
 * 现像 3a 或 2[4] 的输入。
 * 示例:
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 * @author LemonLin
 * @Description :StackdecodeString394LeetCode
 * @date 20.2.4-9:55
 * 思路:考虑用栈，如果是还没遇到],一律进栈，直到遇到],开始出栈。出栈的时候，如果没有
 * 遇到[,对字母进行拼接，一直到遇到[,下一个出栈的数字乘以拼接的字符。然后开始新一轮的
 * 进栈。如此反复。
 * bug1:
 * 输入:
 * "100[leetcode]"
 * 输出
 * "10leetcode"
 * 预期结果
 * "leetcodeleetcodeleetcodel......tcodeleetcode
 * bug2:
 * 输入：
 * "3[z]2[2[y]pq4[2[jk]e1[f]]]ef"
 * 输出：
 * "zzzyypqfejkjkfejkjkfejkjkfejkjkyypqfejkjkfejkjkfejkjkfejkjkef"
 * 预期：
 * "zzzyypqjkjkefjkjkefjkjkefjkjkefyypqjkjkefjkjkefjkjkefjkjkefef"
 * 这里要注意两个问题：
 * 一：从栈中读出字符串，反转字符串要用另外一个栈存一遍，再读出来，这样就达到了反转
 * 的目标，不能简单的用stringBuilder.reverse(),因为可能是添加是这样添加的
 * stack：abc,bb,cde
 * 目标得到：cde,bb,abc
 * 如果直接用stringBuilder.reverse()，就变成edc,bb,cba，不符合要求。
 * 二：字符串是否是数字，不能用Integer.valueOf(str)>=0&&Integer.valueOf(str)<=9
 * 因为当str不是数字的时候，Integer.valueOf()会报错。应该用Character.isDigit()一个
 * 一个判断。
 * 增加题解简约版代码：自己写的实在太复杂了。
 * 参考：https://leetcode-cn.com/problems/decode-string/solution/
 * decode-string-fu-zhu-zhan-fa-di-gui-fa-by-jyd/
 * 新思路：自己写的为什么那么复杂，就是因为只用到了一个栈，应该把数字和字符用两个
 * 栈来存储，这样思路很清晰。简洁了很多代码。
 */
public class StackdecodeString394LeetCode {

    //题解简约版：
    public String decodeString2(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        //把数字和字符用两个栈来存储
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for(Character c : s.toCharArray()) {
            if(c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                //res这里存储临时结果。
                res = new StringBuilder();
            }
            else if(c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                //这里是关键，注意tmp添加了res,这个时候的res是遇到[之前的字符
                for(int i = 0; i < cur_multi; i++) tmp.append(res);
                //链接这里为啥出了stack_res栈，这是处理
                //3[a2[c]]上面的tmp=cc,还需要把a处理了。这样就完结了一次处理。
                res = new StringBuilder(stack_res.removeLast() + tmp);
            }
            //这里为了处理数字是多位数的情况，比如12[c]这里的数字12就是两位数
            else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);
        }
        //退出循环后，这里res存储的是总结果。
        return res.toString();
    }

    public String decodeString(String s) {
        if (s==null||s.length()==0)return "";
        LinkedList<String> stack = new LinkedList();
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder digitString = new StringBuilder();
        LinkedList <String> stackReverse = new LinkedList<>();
        boolean flag=false;
        for (char c:s.toCharArray()){
            if (c !=']'){
                stack.addLast(String.valueOf(c));
                if (c=='['){
                    flag=true;
                }
            }else {
                String temp = stack.removeLast();
                while (!temp.equals("[")){
                    stackReverse.addLast(temp);
                    temp=stack.removeLast();
                }
                //利用一个栈反转字符串
                while (stackReverse.size()!=0){
                    stringBuilder.append(stackReverse.removeLast());
                }
                if (stack.size()!=0){
                    int count = 0;
                    while (!stack.isEmpty()&&isNumeric(stack.peekLast())){
                        stackReverse.addLast(stack.removeLast());
                    }
                    //可能有多个数字。不单单一个数字。
                    while (stackReverse.size()!=0){
                        digitString.append(stackReverse.removeLast());
                    }
                    count = Integer.valueOf(digitString.toString());
                    //要记得清空一次，下次利用。否则会记录上次的垃圾数据，影响结果
                    digitString.delete(0,digitString.length());
                    temp =stringBuilder.toString();
                    while (count>1){
                        stringBuilder.append(temp);
                        count--;
                    }
                    stack.addLast(stringBuilder.toString());
                    //要记得清空一次，下次利用。否则会记录上次的垃圾数据，影响结果
                    stringBuilder.delete(0,stringBuilder.length());
                }
            }
        }
        stringBuilder.delete(0,stringBuilder.length());
        //解决输入没有[]的问题，只有100hello，或者hello
        if (!flag){
            char[] chars = s.toCharArray();
            int count =chars[0]-'0';
            //解决输入是s="heloo",没有数字问题
            if (count<0||count>9){
                return s;
            }
            for (int i=1;i<s.length();i++){
                stringBuilder.append(chars[i]);
            }
            String temp =stringBuilder.toString();
            stringBuilder.delete(0,stringBuilder.length());
            while (count>0){
                stringBuilder.append(temp);
                count--;
            }
            return stringBuilder.toString();
        }
        while (!stack.isEmpty()){
            stringBuilder.append(stack.removeFirst());
        }
        return stringBuilder.toString();
    }

    private boolean isNumeric(String str){
       for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                        return false;
                    }
            }
            return true;
    }
    public static void main(String[] args) {
        String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        System.out.println(new StackdecodeString394LeetCode().decodeString(s));
    }
}
