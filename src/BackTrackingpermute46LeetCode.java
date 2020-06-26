import java.util.ArrayList;
import java.util.List;
/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 * 思路：他山之石，可以攻玉https://leetcode-cn.com/problems/permutations/
 * solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
 * @author LemonLin
 * @Description :BackTrackingpermute46LeetCode
 * @date 20.1.18-20:16
 */
public class BackTrackingpermute46LeetCode {
    ArrayList result = new ArrayList();
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length==0)return new ArrayList<>();
        ArrayList temp = new ArrayList();
        //用来标记是否访问过。
        boolean[] used =new boolean[nums.length];
        backTracking(nums,0,temp,used);
        return result;
    }
    //这里把全排列看成是一颗多叉树，depth是表示多叉树到达的层数
    public void backTracking(int[] nums,int depth,ArrayList temp,boolean[] used){
        if (nums.length == depth){
            result.add(new ArrayList<>(temp));
        }
        for (int i=0;i<nums.length;i++){
            if (used[i]!=true){
                temp.add(nums[i]);
                used[i]=true;
                //这里是depth+1，与LeetCode39题多比较一下
                backTracking(nums,depth+1,temp,used);
                used[i]=false;
                temp.remove(temp.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        int [] input ={1,2,3};
        System.out.println(new BackTrackingpermute46LeetCode().permute(input));
    }
}
