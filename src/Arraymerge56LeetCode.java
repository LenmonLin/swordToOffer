import java.util.ArrayList;
import java.util.Arrays;
/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * @author LemonLin
 * @Description :Arraymerge56LeetCode
 * @date 20.2.9-19:05
 * 思路：遍历每个数组，比较[1,3]中的3与[2,6]中的2的大小。
 * bug1：
 * 输入:
 * [[1,4],[0,4]]
 * 输出
 * [[1,4]]
 * 预期结果
 * [[0,4]]
 * bug2:
 * 输入:
 * [[1,4],[0,0]]
 * 输出
 * [[0,4]]
 * 预期结果
 * [[0,0],[1,4]]
 * bug3:
 * 输入:
 * [[1,4],[1,4]]
 * 输出
 * [[1,4],[1,4]]
 * 预期结果
 * [[1,4]]
 * bug4:
 * 输入:
 * [[1,4],[0,1]]
 * 输出
 * [[1,4],[0,1]]
 * 预期结果
 * [[0,4]]
 * bug5:
 * 输入:
 * [[1,4],[0,2],[3,5]]
 * 输出
 * [[0,4],[3,5]]
 * 预期结果
 * [[0,5]]
 * bug6:
 * 输入:
 * [[2,3],[4,5],[6,7],[8,9],[1,10]]
 * 输出
 * [[1,10],[4,5],[6,7],[8,9]]
 * 预期结果
 * [[1,10]]
 * 思考2参考:https://leetcode-cn.com/problems/merge-intervals/solution/pai-xu-by-powcai/
 * 自己写的太复杂了，思路也不够清晰，不利用记忆。
 * 1、先按首位置进行排序;
 * 2、接下来,如何判断两个区间是否重叠呢?比如 a = [1,4],b = [2,3]
 * 当 a[1] >= b[0] 说明两个区间有重叠.但是如何把这个区间找出来呢?
 * 左边位置一定是确定，就是 a[0]，因为进行了首位置排序，而右边位置是 max(a[1], b[1])
 * 这里主要是对while的运用需要细想一下。所以,我们就能找出整个区间为:[1,4]
 * 所以这里有两个循环，第一个循环是循环遍历输入的二维数组。第二个循环是控制是否有重叠。
 */
public class Arraymerge56LeetCode {

    public int[][] merge2(int[][] intervals) {
        //特殊情况，排除
        if (intervals==null||intervals.length==0||intervals.length==1){
            return intervals;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        //这里泛型中药填int[] 才才可以下方将ArrayList方便转换为int[][]
        ArrayList<int []> result =  new ArrayList();
        int i =0;
        //第一个循环是循环遍历输入的二维数组。
        while (i<intervals.length){
            //重叠区间的左边位置确定好了
            int left = intervals[i][0];
            //临时记录重叠区间的右边位置
            int right = intervals[i][1];
            //这里是i<intervals.length-1是怕i+1越界， 判断所在行的right和下一行的left
            // 大小，只有所在行的right大于下一行的left，才能进行合并。
            while (i<intervals.length-1&&intervals[i+1][0]<=right){
                //这里不要写成right = intervals[i+1][1];，因为可能出现a = [1,4],b = [2,3]
                // 的情况，也就是4都比后面区间的右边位置大
                right = Math.max(right,intervals[i+1][1]);
                i++;
            }
            result.add(new int[]{left,right});
            //这个容易遗漏
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }

    public int[][] merge(int[][] intervals) {
        if (intervals==null||intervals.length==0||intervals.length==1){
            return intervals;
        }
        //这一点很重要，解决了上面大多数bug，对二维数组中的第一个数进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        ArrayList result =  new ArrayList();
        int i =0;
        while (i<intervals.length){
            if (intervals[i][1]>intervals[i+1][1]&&intervals[i][0]>intervals[i+1][1]||
            intervals[i][1]<intervals[i+1][0]){
                result.add(intervals[i]);
            }else {
                int start = Integer.MAX_VALUE;
                int end = intervals[i][1];
                while (i+1<intervals.length&&end>=intervals[i+1][0]){
                    start = Math.min(start,Math.min(intervals[i][0],intervals[i+1][0]));
                    end = Math.max(end,Math.max(intervals[i][1],intervals[i+1][1]));
                    i++;
                }
                int [] temp ={start,end};
                result.add(temp);
            }
            i++;
            if (i==intervals.length-1){
                result.add(intervals[intervals.length-1]);
                break;
            }
        }
        int[][] outPut = (int[][])result.toArray(new int[result.size()][]);
        return outPut;
    }

    public static void main(String[] args) {
        int[][] intervals={
//                {1,10},{2,3},{4,5},{6,7},{8,9}
//                {1,4},{0,2},{3,5}
//                {1,3},{2,6},{8,12},{11,18}
//                {1,4}, {0,1}
                {1,4},{1,4}
        };
        int[][] merge = new Arraymerge56LeetCode().merge(intervals);
        for(int i=0;i<merge.length;i++){
            for (int j=0;j<merge[0].length;j++){
                System.out.print(merge[i][j]);
            }
            System.out.println();
        }
    }
}
