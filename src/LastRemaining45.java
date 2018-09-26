import java.util.LinkedList;

/**
 * @author LemonLin
 * @Description :LastRemaining45
 * @date 2018/9/4-20:45
 *
 * 题目：圆圈中最后剩下的数字（约瑟夫问题）
 *
 * 题目：0, 1, … , n-1 这 n 个数字排成一个圈圈，
 * 从数字 0 开始每次从圆圏里删除第 m 个数字，下一次，从m+1开始数，数第
 * m个删除，以此类推。求出这个圈圈里剩下的最后一个数字。
 *
 * 问题，需要解决如何循环移除的问题？用%(list.size())
 *
 *需要解决如何进行下一步继续循环的问题，比如第一遍数到0-M,如何第二遍从m+1开始数第m个数:
 *
 * index=-1;
 * while(size>1){
 *     index=(index+m)%size();
 *     index--;
 * }
 * 原因，第一次取m-1,
 * 第二次是index--是因为size也减小了一个一；
 * 循环是用%size();
 *
 *
 * 解题思路：
 * 解法一：利用环形链表处理问题，这里用LinkedList的性能比ArrayList的性能好，
 * 因为我们不断的要删除List中的某个元素。如果用ArrayList里面有大量的System.arraycopy。
 *
 *
 * 解法二：开O(n)的辅助空间，建造一个boolean数组，大小为总共数字的个数和，默认boolean
 * 数组的每个元素都是false，数到谁的时候，将数组对对应index的值改为true。
 * 知道最后只剩下一个元素值为false，得到此index。
 */
public class LastRemaining45 {


    /**
     *
     * @param n 表示圆圈最大的数
     * @param m 表示从第几个数开始删除
     * @return 剩下的最后一个数
     */
    public int LastRemaining_Solution(int n, int m) {


        /**
         * 异常输入的判断
         */
        if (n==0||m==0){
            return -1;
        }

        LinkedList<Integer>  linkedList = new LinkedList<>();

        for (int i=0;i<n;i++){
            linkedList.add(i);
        }

        /*
        for(Iterator iter = linkedList.iterator(); iter.hasNext();) {
            System.out.println("原始linkedList==="+iter.next());;
        }*/

        int index =-1;
        while (linkedList.size()>1){

            index=(index+m)%(linkedList.size());


            linkedList.remove(index);
        /*System.out.println("移除过程中的linkedList==="+m);*/
            index--;
        }

/*    for(Iterator iter = linkedList.iterator(); iter.hasNext();) {
            System.out.println("移除之后linkedList==="+iter.next());
        }*/

        return linkedList.get(0);
    }

    public static void main(String[] args) {

        LastRemaining45 lastRemaining45 = new LastRemaining45();

        int test = lastRemaining45.LastRemaining_Solution(5, 3);
        System.out.println(test);
    }
}
