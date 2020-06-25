import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，
 * 我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2:
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和
 * 课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 说明:
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，
 * 因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 * @author LemonLin
 * @Description :BFSfindOrder210LeetCode
 * @date 20.5.17-16:44
 * 参考：https://leetcode-cn.com/problems/course-schedule-ii/solution/tuo-bu-pai-xu-shen-du-you-xian-bian-li-python-dai-/
 * https://leetcode-cn.com/problems/course-schedule-ii/solution/bao-mu-shi-ti-jie-tuo-bu-pai-xu-si-lu-zen-yao-yi-2/
 * 要先读懂输入，比如：4, [[1,0],[2,0],[3,1],[3,2]]
 * 首先是有四个节点，其次，[1,0]表示有0到1的路径，也就是1至少有一个入度方向是0到1
 * 所以求某个节点的入度数组为 inDegree[prerequisites[i][0]]++;
 * 求某个节点的出度的邻接节点：
 *      hashMap.get(prerequisites[i][1]).add(prerequisites[i][0]);
 * 主要用BFS的思路：
 * 注意点有：
 * 1、构建入度数组
 * 每一门课都有一个动态变化的入度
 * 课的编号是 0 到 n - 1，让它作为索引，选用 一维数组 存放 入度
 * 遍历 先决条件表 (二维数组)，计算每门课的初始入度
 * 2、构建哈希表
 * 我们选用 哈希表 即【相邻衔接表】来记录 依赖关系
 * map 存什么键值对：
 * 键： 课的编号
 * 值： 依赖它的后续课程 ( list 数组)
 *      比如：修完 2 才能修 4 和 5
 *          2: [4, 5]
 * 也可以用 邻接矩阵，但二维矩阵它有点大
 * 3、BFS 思路
 * queue 队列中始终是【入度为 0 的课】在里面流动
 * 选择一门课，就让它 出列，同时 查看哈希表，看它 对应哪些后续课
 * 将这些后续课的 入度 - 1，如果有 减至 0 的，就将它 推入 queue
 * 不再有新的入度 0 的课入列 时，此时 queue 为空，退出循环
 */
public class BFSfindOrder210LeetCode {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        if (numCourses <= 0) {
            return new int[0];
        }

        //记录入度的数组
        int [] inDegree = new int[numCourses];
        //记录后续邻接节点关系的HashMap
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        //把数据记录在入度数组和HashMap中
        for(int i=0;i<prerequisites.length;i++){
            inDegree[prerequisites[i][0]]++;
            if (hashMap.containsKey(prerequisites[i][1])){
                hashMap.get(prerequisites[i][1]).add(prerequisites[i][0]);
            }else{
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(prerequisites[i][0]);
                hashMap.put(prerequisites[i][1],temp);
            }
        }
        //以下是BFS的算法主体
        //先入队入度为0 的节点
        LinkedList<Integer> queue = new LinkedList<>();
        //存放输出结果
        ArrayList<Integer> result = new ArrayList<>();
        for (int i =0;i<inDegree.length;i++){
            if(inDegree[i]==0){
                queue.addLast(i);
            }
        }
        //出队，同时查hashmap，如果后续节点的入度为0，则入队
        while (!queue.isEmpty()){
            Integer node = queue.removeFirst();
            result.add(node);
            if(hashMap.containsKey(node)&&hashMap.get(node).size()!=0){
                for(int num:hashMap.get(node)){
                    inDegree[num]--;
                    if (inDegree[num]==0){
                        queue.addLast(num);
                    }
                }
            }
        }
        int [] output = new int[result.size()];
        for (int k=0;k<result.size();k++){
            output[k] = result.get(k);
        }
        //如果拓扑排序存入的数字等于课程总个数，也就是将所有的课程并入了拓扑排序，
        // 就说明没有环，没有拉下的节点，就可以输出结果。
        if (result.size()==numCourses){
            return output;
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int numCourses=2;
        int[][] prerequisites={{1,0}};
        int[] order = new BFSfindOrder210LeetCode().findOrder(numCourses, prerequisites);
        for(int i:order){
            System.out.println(i);
        }
    }
}
