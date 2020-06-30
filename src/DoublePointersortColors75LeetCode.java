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
 * 本题用了三个指针，首先设置left指针，只记录左侧0的部分，也就是[0,left]这个区间内
 * 都是0，设置right指针，只记录右侧2的部分，也就是[right,num.lenght-1]这个区间内
 * 都是2，既然这两个区间都处理好了，那么剩下的部分自然就是1了。
 * 第三个指针cur用来遍历数组中所有的数，
 * 如果num[cur]==0,那么cur指向的值要和left指向的值交换，然后left和cur都想右移一位。
 * 如果num[cur]==2,那么cur指向的值要和right指向的值交换，然后right左移一位，这里
 * 要注意cur没有移动。
 * 如果num[cur]==1,无需交换，移动cur即可。
 * 为什么==2时，就right移动，cur没有移动，而==0时，left和cur都要移动：
 * 1、因为num[cur]==2时,与right此刻所指向的值交换之后，原来right指向的值还没判断
 * 等于多少还需要判断，所以cur不能移动。
 * 2、当因为num[cur]==0时，首先cur只可能活跃在left<=cur<=right,所以不能是left
 * 加了，cur没加，这样不满足这种情况，其次，num[cur]==0时，要交换时，只可能把1
 * 交换到前面去，不可能交换2到前面去，2全部都被交换到后面去了。所以cur++，只会让
 * cur左边的数要么是0要么是1，不会发生存在漏2的情况。暂时先记住吧
 * 评论区关于遇到2时cur不用++的理解：
 * 因为curr左边的值已经扫描过了，所以curr要++继续扫描下一位，而与p2交换的值，
 * curr未扫描，要停下来扫描一下，所以curr不用++。
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
