/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在
 * 数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * @author LemonLin
 * @Description :BinarySearchsearchRange34LeetCode
 * @date 20.2.5-11:32
 * 思路：自然而然的想到了二分法。但是思路很简单，细节是魔鬼。此题关键是：
 * 寻找左侧边界、寻找右侧边界。
 * 参考大神之作：
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-
 * sorted-array/solution/er-fen-cha-zhao-suan-fa-xi-jie-xiang-jie-by-labula/
 * bug1:
 * [1]
 * 0
 * 数组越界.
 * bug2:
 * [2,2]
 * 3
 * 数组越界
 * 1、搞清楚
 *   if()
 *   if()
 *   与：
 *   if()
 *   else if()
 *   的区别：在java当中，如果两个if判断同级，if判断条件的代码都会去判断，不管前面的
 *   if判断条件是否成立，都会去判断执行下一个if的代码；如果是if()else if(){}的话，当
 *   前面的if判断条件成立的话，就不会再去判断else if的条件，有多个else if的时候，会去
 *   挨个进行判断，只要有一个符合条件，后面的else if都不会再去判断，当前面的判断条件
 *   都没成立，并且结尾有一个else，就会执行最后else中的代码。
 *   也就是不管前面if满不满足添加，对于if来说都会去判断，但是对于else if来说，只要满足
 *   其中一个，else if或者if，那么剩下的else if和else都不会执行了。
 *   2、二分查找的框架：
 *   int binarySearch(int[] nums, int target) {
 *     int left = 0, right = ...;
 *     while(...) {
 *         int mid = left + (right - left) / 2;
 *         if (nums[mid] == target) {
 *             ...
 *         } else if (nums[mid] < target) {
 *             left = ...
 *         } else if (nums[mid] > target) {
 *             right = ...
 *         }
 *     }
 *     return ...;
 * }
* 其中 ... 标记的部分，就是可能出现细节问题的地方。
 * 代码中 left + (right - left) / 2 就和 (left + right) / 2 的结果相同，但是有效防止了
 * left 和 right 太大直接相加导致溢出。
 *3、寻找一个数：
 * int binarySearch(int[] nums, int target) {
 *     int left = 0;
 *     int right = nums.length - 1; // 注意
 *     while(left <= right) {
 *         int mid = left + (right - left) / 2;
 *         if(nums[mid] == target)
 *             return mid;
 *         else if (nums[mid] < target)
 *             left = mid + 1; // 注意
 *         else if (nums[mid] > target)
 *             right = mid - 1; // 注意
 *     }
 *     return -1;
 * }
 * 4、寻找左侧边界的二分搜索：
 * int left_bound(int[] nums, int target) {
 *     int left = 0, right = nums.length - 1;
 *     // 搜索区间为 [left, right]
 *     while (left <= right) {
 *         int mid = left + (right - left) / 2;
 *         if (nums[mid] < target) {
 *             // 搜索区间变为 [mid+1, right]
 *             left = mid + 1;
 *         } else if (nums[mid] > target) {
 *             // 搜索区间变为 [left, mid-1]
 *             right = mid - 1;
 *         } else if (nums[mid] == target) {
 *             // 收缩右侧边界
 *             right = mid - 1;
 *         }
 *     }
 *     // 检查出界情况
 *     if (left >= nums.length || nums[left] != target)
 *         return -1;
 *     return left;
 * }
 *5、寻找右侧边界的二分查找
 * int right_bound(int[] nums, int target) {
 *     int left = 0, right = nums.length - 1;
 *     while (left <= right) {
 *         int mid = left + (right - left) / 2;
 *         if (nums[mid] < target) {
 *             left = mid + 1;
 *         } else if (nums[mid] > target) {
 *             right = mid - 1;
 *         } else if (nums[mid] == target) {
 *             // 这里改成收缩左侧边界即可
 *             left = mid + 1;
 *         }
 *     }
 *     // 这里改为检查 right 越界的情况，
 *     if (right < 0 || nums[right] != target)
 *         return -1;
 *     return right;
 * }
 * 总结上面三种代码套路：
 * 1、right 需要写成nums.length - 1; 配套的while循环条件必须写<=,不能写成<
 * 2、nums[mid] < target或者nums[mid]>target内部的写法都一样，只有
 * nums[mid] == target内部的写法需要根据所求的不同，写法不一样：
 * 求一个数：
 * A、return mid;
 * B、求左边界：// 收缩右侧边界 right = mid - 1;
 * C、求右边界：//这里改成收缩左侧边界即可 left = mid + 1;
 * 3、求左右边界需要判断越界问题：
 *      求左边界：因为是向左压缩的区间，while 的退出条件是 left == right + 1，所以
 *      当 target 比 nums 中所有元素都大时，会出现right<left的情况。right最大能到
 *      nums.length-1,那么left就可能到nums.length,发生数组越界。
 *   // 检查出界情况
 *  if (left >= nums.length || nums[left] != target)
 *      return -1;
 *      求右边界：
 *  // 这里改为检查 right 越界的情况，当 target 比所有元素都小时，while 的退出条件是
 *  left == right + 1，会发生right<left,left最小能到0，right 会被减到 -1，所以需要在
 *  最后防止越界：
 * if (right < 0 || nums[right] != target)
 *     return -1;
 *     总结就是：求左边界,收缩右侧边界 right = mid - 1;左边肯定没问题，最右可能出现问题。
 *                     求右边界,收缩左侧边界left = mid + 1;右边肯定没问题，最左边可能出问题。
 */
public class BinarySearchsearchRange34LeetCode {
    public int[] searchRange(int[] nums, int target) {
        int [] result = {-1,-1};
        if (nums.length==0){
            return result;
        }
        int left =0;
        int right =nums.length;
        int mid=0;
        //求左边界
        while (left<right){
            mid=(left+right)/2;
            if (nums[mid]<target){
                left = mid+1;
            }else if (nums[mid]>target){
                right = mid;
            }else if (nums[mid]==target){
                //设置右边界，让mid继续向左压缩
                right = mid;
            }
        }
        //解决bug1：
        if (left<nums.length&&nums[left]==target){
            result[0]=left;
        }else {
            result[0] =-1;
        }
        left=0;
        right=nums.length;
        //求右边界
        while (left<right){
            mid=(left+right)/2;
            if (nums[mid]<target){
                left = mid+1;
            }else if (nums[mid]>target){
                right = mid;
            }else if (nums[mid]==target){
                //设置左边界，让mid继续向右压缩
                left = mid+1;
            }
        }
        //解决bug2：
        if (left-1>=0&&nums[left-1]==target){
            result[1]=left-1;
        }else {
            result[1] =-1;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums ={2,2};
        int target = 3;

        int[] ints = new BinarySearchsearchRange34LeetCode().searchRange(nums, target);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }
}
