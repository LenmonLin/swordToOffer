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
 * 首先要想到这题需要把输入的一个n，想成是左括号和右括号都是n,所以是要变成两个输入
 * 变量。然后再想以下几个问题：
 * 1、当前左右括号都有大于 00 个可以使用的时候，才产生分支；
 *2、 产生左分支的时候，只看当前是否还有左括号可以使用；
 *3、 产生右分支的时候，还受到左分支的限制，右边剩余可以使用的括号数量一定得在严
 * 格大于左边剩余的数量的时候，才可以产生分支；
 *4、 在左边和右边剩余的括号数都等于 00 的时候结算。
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
 *
 * 关于为什么本题看起来就一个隐藏的外循环，没有内循环，回溯题之前不是总结的时候都有
 * 两重循环，一重是控制树高的循环，一重是控制每个节点分支的循环。为什么本题没有控制
 * 每个节点分支的循环。注意这里本题其实每个节点只有两个分支，也就是类似二叉树，你也
 * 可以用循环，循环判断条件是<2,但是二叉树循环的处理最方便就是直接写两个DFS，手动处理
 * 循环，反正就只有两个，左子树和右子树都写就完事了。这就是表面看上去没有内循环的原因。
 * 其实已经处理了。
 * 还有一个很关键的是为什么没有一般回溯的remove()形式，其实本题是不需要remove的，
 * 但是思维上是有回溯的。因为本题的递归函数用的变量是String，string在回退的时候，就
 * 变成上一层的string，不会像一般回溯那样用的是ArrayList，ArrayList即使回退到树的
 * 上一层时，在刚刚添加的节点值不会消失，会一直在，所以需要remove，而string变量
 * 算是局部变量，回退上一层之后，本层添加的字符串就没了。不需要remove，就有了
 * remove的功能。当然本题也可以用ArrayList做，只是会麻烦一点。
 * 本题的特殊点，在于传入左右括号的个数这两个参数很难想到
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
     * cur     当前递归得到的结果，这里的cur可以理解成一般回溯算法中的path 的ArrayList。
     *             只是这里没有写remove，但是其实有回溯的思维在里面。
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
