import java.util.Arrays;
/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * @author LemonLin
 * @Description :ArraynextPermutation31LeetCode
 * @date 20.2.16-21:33
 *思路：关键词：字典序算法，没得说的记住四步算法步骤。
 * 参考：https://blog.csdn.net/GoGleTech/article/details/79892296
 * https://blog.csdn.net/happyrocking/article/details/83619392
 * 关键点：
 * 步骤一：找出逆序区域
 * 比如给定整数12354，如何找到离它最近且大于它的换位数呢？
 * 为了和原数接近，我们需要尽量保持高位不变，低位在最小的范围内变换顺序。
 * 步骤三：把逆序区域改为顺序
 * 互换后的临时结果是12453，倒数第3位已经确定，这时候最后两位仍然是逆序状态。
 * 我们需要把最后两位转变回顺序，以此保证在倒数第3位数值为4的情况下，后两位尽可能小
 */
public class ArraynextPermutation31LeetCode {
    public void nextPermutation(int[] nums) {
        int index1 = find1(nums);
        int index2 = find2(nums,index1);
        if (index2==-1){
            Arrays.sort(nums,0,nums.length);
            return;
        }
        exchange(nums,index1,index2);
        //4.把index1+1到num.length-1的排序，主要函数的使用下标，这里是index1+1，
        // 不是index2+1，这个要注意
        Arrays.sort(nums,index1+1,nums.length);
    }
    //1、从右到左找第一个左邻小于右邻的数的下标，没有就返回-1；
    private int find1(int[] nums){
        for (int i=nums.length-1;i>=0;i--){
            if (i-1>=0&&nums[i-1]<nums[i]){
                return i-1;
            }
        }
        return -1;
    }
    //2、从右到左找第一个比给定下标所代表的值大的数的下标,比如num[]=1236542，给
    // 定下标为2，既要找比num[2]=3大的数的下标，就是num[5]=4,返回下标5,否则返回-1
    private int find2(int[] nums,int index){
        if (index ==-1){
            return -1;
        }
        for (int i=nums.length-1;i>=0;i--){
            if (nums[index]<nums[i]){
                return i;
            }
        }
        return -1;
    }
    //3,交换两个数
    private void exchange(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args) {
        int[] nums ={1,1,5};
        new ArraynextPermutation31LeetCode().nextPermutation(nums);
        for (int i:nums){
            System.out.println(i);
        }
    }
}
