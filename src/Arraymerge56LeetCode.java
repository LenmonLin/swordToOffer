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
 */
public class Arraymerge56LeetCode {
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
