/**
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 * 示例 1:
 * 输入: [10,2]
 * 输出: 210
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * @author LemonLin
 * @Description :SortedlargestNumber179LeetCode
 * @date 20.2.15-20:26
 * 思路：参考：https://leetcode-cn.com/problems/largest-number/comments/
 * 这里有一个很巧妙的使用比较函数，就是比较两个字符串数字s1,s2，判断他们怎么拼接
 * 起来比较大，比如s1s2大还是s2s1大。用比较函数即可，直接拼接即可。
 * bug1:
 * 输入:
 * [0,0]
 * 输出
 * "00"
 * 预期结果
 * "0"
 * 主要思路是通过比较s1s2大还是s2s1大，将小的往后移动，类似冒泡排序，最后的结果数组
 * 就是最小的在最后，最大的在最前。
 */
public class SortedlargestNumber179LeetCode {

    public String largestNumber(int[] nums) {
        //冒泡排序
        for (int i=nums.length-1;i>=0;i--){
            for (int j=0;j<i;j++){
                String s1 = String.valueOf(nums[j])+nums[j+1];
                String s2= String.valueOf(nums[j+1])+nums[j];
                if (compare(s1,s2)==-1){
                    swap(j,j+1,nums);
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        //解决bug1
        boolean isAllZero = true;
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=0){
                isAllZero = false;
            }
            stringBuilder.append(nums[i]);
        }
        if (isAllZero){
            return "0";
        }
        return stringBuilder.toString();
    }

    //交换数组的两个元素的值，一定要传入数组才能是全局变化
    private void swap (int a,int b,int [] nums){
        int temp = nums[a];
        nums[a]=nums[b];
        nums[b] = temp;
    }
    //比较s1s2和s2s1哪个大
    private int compare(String s1,String s2){
        if (s1.length()<s2.length()){
            return -1;
        }else if (s1.length()>s2.length()){
            return 1;
        }else {
            for (int i=0;i<s1.length();i++){
                if (s1.charAt(i)-'0'<s2.charAt(i)-'0'){
                    return -1;
                }else if (s1.charAt(i)-'0'>s2.charAt(i)-'0'){
                    return 1;
                }else {
                    continue;
                }
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        int [] nums ={0,0};
        System.out.println(new SortedlargestNumber179LeetCode().largestNumber(nums));
    }
}
