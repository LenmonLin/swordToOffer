import java.util.ArrayList;
import java.util.List;
/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 例如，给出 n = 3，生成结果为：
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * @author LemonLin
 * @Description :DFSgenerateParenthesis22LeetCode
 * @date 20.2.16-19:14
 * 思路：参考https://leetcode-cn.com/problems/generate-parentheses/solution/
 * hui-su-suan-fa-by-liweiwei1419/
 * 网友问题：深度优先搜索题解中每次都使用新的字符串变量什么意思，我每次做回溯发现
 * 对是否要回溯，如何回溯不能理解，就像你写的题解，我不知道每次dfs中的字符串是否会叠加？
 * 题解博主回答：
 * 1、简单的回溯问题一般而言是一个树形问题，例如我在这题中画的树形结构。题目就
 * 是要我们在这个树中做一次搜索（深搜和广搜均可）；
 * 每一个结点表示了问题求解的不同阶段，用不同的变量加以区分。
 * 2、回溯搜索就是对这个树形问题做一次深度优先遍历，而深度优先遍历它有一个回来的
 * 过程（想象一下深搜代码是怎么在树上跑的）；
 * 3、如果全局使用一个变量搜索满足条件的解，从深度深的结点回到深度浅的结点的时
 * 候必须“状态重置”（即回溯）；
 * 4、如果在每一个结点使用不同的状态变量表示求解问题的不同阶段，从深度深的结点
 * 回到深度浅的结点的时候不用“状态重置”（即回溯）；
 * 5、一般而言，对象变量全局使用一个即可，在深搜的过程中必须回溯。基本类型变量
 * 因为在方法传递的过程中是值传递，因此，在深搜的过程中基本类型变量其实就是在复
 * 制，因此不用回溯。
 * 字符串变量很特殊，按照 Python 和 Java 对字符串变量的设计，每一次拼接都生产新
 * 的字符串，因此字符串变量在深搜的过程中，不必回溯。
 * 重点看3,4,5
 */
public class DFSgenerateParenthesis22LeetCode {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<>();
        // 特判
        if (n == 0) {
            return result;
        }
        dfs(n,n,"",result);
        return result;
    }
    /**
     * left     左括号还有几个可以使用
     * right  右括号还有几个可以使用
     * cur     当前递归得到的结果
     *result  结果集
     */
    private void dfs(int left, int right, String cur, ArrayList<String> result){
        if (left==0&&right==0){
            result.add(cur);
            return;
        }
        //这里要注意是左括号小于右括号。不要写错了，
        if (left>0&&left<=right){
            dfs(left-1,right,cur+"(",result);
        }
        if (right>0&&left<=right){
            dfs(left,right-1,cur+")",result);
        }
    }

    public static void main(String[] args) {
        int n=3;
        List<String> list = new DFSgenerateParenthesis22LeetCode().generateParenthesis(n);
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}
