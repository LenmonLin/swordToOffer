/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后
 * 能接多少雨水。
 *图片：https://leetcode-cn.com/classic/problems/trapping-rain-water/description/
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单
 * 位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * @author LemonLin
 * @Description :DoublePointertrap42LeetCode
 * @date 20.3.3-11:50
 * 思路：参考：https://leetcode-cn.com/problems/trapping-rain-water/solution/
 * tu-jie-jie-yu-shui-dong-tai-gui-hua-he-shuang-zhi-/
 * 解法1：暴力解法
 * 注意思路就是根据木桶效应，找height[i]左右两边最矮的那个，然后用这个最矮的木桶减
 * 去当前柱子高度，就能得到当前柱子能放多少水，这个减去操作才是本题的精华和不容易
 * 想到的点。
 * 解法2：记忆化搜索
 * 解法2和解法1总思路是一样的，不过解法2是把leftMax和rightMax存储起来了，有点
 * 动态规划的意思。空间换时间。
 * 解法3：双指针。
 * 观察发现，其实不需要保存全部的leftMax和rightMax，只要遍历到哪一个height[i]，用
 * 了那个时候的leftMax和rightMax，遍历下一个的时候再用此时的leftMax和rightMax即
 * 可，之前的leftMax和rightMax可以丢弃，无需保存。
 */
public class DoublePointertrap42LeetCode {
    public int trap(int[] height) {
//        return trap1(height);
//        return trap2(height);
        return trap3(height);
    }
    //解法1：暴力解法
    public int trap1(int[] height){
        int result =0;
        int leftMax=0;
        int rightMax=0;
        for (int i=0;i<height.length;i++){
            leftMax =0;
            rightMax =0;
            //从左往右找i右边最高的柱子,为什么这里是从左往右找右边最高的柱子，而解法2
            // 从左往右是找左边最高的柱子。因为注意看，下标j是从i开始，也就是遍历范围
            // 是i的右边，所以找的也是右边最高的柱子，而解法2，下标i是从最开始的地方0
            // 开始的，因为0已经填过了，所以从1开始。所以相当于解法2是从左到右都遍历
            // 了，所以求的是rightMax
            for (int j =i;j<height.length;j++){
                rightMax = Math.max(rightMax,height[j]);
            }
            //从右往左找i坐标最高的柱子
            for (int j =i;j>=0;j--){
                leftMax = Math.max(leftMax,height[j]);
            }
            result += Math.min(leftMax,rightMax)-height[i];
        }
        return result;
    }
    //解法2：记忆化搜索
    public int trap2(int[] height){
        int result =0;
        if (height.length==0)return result;
        int [] leftMax= new int[height.length];
        int [] rightMax= new int[height.length];
        leftMax[0] = height[0];
        rightMax[height.length-1] = height[height.length-1];
        //从左往右找左边最大的，因为上面下标0已经填了，而且循环中有i-1,，怕越界，所以
        // 下标从1开始
        for (int i=1;i<height.length;i++){
            leftMax[i]=Math.max(leftMax[i-1],height[i]);
        }
        //从右往左找右边最大的，因为上面下标length-1已经填了，而且循环中有i+1,怕数组
        // 越界，所以下标从length-2开始。
        for (int i=height.length-2;i>=0;i--){
            rightMax[i]=Math.max(rightMax[i+1],height[i]);
        }
        for (int i=0;i<height.length;i++){
            result += Math.min(leftMax[i],rightMax[i])-height[i];
        }
        return result;
    }
    //解法3：双指针。
    public int trap3(int[] height){
        int result =0;
        if (height.length==0)return result;
        int right =height.length-1;
        int left= 0;
        int leftMax = height[left];
        int rightMax = height[right];
        while (left<right){
            leftMax = Math.max(leftMax,height[left]);
            rightMax = Math.max(rightMax,height[right]);
            if (leftMax<rightMax){
                result+=leftMax-height[left];
                left++;
            }else {
                result+=rightMax-height[right];
                right--;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int [] height ={0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(new DoublePointertrap42LeetCode().trap(height));
    }
}
