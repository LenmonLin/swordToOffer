/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * @author LemonLin
 * @Description :ArraymoveZeroes283LeetCode
 * @date 20.3.29-20:03
 * 思路：另外设置一个指针notZero，记录非零元素的下标存放位置，最后把0补到notZero
 * 后即可。
 */
public class ArraymoveZeroes283LeetCode {
    public void moveZeroes(int[] nums) {
        int notZero=0;
        int i=0;
        while (i<nums.length){
            if (nums[i]!=0){
                nums[notZero] = nums[i];
                notZero++;
            }
            i++;
        }
        for (i=notZero;i<nums.length;i++){
            nums[i] =0;
        }
    }

    public static void main(String[] args) {
        int [] nums ={0,1,0,3,12};
        new ArraymoveZeroes283LeetCode().moveZeroes(nums);
        for (int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }
}
