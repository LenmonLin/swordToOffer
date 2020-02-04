import java.util.LinkedList;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将
 * 目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请
 * 参阅：Linux / Unix中的绝对路径 vs 相对路径
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜
 * 杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径
 * 的最短字符串。
 * 示例 1：
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * 示例 2：
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * 示例 3：
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * 示例 4：
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 * 示例 5：
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 * 示例 6：
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"
 * @author LemonLin
 * @Description :StacksimplifyPath71LeetCode
 * @date 20.2.4-17:36
 * 参考题解：https://leetcode-cn.com/problems/simplify-path/solution/zhan-by-powcai/
 * 比如示例4
 * 1.此题主要考察的是栈,所以定义一个辅助栈（实际定义LinkedList,因为后续要用到从队头
 * 出队拼接）;
 * 2.先把字符串以"/"为分隔符分割成数组,此时数组以下几种类型：
 *      有"路径"比如：a，b,等
 *      空格""
 *      单点 "."
 *      双点".."
 * 3.遍历数组,当s[i].equals("..")并且栈不空时出栈,因为".."表示上个目录，
 * 当!s[i].isEmpty() && !s[i].equals("."),入栈，即s[i]是路径入栈;
 * 4.然后用一个stringbuilder拼接，中间要穿插"/",把linkedlist的元素从队头出队。
 * 关于很多////怎么处理成一个，这是整体思路的功劳，先把string字符串以/分隔，
 * 因为很多个//中间是空格，所以没有入栈，最后用一个/拼接，就达成了忽略很多///的目的。
 * bug1:
 * 输入:
 * "/../"
 * 输出
 * "/.."
 * 预期结果
 * "/"
 */
public class StacksimplifyPath71LeetCode {
    public String simplifyPath(String path) {
        LinkedList<String> stack = new LinkedList();
        String [] s= path.split("/");
        for (int i=0;i<s.length;i++){
            if (s[i].equals("..")){
                //解决bug1,把".."略过处理
                if(!stack.isEmpty()){
                    stack.removeLast();
                }
            }
            //注意这里用else if ,不是用if，不然".."也会加入栈中
            else if (!s[i].isEmpty() && !s[i].equals(".")){
                stack.addLast(s[i]);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        //注意这里用比从头遍历的方式访问栈链表
        for (String s1:stack){
            stringBuilder.append("/");
            stringBuilder.append(s1);
        }
        if(stringBuilder.length()==0){
            return "/";
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String path="/a//b////c/d//././/..";
        System.out.println(new StacksimplifyPath71LeetCode().simplifyPath(path));
    }
}
