import java.util.ArrayList;
import java.util.List;
/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * 思路：回溯算法中要解决的和全排列不一样，选子集时，个数是0,1,2,3都可以是子集，
 * 递归出口怎么设置是个问题。同时子集的长度怎么控制也是个问题。
 * 答：这里没有递归出口，每次都把结果添加到结果集中
 * 还有j=i这个操作要仔细体会，然后 递归函数传入的是j+1;j=i这个操作可以理解为
 * i=2时，输出1,2,3;
 * i=1时，
 * @author LemonLin
 * @Description :BackTrackingsubsets78LeetCode
 * @date 20.1.21-10:16
 */
public class BackTrackingsubsets78LeetCode {
    ArrayList result = new ArrayList();
    public List<List<Integer>> subsets(int[] nums) {
        backTracking(nums,new ArrayList(),0);
        return  result;
    }
    public void backTracking(int[] nums,ArrayList temp,int inputIndex){
        result.add(new ArrayList<>(temp));
        for (int j=inputIndex;j<nums.length;j++){
            temp.add(nums[j]);
            backTracking(nums,temp,j+1);
            temp.remove(temp.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = new BackTrackingsubsets78LeetCode().subsets(nums);
        for(int i=0;i<subsets.size();i++){
            System.out.println("====");
            for (int j=0;j<subsets.get(i).size();j++){
                System.out.print(subsets.get(i).get(j));
            }
        }
    }
}
