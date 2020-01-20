import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *思路：
 * 参考：https://leetcode-cn.com/problems/permutations-ii/solution/
 * hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
 * 这个题要和LeetCode46相比很关键，添加了两步，主要目的是为了去重，
 * 主要是要判断num[i]==num[i-1],如果两个相等，说明如果以其中一个为序列选择，另
 * 一个一定会重复，因为占用的序列位置一定是一样的，所以产生的结果也是一样的，
 * 而为什么要先i>0,如果没有判断，那么后面的num[i-1]就数组越界，所以这个是配套的。
 * used[i-1]==false是因为前面有了一个if (used[i]!=true){的判断，也就是如果
 * used[i-1]==false,同时used[i]==false,就剪枝了话，这个剪的更彻底，因为前面的
 * i>0&&nums[i]==nums[i-1]已经决定了必须剪枝，而不管后面的used[i-1]是否为true，
 * 而如果used[i-1]==false就剪的话，说明used[i-1]还没发生就去掉可能会重复的结果，
 * 这样就没有的结果去掉的更早，遍历有用的全排列做的工作更少，花的时间也更少，效率
 * 更高。
 * @author LemonLin
 * @Description :BackTrackingpermuteUnique47LeetCode
 * @date 20.1.20-23:02
 */
public class BackTrackingpermuteUnique47LeetCode {
    ArrayList result = new ArrayList();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length==0)return new ArrayList<>();
        ArrayList temp = new ArrayList();
        //与LeetCode46相比第一步添加了这个，很关键
        Arrays.sort(nums);
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
                //与LeetCode46相比，添加了第二个变化
                if(i>0&&nums[i]==nums[i-1]&&used[i-1]==false){
                    continue;
                }
                temp.add(nums[i]);
                used[i]=true;
                //这里是depth+1，与LeetCode39题多比较一下
                backTracking(nums,depth+1,temp,used);
                used[i]=false;
                temp.remove(temp.size()-1);
            }
        }
    }
}
