import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 * 思路：剪枝条件的选择要写好。参考LeetCode78题和LeetCode47题
 * @author LemonLin
 * @Description :BackTrackingsubsetsWithDup90LeetCode
 * @date 20.1.21-11:37
 */
public class BackTrackingsubsetsWithDup90LeetCode {
    ArrayList result = new ArrayList();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums); //排序
        backTracking(nums,new ArrayList(),0);
        return  result;
    }
    public void backTracking(int[] nums, ArrayList temp, int i){
        result.add(new ArrayList<>(temp));
        for (int j=i;j<nums.length;j++){
            //这里j>i其实就是保证j-1不数组越界，因为j-1>=i推出j>=i-1也就是等价于j>i
            if (j>i&&nums[j]==nums[j-1]){
                continue;
            }
            temp.add(nums[j]);
            backTracking(nums,temp,j+1);
            temp.remove(temp.size()-1);
        }
    }
}
