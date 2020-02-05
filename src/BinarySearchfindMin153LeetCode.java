/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 * 你可以假设数组中不存在重复元素。
 * 示例 1:
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 * @author LemonLin
 * @Description :BinarySearchfindMin153LeetCode
 * @date 20.2.5-11:35
 * 思路：思路类似LeetCode33，这里可以转化一下思路，找最小的，找到最大的，其实就
 *找到了最小的。找到最大比较容易思考。所以我们去找最大的。结果加一即可。
 * 如果3,4,5,1,2 ，nums[left]<nums[mid]，要去右半部分找最大的，所以left = mid;
 * 如果4,5,1,2,3,nums[left]>nums[mid]，要去左半部分找最大的，所以right = mid;
 * bug1：
 * 输入:
 * [1,2]
 * 输出
 * 2
 * 预期结果
 * 1
 */
public class BinarySearchfindMin153LeetCode {
    public int findMin(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int left =0;
        int right = nums.length-1;
        int mid=0;
        //解决bug1
        if (nums[left]<nums[right]){
            return nums[0];
        }
        while (left<right){
            mid = left+(right-left)/2;
            //左半部分递增
            if (nums[left]<nums[mid]){
                //这里要用等号
                    left = mid;
            }else {
                    right = mid;
            }
        }
        if (left==nums.length-1){
            return nums[0];
        }
        return nums[left+1];
    }

    public static void main(String[] args) {
        int [] nums ={1,2};
        System.out.println(new BinarySearchfindMin153LeetCode().findMin(nums));
    }
}
