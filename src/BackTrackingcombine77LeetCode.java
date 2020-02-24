import java.util.ArrayList;
import java.util.List;
/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 思路：也是递归回溯问题，主要要处理这个k=2,需要把它作为判断递归出口的条件。
 * @author LemonLin
 * @Description :BackTrackingcombine77LeetCode
 * @date 20.1.21-11:57
 */
public class BackTrackingcombine77LeetCode {
    ArrayList result = new ArrayList();
    public List<List<Integer>> combine(int n, int k) {
        backTracking(n,k,new ArrayList(),1);
        return result;
    }
    public void backTracking(int n, int k , ArrayList temp,int index){
        if (temp.size() == k){
            result.add(new ArrayList<>(temp));
            return;
        }
        //关于这里for循环的start的处理是这样的：第二个内部循环for其实是树中每个节点
        // 可以有多少个分支，本题来说，如果取了一个数作为节点值，那么这个节点值的分支
        //肯定是不能包括这个数的，遍历的开头就下移了一位。举个例子来说：n=4,那么递归
        // 数组就为[1,2,3,4],第一个根节点可以遍历这四个数，for循环的index为0，如果选了1，
        //那么第二层节点值就不能选1，必须从index+1开始选。for循环就变成遍历[2,3,4],可
        // 以看出内循环的遍历起点是会变的，遍历终点不会变，for (int i =index;i<=n;i++){
        //这种写法，可以看出i是变量index的赋值，这个是会变的。i<=n，终点是n,是没有变的。
        //这里说的没有变，是每次递归进来，都没有变，都是n。
        for (int i =index;i<=n;i++){
            if (k>temp.size()){
                temp.add(i);
                backTracking(n,k,temp,i+1);
                temp.remove(temp.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        int n=4,k=2;
        System.out.println(new BackTrackingcombine77LeetCode().combine(n, k));
    }
}
