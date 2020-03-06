import java.util.ArrayList;
/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * 示例 1：
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * 限制：
 * 1 <= target <= 10^5
 * @author LemonLin
 * @Description :面试题57II和为s的连续正数序列
 * @date 20.3.6-9:57
 * 思路：双重遍历
 * 思路修正：最开始的想法是双重遍历，但是这样很有问题，效率低，这里考虑用类似滑动窗口
 * 的办法，把首尾指针独立出来用while控制，不用for，while可以控制合适++，
 * 如果sum>target ,那么右边太大了，不能再加了，左边收缩一下
 * 如果sum<target,那么右边太小了，右边需要再加。
 */
public class 面试题57II和为s的连续正数序列 {
    public int[][] findContinuousSequence(int target) {
        if (target<=2)return null;
        int sum = 1;
        ArrayList<int[]> result = new ArrayList<>();
        int left=1;
        int right =left+1;
        //因为不可能出现大于target一般的数两个相加会等于target，一定大于target，所以
        //之后的数没必要继续遍历了。
        while (left<=target/2+1){
            if (sum<target){
                sum += right;
                right++;
            }else if (sum>target){
                sum-=left;
                left++;
            }else if (target ==sum){
                int[] temp = new int[right-left];
                //因为后期还需要用到left，left经过for循环就变量，所以需要用leftCopy保存
                // 一个原值。
                int leftCopy =left;
                for (int i =0;i<right-left;i++){
                    temp[i] = leftCopy;
                    leftCopy++;
                }
                result.add(temp);
                //等于之后，需要破坏平衡，以便继续遍历。
                sum -=left;
                left++;
            }
        }
        int [][]out = new int[result.size()][];
        return result.toArray(out);
    }

    public static void main(String[] args) {
        int target =3;
        int[][] continuousSequence = new 面试题57II和为s的连续正数序列().
                findContinuousSequence(target);
        for (int i=0;i<continuousSequence.length;i++){
            for (int j =0;j<continuousSequence[i].length;j++){
                System.out.print(continuousSequence[i][j]);
            }
            System.out.println();
        }
    }
}
