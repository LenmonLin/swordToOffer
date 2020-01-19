import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使
 * 数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * 思路：参考LeetCode39，注意对比差别。这里需要一个重复性的剪枝，
 * 可参考LeetCode47，问题是candidates中的元素可能重复，但是结果集不能重复，
 * 如果按照LeetCode39，如果candidates中的元素重复，即使本题candidates
 * 中每个数字只能出现一次，结果很可能重复，比如candidates={1,1,2,3}，target =6
 * 那么{1[0],2,3}与{1[1],2,3},满足结果集中candidates中的每个数字只出现一次，
 * 但是结果集还是重复了，所以要去掉这个分支，剪枝条件可以看具体代码
 * @author LemonLin
 * @Description :BackTrackingcombinationSum2LeetCode40
 * @date 20.1.18-21:55
 */
public class BackTrackingcombinationSum2LeetCode40 {
    ArrayList result = new ArrayList();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //给输入值排序有利于剪枝操作
        Arrays.sort(candidates);
        backTracking(candidates,target,0,new ArrayList<>());
        return result;
    }
    public void  backTracking(int[] candidates, int target, int start,
                              ArrayList<Integer>tempArray){
        if (target<0)return;
        if (target == 0){
            result.add(new ArrayList<>(tempArray));
        }
        //注意这里的i应该是从start开始，而不是从0开始，因为i的初始值是会变的
        for (int i=start;i<candidates.length;i++){
            //剪枝操作，表示下一轮的剩余，如果下一轮的剩余都小于 0 ，就没有必要进行
            // 后面的循环了
            if (target-candidates[i]<0){
                break;
            }
            // 这是本题区别与39题的关键
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            tempArray.add(candidates[i]);
            //注意这里传的是i+1，而不是start，也不是i,因为是不可重复选取的
            backTracking(candidates,target-candidates[i],i+1,tempArray);
            //注意这里移除的不是candidates[i],怎么解释还没想好??
            tempArray.remove(tempArray.size()-1);
        }
    }

    public static void main(String[] args) {
        //1,1,2,5,6,7,10
        int[] input = {10,1,2,7,6,1,5};
        Arrays.sort(input);
        for (int p =0;p<input.length;p++){
            System.out.print(input[p]);
        }
        int target =8;
        List<List<Integer>> lists = new BackTrackingcombinationSum2LeetCode40()
                .combinationSum2(input, target);
        for (int i =0;i<lists.size();i++){
            System.out.println("=====");
            for (int j=0;j<lists.get(i).size();j++){
                System.out.print(lists.get(i).get(j));
            }
        }
    }
}
