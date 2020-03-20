import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8
 * 个数字，则最小的4个数字是1、2、3、4。
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * @author LemonLin
 * @Description :面试题40最小的k个数
 * @date 20.3.20-7:48
 * 思路:遍历数组，用大顶堆存入K个数。如果大顶堆没有到K个数的，继续存入，如果到K个
 * 数的了，就需要和大顶堆比较一下，比大顶堆堆顶元素小的，存入大顶堆，再重新调整。
 * 比大顶堆堆顶元素大的，略过不处理。PriorityQueue默认情况下是最小堆，需要传入比较
 * 器，才能转换为大顶堆。
 */
public class 面试题40最小的k个数 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr==null||k<=0)return new int[0];
        PriorityQueue<Integer>  maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int i=0;i<arr.length;i++){
            if (maxHeap.size()<k){
                maxHeap.add(arr[i]);
            }else if (maxHeap.peek()>arr[i]){
                //先把堆顶元素移除
                maxHeap.poll();
                //再存入比堆顶元素小的数
                maxHeap.add(arr[i]);
            }
        }
        int [] result = new int[k];
        for (int i=0;i<k;i++){
            if (maxHeap.size()>0){
                result[i] = maxHeap.poll();
            }
        }
        return  result;
    }

    public static void main(String[] args) {
        int [] text ={1};
        int k =1;
        int[] leastNumbers = new 面试题40最小的k个数().getLeastNumbers(text, k);
        for(int i=0;i<leastNumbers.length;i++){
            System.out.println(leastNumbers[i]);
        }
    }
}
