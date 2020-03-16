import java.util.ArrayList;
import java.util.List;
/**
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而
 * 其他元素出现一次。找到所有出现两次的元素。
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 * 示例：
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * 输出:
 * [2,3]
 * @author LemonLin
 * @Description :ArrayfindDuplicates442LeetCode
 * @date 20.2.9-22:58
 * 思路：参考：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array/
 * solution/c-qiao-miao-zi-xing-ha-xi-by-hou-yong-sheng/
 * 主要难度在于不能开辟新空间。
 * 利用题目中所给信息 1 ≤ a[i] ≤ n ，将出现过的数字作为数组的index（访问元素时需
 * 要减一），如果出现一次的，将该index下的数取相反数，记录此时的状态，如果值为
 * index的数字再出现一次（此时index索引的值为负数），那么这个数字就出现了两次。
 * 比如 数组 [2,2,1] , 第一次更新后 index = 2 索引的第元素取相反数 [2,-2,1], 第二次
 * 更新 index = 2 , 此时数组元素已为负，所以2就是其中的一个结果
 */
public class ArrayfindDuplicates442LeetCode {
    public List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> result = new ArrayList();
        for (int  i =0;i<nums.length;i++){
            //因为值可能被修改为负数，索引只能是正数，所以要取绝对值，减一是为了不数组
            // 越界
            int index = Math.abs(nums[i])-1;
            if(nums[index]<0){
                //要加回1
                result.add(index+1);
            }else {
                nums[index]=-nums[index];
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int [] nums={4,3,2,7,8,2,3,1};
        System.out.println(new ArrayfindDuplicates442LeetCode().findDuplicates(nums));
    }
}
