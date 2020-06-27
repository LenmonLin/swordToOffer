/**
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 提示：
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 * @author LemonLin
 * @Description :ArrayfirstMissingPositive41LeetCode
 * @date 20.6.27-11:17
 * 参考：https://leetcode-cn.com/problems/first-missing-positive/solution/tong-pai-xu-python-dai-ma-by-liweiwei1419/
 * 1、遍历一次数组把大于等于1的和小于数组大小的值放到原数组对应位置，用swap交换。
 * 2、然后再遍历一次数组查当前下标是否和值对应，
 *      如果不对应那这个下标就是答案，否则遍历完都没出现那么答案就是数组长度加1
 *  说明：while 循环不会每一次都把数组里面的所有元素都看一遍。如果有一些元素在这
 *  一次的循环中被交换到了它们应该在的位置，那么在后续的遍历中，由于它们已经在
 *  正确的位置上了，代码再执行到它们的时候，就会被跳过。
 * 最极端的一种情况是，在第 1 个位置经过这个 while 就把所有的元素都看了一遍，这
 * 个所有的元素都被放置在它们应该在的位置，那么 for 循环后面的部分的 while
 * 的循环体都不会被执行。平均下来，每个数只需要看一次就可以了，while
 * 循环体被执行很多次的情况不会每次都发生。这样的复杂度分析的方法叫做均摊复杂度分析。
 * bug1:
 * 输入:
 * []
 * 输出
 * 0
 * 预期结果
 * 1
 * bug2:
 * 输入:
 * [1]
 * 输出
 * 1
 * 预期结果
 * 2
 * bug3:
 * 执行结果：
 * 超出时间限制
 * 显示详情
 * 最后执行的输入：
 * [1,1]
 */
public class ArrayfirstMissingPositive41LeetCode {
    public int firstMissingPositive(int[] nums) {
        //解决bug1
        if (nums.length==0){
            return 1;
        }
        for (int i = 0; i <nums.length; i++) {
            /**
             * 这里有一个bug， 只有在 nums[i] 是 [1, len] 之间的数，并且不在自己应该呆
             * 的位置， nums[i] != i + 1 ，才交换，所以注意是因为0不取，所以要向前移动
             * 一位，num[i] -1!=i,
             * 	nums[nums[i] - 1] != nums[i] 是为了解决bug3,它应该呆的位置没有被同伴
             * 	占有（即存在重复值占有）
             */
            while (i!=nums[i]-1&&nums[i]>0&&nums[i]-1<nums.length&&
                    nums[i]!=nums[nums[i]-1]){
                swap(i,nums[i]-1,nums);
            }
        }
        int j=0;
        for (j = 0; j <nums.length ; j++) {
            if (j != nums[j]-1){
                break;
            }
        }
        return j+1;
    }
    private void  swap(int a,int b,int[] nums){
        int temp = nums[a];
        nums[a] = nums [b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        int [] nums={1,1};
        System.out.println(new ArrayfirstMissingPositive41LeetCode().firstMissingPositive(nums));
    }
}
