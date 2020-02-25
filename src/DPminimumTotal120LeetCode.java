import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 例如，给定三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法
 * 会很加分。
 * 思路：
 * 参考：https://www.liwei.party/2018/09/16/leetcode-solution/
 * dynamic-programming-2/#toc-heading-5
 * 方法一：首先，假如用二维数组来存放数字三角形，然后我们用D( r, j) 来表示第r行第 j
 * 个数字(r,j从0开始算)，我们用MinSum(r, j)表示从D(r,j)到底边的各条路径中，最佳路
 * 径的数字之和。因此，此题的最终问题就变成了求 MinSum(0,0)。当我们看到这个题目的
 * 时候，首先想到的就是可以用简单的递归来解题：D(r, j)出发，下一步只能走D(r+1,j)或
 * 者D(r+1, j+1)。故对于N行的三角形，我们可以写出如下的递归式：   
 * if ( r == N)这里N表示最后一行
 * 	MinSum(r,j) = D(r,j)
 * else
 * 	MinSum( r, j) = Min{ MinSum(r＋1,j), MinSum(r+1,j+1) } + D(r,j)
 * 根据上面这个简单的递归式，我们就可以很轻松地写出完整的递归代码：minimumTotal1
 * 这里很关键的是递归是从上往下思考问题，一定要时刻弄清楚你最后要求的是什么东西，这里
 * 推出你最后要求的是MinSum(0,0)，求MinSum(0,0)要求MinSum(1,0)和MinSum(1,1)，
 * 以此类推。
 * 方法二：
 * 就拿第三行数字1来说，当我们计算从第2行的数字3开始的MinSum时会计算出从1开始的
 * MinSum，当我们计算从第二行的数字4开始的MinSum的时候又会计算一次从1开始的
 * MinSum，也就是说有重复计算。这样就浪费了大量的时间。也就是说如果采用递规的方
 * 法，深度遍历每条路径，存在大量重复计算。
 * 接下来，我们就要考虑如何进行改进，我们自然而然就可以想到如果每算出一个MinSum(r,j)
 * 就保存起来，下次用到其值的时候直接取用，则可免去重复计算。那么可以用n方的时间复杂
 * 度完成计算。因为三角形的数字总数是 n(n+1)/2。
 * 根据这个思路，我们就可以将minimumTotal1的代码进行改进，使之成为记忆递归型的动
 * 态规划程序。可以看出记忆递归型的代码和递归代码很相像，只是多了数组保存之前的结果。
 * 两者都是从上往下的思考问题和解决问题。以下引用个人觉得写得很好的一个思维：
 *      "做过 Web 开发的朋友们一定知道，一个 Web 服务的性能瓶颈在数据库访问，那么优化
 * 数据库访问的一个措施就是对一些访问高频但是修改并不频繁的数据使用缓存。借助这个
 * 思路，我们对斐波拉契函数的递归版本也加上缓存，为此，我们引入一个数组。"
 * 方法三：
 * 递归总是需要使用大量堆栈上的空间，很容易造成栈溢出，我们现在就要考虑如何把递归转
 * 换为递推， 我们首先需要计算的是最后一行，因此可以把最后一行直接写出。方法1和方法2
 * 的思维模式是从上往下的思考，我们发现直接分析递归结构，是假设更小的子问题已经解
 * 决给出的实现，思考的路径是“自顶向下”。但有的时候，“自底向上”的思考路径往往更
 * 直接，这就是“动态规划”。同时可以发现用从下往上的写法，代码更加简洁。这也是动态
 * 规划比方法二优势的地方。
 *方法四：
 * 仍然可以继续优化，而这个优化当然是对于空间进行优化，其实完全没必要用二维d[][]
 * 数组存储每一个d[i][j],只要从底层一行行向上递推，那么只要一维数组d[]即可,即只要
 * 存储一行的minSum值就可以。怎么理解呢，比如我最终要求的是第0行的dp[0][0]结果。
 * 这个怎么求，这个要先求第三行的dp[3][0],dp[3][1],dp[3][2]，dp[3][3]，然后根据
 * 第三行的四个数，求第二行的dp[2][0],dp[2][1],dp[2][2].然后在根据第二行的这三个数。
 * 求第一行的dp[1][0],dp[1][1]，最后求出dp[0][0].这个过程中我们发现，dp[3][0]在
 * 求dp[2][0]的时候利用了一下，再往回求dp[1][0]和dp[0][0]的时候都没有用到，因为用的
 * 是dp[2][0],也就是dp[3][0]的结果通过dp[2][0]反馈给上一层，那么自然而然的想，用了
 * 一次之后就没有用了，那么接下来就可以把dp[2][0],dp[2][1],dp[2][2]的值覆盖在dp[3][0],
 * dp[3][1],dp[3][2]的内存空间上，以此类推，就知道，不需要二维数组，一维数组就能
 * 解决需要。也就是通过复用，把二维数组转换为一维数组。
 * @author LemonLin
 * @Description :DPminimumTotal120LeetCode
 * @date 19.8.7-15:57
 */
