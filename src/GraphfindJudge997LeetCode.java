/**
 * 在一个小镇里，按从 1 到 N 标记了 N 个人。传言称，这些人中有一个是小镇上的秘密法官。
 * 如果小镇的法官真的存在，那么：
 * 小镇的法官不相信任何人。
 * 每个人（除了小镇法官外）都信任小镇的法官。
 * 只有一个人同时满足属性 1 和属性 2 。
 * 给定数组 trust，该数组由信任对 trust[i] = [a, b] 组成，表示标记为 a 的人信任标记为 b 的人。
 * 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的标记。否则，返回 -1。
 * 示例 1：
 * 输入：N = 2, trust = [[1,2]]
 * 输出：2
 * 示例 2：
 * 输入：N = 3, trust = [[1,3],[2,3]]
 * 输出：3
 * 示例 3：
 * 输入：N = 3, trust = [[1,3],[2,3],[3,1]]
 * 输出：-1
 * 示例 4：
 * 输入：N = 3, trust = [[1,2],[2,3]]
 * 输出：-1
 * 示例 5：
 * 输入：N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
 * 输出：3
 * 提示：
 * 1 <= N <= 1000
 * trust.length <= 10000
 * trust[i] 是完全不同的
 * trust[i][0] != trust[i][1]
 * 1 <= trust[i][0], trust[i][1] <= N
 * @author LemonLin
 * @Description :GraphfindJudge997LeetCode
 * @date 20.2.14-16:45
 * 思路：参考：https://leetcode-cn.com/problems/find-the-town-judge/solution0
 * /javashi-xian-5ms-by-huant/
* 法官不相信任何人，说明法官不存在出度
* 所有人都信任法官，说明法官的入度为N-1
* 那么法官的出度加入度为N-1
 */
public class GraphfindJudge997LeetCode {
    public int findJudge(int N, int[][] trust) {
        //统计每个顶点的度（出度加入度）
        //注意这里不用下标为0的情况
        int [] count =new int[N+1];
        for(int[] temp:trust){
            //出度计算
            count[temp[0]]--;
            //入度计算
            count[temp[1]]++;
        }
        //记住这里的i从1开始
        for (int i =1;i<=N;i++){
            if (count[i]==N-1){
                return i;
            }
        }
        return -1;
    }
}
