import java.util.Arrays;

/**
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母
 * 表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单
 * 位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位
 * 时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的最短时间。
 * 示例 1：
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * 注：
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 * @author LemonLin
 * @Description :ArrayleastInterval621LeetCode
 * @date 20.2.11-16:48
 * 思路:参考：https://leetcode-cn.com/problems/task-scheduler/solution/621-ren-wu-diao-du-qi-java-jie-ti-zhu-shi-ying-gai/
 * 题意理解，相同任务类型不能长时间占用CPU，可以理解为不同的任务类型为不同的用户，
 * 然后每个用户一次申请CPU是有时间片长度控制的，同一个用户不能连续两次申请占用
 * CPU，为了防止其他用户出现饥饿问题。需要间隔n的时间片，而这个间隔的时间可以给
 * 其他的用户占用CPU执行。
 *  解题思路：
 *    1、将任务按类型分组，正好A-Z用一个int[26]保存任务类型个数
 *    2、找出个数（count）最大的任务，
 *         如题得到的时间至少为 retCount =（count-1）* (n+1) + 1 ==>
 *      A->X->X->A->X->X->A(X为其他任务或者待命)
 *    3、再排序下一个任务，如果下一个任务B个数和最大任务数一致，
 *         则retCount++ ==> A->B->X->A->B->X->A->B
 *    4、如果空位都插满之后还有任务，那就随便在这些间隔里面插入就可以，因为间隔
 *    长度肯定会大于n，在这种情况下就是任务的总数就是最小所需时间
 */
public class ArrayleastInterval621LeetCode {
    public int leastInterval(char[] tasks, int n) {
        int result =0;
        int[] count = new int[26];
        //1、记录每个任务类型的出现次数
        for (int i=0;i<tasks.length;i++){
            count[tasks[i]-'A']++;
        }
        //2、对数组进行排序，优先排列个数（count）最大的任务，计算现阶段(不算其他
        // 类型任务的情况下)最少的时间
        Arrays.sort(count);
        int maxCount =count[25];
        //计算现阶段(不算其他类型任务的情况下)最少的时间
        //maxCount-1可以当做有几个大任务，N+1中的1表示最多次数的任务，最后一个1
        // 是刚刚前面maxCount减掉的最后的那个任务。
        result=(maxCount-1)*(n+1)+1;

        //3、再排序下一个任务，如果下一个任务B个数和最大任务数一致，
        int i=24;
       while (i>=0&&count[i]==maxCount){
           result++;
           i--;
       }
      //4、比较此刻result与任务长度大小，
        return Math.max(result,tasks.length);
    }

    public static void main(String[] args) {
        char [] tasks =
//                {'A','A','A','B','B','B'}
        {'A','A','A','B','B','B','C','C','D','D'}
                ;
        int n=2;
        System.out.println(new ArrayleastInterval621LeetCode().
                leastInterval(tasks, n));
    }
}
