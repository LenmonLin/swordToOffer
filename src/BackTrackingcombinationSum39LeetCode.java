import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中
 * 所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * 思路：题解讲的特别好：https://leetcode-cn.com/problems/combination-sum/
 * solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
 * 思路大概了解了，主要记住回溯算法的模板
 * @author LemonLin
 * @Description :BackTrackingcombinationSum39LeetCode
 * @date 20.1.18-12:04
 */
public class BackTrackingcombinationSum39LeetCode {
    ArrayList result = new ArrayList();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            tempArray.add(candidates[i]);
            //注意这里传的是i，而不是start，也不是i+1,因为是可重复选取的
            backTracking(candidates,target-candidates[i],i,tempArray);
            //注意这里移除的不是candidates[i],怎么解释还没想好??
            tempArray.remove(tempArray.size()-1);
        }
    }
}
