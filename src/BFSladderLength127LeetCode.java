import java.util.*;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到
 * endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 * @author LemonLin
 * @Description :BFSladderLength127LeetCode
 * @date 20.1.16-19:41
 * 思路：这题分为两部分，第一部分怎么寻找一个单词改变一个字符后是否在wordList中。
 * 困难，看了很久没看懂。
 * 参考：https://www.dazhuanlan.com/2019/12/10/5dee8143252f0/
 * 解法一：BFS
 * 可以将每个单词看成图中的一个结点，如果两个单词之间可以互相转化，那么这两个单词
 * 所在的结点就有一条无向边。问题就转为从起点到终点的的最短路径。由于边权都相等，
 * 可以用 BFS 求最短路。
 * 这里分成两个函数，一个BFS，一个是匹配函数，只有一个字母相同的，就匹配。
 * bug1:
 * 输入：
 * "a"
 * "c"
 * ["a","b","c"]
 * 输出：
 * 3
 * 预期：
 * 2
 * bug2:
* "place"
* ["peale","wilts","place","fetch","purer","pooch","peace","poach","berra","teach","rheum","peach"]
* 输出
* 6
* 预期结果
* 4
 * 问题：为啥这个求解结果是最短的呢？
 * 这里介绍一下为啥求得就是最短路径，本题实际是用广度优先遍历求无权图最短路径的问题。
 * 和广度优先遍历的求解方式有关：比如下面这张图：(画的不好)简单描述一下，
 * a可以到b、c
 * b可以到e
 * c可以到d
 * d可以到e
 * 求a到e的最短路径。用广度优先遍历解法如下：
 * 从a出发，遍历b,c.此时路径长度加1等于2；
 * 从b出发到达e，就返回结果3（此刻为什么3就是最短的）
 * 因为如果从c出发，下一步是d,还没到，长度为3了。
 * 也就是说，广度优先遍历只要遇到了目标对象，这个时候跳出代码了就是最短无权路径。因
 * 为其他更长的路径还没遍历到，也没有机会遍历到就被终止代码了。
 *   a---->b------------>e
 *   |                             ^
 *   ----->c----->d-------|
 *优化参考：
 * https://leetcode-cn.com/problems/word-ladder/solution/
 * suan-fa-shi-xian-he-you-hua-javashuang-xiang-bfs23/
 */
