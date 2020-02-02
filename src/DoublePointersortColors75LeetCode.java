/**
* 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同
 * 颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
* 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
* 注意:
* 不能使用代码库中的排序函数来解决这道题。
* 示例:
* 输入: [2,0,2,1,1,0]
* 输出: [0,0,1,1,2,2]
* 进阶：
* 一个直观的解决方案是使用计数排序的两趟扫描算法。
* 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
* 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * @author LemonLin
 * @Description :DoublePointersortColors75LeetCode
 * @date 20.2.2-17:36
 * 思路：利用快速排序的思想，设置首尾指针，但是比较值用什么？
 * 很晕，调了一个小时才调通，以为很简单，其实不然，很多细节，这里和快排还是有点不
 * 一样。快排是以首尾指针相遇为循环跳出条件。这里的首指针只走到0的末尾就不走了，
 * 尾指针是从后往前走到最后一个2之后就不走了，考的是cur指针不断遍历，碰到尾指针
 * 跳出。所以这里比较值用的是num[cur]
 */
public class DoublePointersortColors75LeetCode {
    public void sortColors(int[] nums) {
        if (nums==null)return;
        int left=0;
        int right=nums.length-1;
        int cur=0;
        while (cur<=right){
            if (nums[cur]==0){
                swap(nums,left,cur);
                left++;
                cur++;
                continue;
            }
            if(nums[cur]==1){
                cur++;
                continue;
            }
            if (nums[cur]==2){
                swap(nums,cur,right);
                right--;
                continue;
            }
        }
    }
    private void swap(int [] nums,int left,int right){
        int temp=nums[left];
        nums[left]=nums[right];
        nums[right]=temp;
    }

    public static void main(String[] args) {
        int [] nums={2,0,1};
        new DoublePointersortColors75LeetCode().sortColors(nums);
        for (int i=0;i<nums.length;i++){
            System.out.println(nums[i]);
        }
    }
}
