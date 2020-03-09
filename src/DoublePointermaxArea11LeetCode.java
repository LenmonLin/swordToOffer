/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，
 * 垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可
 * 以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最
 * 大值为 49。
 * 示例:
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * 思路：
 * 可以理解这个就是求最大长方形面积，长为x轴两个坐标相减，宽为两个坐标的最短的y坐标。
 * 当然长、宽越大越好，所以考虑设置两个首尾指针。比较两个指针的y大小，最小的那个移动，
 * 比如left=0，right=8，arr[left]<arr[right],所以left++;之后arr[1]>arr[8],所以right--;
 * 如此反复直到首尾指针相遇。这个过程中设置maxArea，不断比较获得maxArea
 * @Description :DoublePointermaxArea11LeetCode
 * @date 19.7.12-23:59
 */
public class DoublePointermaxArea11LeetCode {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int minY = height[left]<height[right]?height[left]:height[right];
        int maxArea =calculateArea(left,right,minY);
        while (left<right){
            if (height[left]<height[right]){
                left++;
            }else {
                right--;
            }
            minY = height[left]<height[right]?height[left]:height[right];
            if (maxArea<calculateArea(left,right,minY)){
                maxArea = calculateArea(left,right,minY);
            }
        }
        return maxArea;
    }
    public int calculateArea(int left ,int right,int minY){
        return (right-left)*minY;
    }

    public static void main(String[] args) {
        int [] test = {1,8,6,2,5,4,8,3,7};
        System.out.println(new DoublePointermaxArea11LeetCode().maxArea(test));
    }
}
