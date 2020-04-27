import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * @author LemonLin
 * @Description :ArrayeraseOverlapIntervals435LeetCode
 * @date 20.4.27-15:48
 * 思路：参考：
 * https://leetcode-cn.com/problems/non-overlapping-intervals/solution/
 * tan-xin-suan-fa-zhi-qu-jian-diao-du-wen-ti-by-labu/
 * 证明参考：
 * https://leetcode-cn.com/problems/non-overlapping-intervals/comments/
 * 贪心算法，按照起点排序：选择结尾最短的，后面才可能连接更多的区间（如果两个区间
 * 有重叠，应该保留结尾小的） 把问题转化为最多能保留多少个区间，使他们互不重复，
 * 则按照终点排序，每个区间的结尾很重要，结尾越小，则后面越有可能容纳更多的区间。
 */
public class ArrayeraseOverlapIntervals435LeetCode {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0||intervals.length==1) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        int result =1;
        int leftEnd = intervals[0][1];
        for (int i =1;i<intervals.length;i++){
            int rightStart = intervals[i][0];
            if(rightStart>=leftEnd){
                result++;
                leftEnd = intervals[i][1];
            }
        }
        return intervals.length-result;
    }

    public static void main(String[] args) {
        int [][] intervals ={{1,2},{1,2}};
        System.out.println(new ArrayeraseOverlapIntervals435LeetCode().
                eraseOverlapIntervals(intervals));
    }
}