public class DPminimumTotal120LeetCode {
    public int minimumTotal(List<List<Integer>> triangle) {

   //     return minimumTotal1(triangle);
//        return minimumTotal2(triangle);
//        return minimumTotal3(triangle);
        return minimumTotal4(triangle);
    }
    //  方法一：递归解法
    public int minimumTotal1(List<List<Integer>> triangle) {
        return minimumTotal1array(triangle,0,0);
    }
    public int minimumTotal1array(List<List<Integer>> triangle,int i,int j){
        //递归出口
        if (triangle.size()-1==i){
            return triangle.get(i).get(j);
        }
        int x=minimumTotal1array(triangle,i+1,j);
        int y=minimumTotal1array(triangle,i+1,j+1);
        return Math.min(x,y)+triangle.get(i).get(j);
    }
    //方法二：记忆递归型
    public int minimumTotal2(List<List<Integer>> triangle) {
        //最长行的长度
        int lastetLength=triangle.get(triangle.size()-1).size();
        int [][] d = new int[triangle.size()][lastetLength];
        //二维数组全部初始化为-1
        for(int i=0;i<triangle.size();i++){
            for (int j=0;j<triangle.get(i).size();j++){
                d [i][j]=-1;
            }
        }
        return minimumTotal2array(triangle,0,0,d);
    }
    public int minimumTotal2array(List<List<Integer>> triangle,int i,int j,int[][] d){
        //这里保证了不重复计算，也就是之前有保存了就直接取出即可。
        if( d[i][j] != -1 ){
            return d[i][j];
        }
        if (triangle.size()-1==i){
            d[i][j] = triangle.get(i).get(j);
            //这里的返回很重要，才能保证向上一层返回移动
            return d[i][j];
        }
        int x=minimumTotal2array(triangle,i+1,j,d);
        int y=minimumTotal2array(triangle,i+1,j+1,d);
        d[i][j] = Math.min(x,y)+triangle.get(i).get(j);
        //这里的返回很重要，才能保证向上一层返回移动
        return d[i][j];
    }

    //  方法三：自底向上递推型动态规划，执行时间和minimumTotal2是一样的，只是不需要用到栈，
    //开辟的空间是一样的
    public int minimumTotal3(List<List<Integer>> triangle) {
        int lastetLength=triangle.get(triangle.size()-1).size();
        int [][] d = new int[triangle.size()][lastetLength];
        //先把最后一层的值存到d[][]里面
        for (int p=0;p<triangle.size();p++){
            d[triangle.size()-1][p]=triangle.get(triangle.size()-1).get(p);
        }
        //开始从下往上计算。
        for (int p=triangle.size()-1;p>0;p--){
            for (int q=0;q<triangle.get(p-1).size();q++){
                d[p-1][q]=Math.min(d[p][q],d[p][q+1])+triangle.get(p-1).get(q);
            }
        }
        return d[0][0];
    }

    //优化空间复杂度为O(n)
    public int minimumTotal4(List<List<Integer>> triangle) {
        int lastetLength=triangle.get(triangle.size()-1).size();
        //只需要用到一维数组
        int [] d = new int[lastetLength];
        //先把最后一层的值存到d[][]里面
        for (int p=0;p<triangle.size();p++){
            d[p]=triangle.get(triangle.size()-1).get(p);
        }
        //开始从下往上计算。
        for (int p=triangle.size()-1;p>0;p--){
            for (int q=0;q<triangle.get(p-1).size();q++){
                d[q]=Math.min(d[q],d[q+1])+triangle.get(p-1).get(q);
            }
        }
        return d[0];
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> test1 = Arrays.asList(2);
        List<Integer> test2 = Arrays.asList(3,4);
        List<Integer> test3 = Arrays.asList(6,5,7);
        List<Integer> test4 = Arrays.asList(4,1,8,3);
        triangle.add(test1);
        triangle.add(test2);
        triangle.add(test3);
        triangle.add(test4);
        System.out.println(new DPminimumTotal120LeetCode().minimumTotal(triangle));
    }
}
