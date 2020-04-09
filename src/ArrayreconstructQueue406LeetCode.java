import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
* 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个
 * 人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
* 注意：
* 总人数少于1100人。
* 示例
* 输入:
* [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
* 输出:
* [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * @author LemonLin
 * @Description :ArrayreconstructQueue406LeetCode
 * @date 20.4.9-19:16
 * 思路：刚开始一直找规律，想着用什么桶排序之类的。但是发现好像都不太靠谱，这题属于
 * 很难自己想出来答案，但是看题解又觉得比较简单的神奇的题目。
 * https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/
 * 406-gen-ju-shen-gao-zhong-jian-dui-lie-pai-xu-hou-/
 */
public class ArrayreconstructQueue406LeetCode {
    public int[][] reconstructQueue(int[][] people) {
        //先按照第一维降序排列，如果第一维相等，就按照第二维升序排列
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            //这个比较的参数可以看做举例了people数组的前两个元素数组（o1是[7,0]，02是
            // [4,4]），以这两个数组为榜样，如果这两个数组制定了什么规则排序，后面所
            // 有的数组就按照这个规则怎么排序
            public int compare(int[] o1, int[] o2) {
                //如果第一维相等，按照第二维升序排列
                if(o1[0]==o2[0])return o1[1]-o2[1];
                //按照第一维降序排列
                return o2[0]-o1[0];
            }
        });
        //遍历排序后的people数组，按照第二维的数字为下标插入到链表对应下标中。
        // 因为要插入，所以用链表比较快，最后再把链表转换为数组即可
        LinkedList<int []> linkedList = new LinkedList<>();
        for (int i=0;i<people.length;i++){
            linkedList.add(people[i][1],people[i]);
        }
        return linkedList.toArray(new int[linkedList.size()][2]);
    }

    public static void main(String[] args) {
        int [][] people ={
            {7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}
        };
        int[][] ints = new ArrayreconstructQueue406LeetCode().reconstructQueue(people);
        for (int i=0;i<ints.length;i++){
            for(int j=0;j<ints[0].length;j++){
                System.out.println(ints[i][j]);
            }
        }

    }
}
