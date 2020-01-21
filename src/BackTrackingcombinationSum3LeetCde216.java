import java.util.ArrayList;
import java.util.List;
/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每
 * 种组合中不存在重复的数字。
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * @author LemonLin
 * @Description :BackTrackingcombinationSum3LeetCde216
 * @date 20.1.21-12:10
 * 思路：这个题和前两个组合题对比，差别就是多了添加递归遍历的限制条件。本题的限制
 * 添加是k,也就是组合中个数只能为k。
 */
public class BackTrackingcombinationSum3LeetCde216 {
    ArrayList result = new ArrayList();
    public List<List<Integer>> combinationSum3(int k, int n) {
        backTracking(k,n,new ArrayList(),1);
        return result;
    }
    public void backTracking(int nums,int target,ArrayList temp,int start){
        //递归终止。
        if (temp.size()>nums||target<0||(temp.size()==nums&&target!=0)){
            return;
        }
        //递归出口
        if (temp.size() == nums&&target==0){
            result.add(new ArrayList<>(temp));
        }
        for (int i =start;i<10;i++){
            //剪枝,这里是target-i，不是target-start，这是个容易出错的bug。要小心。
            if (target-i<0){
                break;
            }
            temp.add(i);
            backTracking(nums,target-i,temp,i+1);
            temp.remove(temp.size()-1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists =
                new BackTrackingcombinationSum3LeetCde216().combinationSum3(3, 9);
        for (int i=0;i<lists.size();i++) {
            System.out.println("====");
            for (int j = 0; j < lists.get(i).size(); j++) {
                System.out.print("hello" + lists.get(i));
            }
        }
    }
}
