import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 * 示例:
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 * 返回 13。
 * 说明:
 * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n2 。
 * @author LemonLin
 * @Description :HeapkthSmallest378LeetCode
 * @date 20.2.4-21:46
 * 思路：用最大堆，一次遍历就结束了，思路很清晰。
 */
public class HeapkthSmallest378LeetCode {
    public int kthSmallest(int[][] matrix, int k) {
        // PriorityQueue(k);只是预约建议k，size() 是看里面具体有多少个。
        PriorityQueue<Integer> priorityQueue = new PriorityQueue(k,
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                });
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                if (priorityQueue.size() < k) {
                    priorityQueue.add(matrix[row][column]);
                } else {
                    if (priorityQueue.peek() > matrix[row][column]) {
                        priorityQueue.poll();
                        priorityQueue.add(matrix[row][column]);
                    }
                }
            }
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        int k = 8;
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        System.out.println(new HeapkthSmallest378LeetCode().kthSmallest(matrix, k));
    }
}