public class BFSladderLength127LeetCode {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        int level =0;
//        level = bfs2(beginWord, endWord, wordList, level);
        level = bfs3(beginWord, endWord, wordList);
        return level;
    }
    private  boolean isMatch(String a,String b){
        int cnt =0;
        for (int i=0;i<a.length();i++){
            if (a.charAt(i)!=b.charAt(i)){
                cnt++;
            }
            if (cnt>=2){
                return false;
            }
        }
        if (cnt==1){
            return true;
        }
        return false;
    }
    //第四版：双向广度优先遍历，从节点更少的一段开始遍历
    private int bfs4(String beginWord, String endWord, List<String> wordList){
        return 0;
    }
    //第三版：双向广度优先遍历
    //执行用时 :361 ms, 在所有 Java 提交中击败了29.12%的用户
    //内存消耗 :39.5 MB, 在所有 Java 提交中击败了63.47%的用户
    private int bfs3(String beginWord, String endWord, List<String> wordList){
        LinkedList<String> queue1 = new LinkedList<>();
        LinkedList<String> queue2 = new LinkedList<>();
        boolean[] visited1 = new boolean[wordList.size()];
        boolean[] visited2 = new boolean[wordList.size()];
        queue1.addLast(beginWord);
        queue2.addLast(endWord);
        int idx1 = wordList.indexOf(beginWord);
        if (idx1 != -1) {
            visited1[idx1] = true;
        }
        int idx2 = wordList.indexOf(endWord);
        if (idx2 != -1) {
            visited2[idx2] = true;
        }
        int count1 =0;
        int count2 =0;
        while (!queue1.isEmpty()&&!queue2.isEmpty()){
            count1++;
            int size1 = queue1.size();
            for (int p=0;p<size1;p++){
                String  temp = queue1.removeFirst();
                for (int i=0;i<wordList.size();i++){
                if(visited1[i]==true){
                    continue;
                }
                if (!isMatch(temp,wordList.get(i))){
                    continue;
                }
                //注意这里是双向循环的精髓，是visit2，不是visit1
                if(visited2[i]==true){
                    return count1+count2+1;
                }
                queue1.addLast(wordList.get(i));
                visited1[i]=true;
                }
            }
            //count2++只能写在上述for循环之后，不能写在之前，否则会有错误
            count2++;
            int size2 = queue2.size();
            for (int q=0;q<size2;q++) {
                String temp = queue2.removeFirst() ;
                for (int j = 0; j < wordList.size(); j++) {
                    if (visited2[j] == true) {
                        continue;
                    }
                    if (!isMatch(temp, wordList.get(j))) {
                        continue;
                    }
                    //注意这里是双向循环的精髓
                    if (visited1[j] == true) {
                        return count1 + count2+1;
                    }
                    queue2.addLast(wordList.get(j));
                    visited2[j] = true;
                }
            }
        }
        return 0;
    }
    //第二版，单向广度优先遍历，使用Boolean数组处理判断重复路径访问的问题。优化了
    // hashset.contains()操作的太慢
    //执行用时 :618 ms, 在所有 Java 提交中击败了16.09%的用户
    //内存消耗 :39.6 MB, 在所有 Java 提交中击败了61.68%的用户
    private int bfs2(String beginWord, String endWord, List<String> wordList,int level){
        LinkedList<String> queue = new LinkedList();
        boolean [] visited = new boolean[wordList.size()];
        queue.addLast(beginWord);
        //用下标的处理方式及其巧妙
        int idx = wordList.indexOf(beginWord);
        if (idx != -1) {
            visited[idx] = true;
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            level++;
            for (int j=0;j<size;j++){
                String  temp = queue.removeFirst();
                for (int i=0;i<wordList.size();i++){
                    if (visited[i]==true){
                        continue;
                    }
                    //这样写逻辑更清晰一下，也可以bfs()第一版那样写。效果差不多
                    if (!isMatch(temp,wordList.get(i))){
                        continue;
                    }
                    if (wordList.get(i).equals(endWord)){
                        return level+1;
                    }
                    queue.addLast(wordList.get(i));
                   visited[i]=true;
                }
            }
        }
        return 0;
    }
    //第一版，单向广度优先遍历，使用Hashset处理判断重复路径访问的问题。
    //执行用时 :1563 ms, 在所有 Java 提交中击败了5.01%的用户
    //内存消耗 :41.3 MB, 在所有 Java 提交中击败了44.06%的用户
    private int bfs(String beginWord, String endWord, List<String> wordList,int level){
        /**
         *这里和广度优先遍历有点不一样的事，这边的递归出口怎么设置，只要遍历到endWord，
         *就应该停止循环，怎么判断是否遍历到endWord，这里的处理方式是用一个Set，每次
         * 遍历一个单词应该把它添加到Set中，然后通过Set.contains().
         * 不对不对，上述这里想错了。BFS和DFS搞混了，BFS类似层次遍历，可以通过队列来处理，
         * 一般不用到递归。何来递归出口。
         * 但是也是需要想终止条件的，终止条件就是遍历到发现wordList中的某一个字符串
         * 与endWord匹配，就结束。看示例二，endWord必须在wordList才算。
         * 那么一个问题，参考链接中有使用到HashSet的数据结构，平时的BFS常规题没有用到
         * 这个数据结构。原因是本题是图的处理方式，而且是无向图方式，那么图就有可能
         * 构成环，那么怎么判断某个节点是否访问过，就需要用HashSet把访问过的接地存入，
         * 如果之前访问过，HashSet.contains()就会true，遍历的时候continue就可以了。
         * 那么之前做题的BFS为啥不需要考虑是否有环，因为之前的BFS解决的题目都是树，
         * 树的题默认是不会构成环的，所以不需要这么考虑。
         */
        LinkedList<String> queue = new LinkedList();
        HashSet<String> hashSet = new HashSet();
        queue.addLast(beginWord);
        hashSet.add(beginWord);
        while (!queue.isEmpty()){
            int size = queue.size();
            level++;
            //这个for循环是层序遍历的套路，j有可能用到，有可能用不到，有的题目要求每一
            // 层的最左边或者最右边结点时，就需要用到这个j变量。
            for (int j=0;j<size;j++){
                //这里用的是队列必须是removeFirst，别搞错了用成了栈removeLast(),找了半天。
               String  temp = queue.removeFirst();
               //这里的这个for循环的意思是，每个节点的下一个结点都需要从这个wordList
                // 中寻找。而如果之前已经匹配过了，就说明这个结点路走过了，用Hashset
                // 循环中的continue跳过。
               for (int i=0;i<wordList.size();i++){
                   if (hashSet.contains(wordList.get(i))){
                       continue;
                   }
                   if (isMatch(temp,wordList.get(i))){
                       if (wordList.get(i).equals(endWord)){
                           return level+1;
                       }
                       queue.addLast(wordList.get(i));
                       hashSet.add(wordList.get(i));
                   }
               }
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        String beginWord="a";
        String endWord="c";
        List<String> wordList= Arrays.asList("a","b","c");
//        String beginWord="hit";
//        String endWord="cog";
//        List<String> wordList= Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println(new BFSladderLength127LeetCode().
                ladderLength(beginWord, endWord, wordList));
    }
}
