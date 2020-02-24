import java.util.ArrayList;
import java.util.List;
/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 * 示例:
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 * @author LemonLin
 * @Description :BackTrackingpartition131LeetCode
 * @date 20.1.21-12:22
 * 思路：本题的第二个内循环的遍历写法和LeetCode93如出一辙，可以看看93。
 * 递归过程和LeetCode78子集很相似，就是判断回文串，这个还没想到用什么简单
 * 的方法处理。暂时用前后对比。
 * 对比257的写法，到底递归出口要怎么写何时写，剪枝条件要何时写，怎么写
 * 参考：https://leetcode-cn.com/problems/palindrome-partitioning/solution/
 * hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/
 * 用添加路径的方式记录分割方案，然后递归出口条件是遍历起点等于字符串总长的时候。
 * 剪枝条件是传入的子字符串不是回文串的时候进行剪枝。
 * 注意这里的start 和i的变化过程，很有东西
 *  start   i     substring(start,i+1)    temp
 *  0
 *  --------------------------------------------
 *            0
 *  --------------------------------------------
 *                             0,1                      [a]
 *  --------------------------------------------
 *  1(i+1=start)
 *    --------------------------------------------
 *            1
 *  --------------------------------------------
 *                             1,2                      [a],[a]
 *  --------------------------------------------
 *  2(i+1=start)
 *   --------------------------------------------
 *          2
 *   --------------------------------------------
 *                           2,3                     [a],[a],[b]
 *     --------------------------------------------
 *  3(i+1=start)
 *   --------------------------------------------
 *   start=s.length(),把temp添加到result中去。
 *   --------------------------------------------
 *   开始回溯
 *   --------------------------------------------
 *   2     2                 运行remove       [a],[a]
 *   --------------------------------------------
 *          3  不满足i<s.length();退出i=2循环（这里要注意是i初始值是2，然后i++到3
 *          之后，判断了i<s.length(),不满足，立刻回退循环，所以退出的是i=2循环）
 *   --------------------------------------------
 *  1      1                运行remove        [a]
 *    --------------------------------------------
 *   1     2          1 ,3（ab）剪枝contine [a]
 *    --------------------------------------------
 *          3不满足i<s.length();退出i=1循环
 *    --------------------------------------------
 *  0     0                 运行remove        []
 *  --------------------------------------------
 *  0
 *    --------------------------------------------
 *           1
 *    --------------------------------------------
 *                          0,2                     aa
 *    --------------------------------------------
 *    2(i+1=start)
 *    --------------------------------------------
 *           2
 *    --------------------------------------------
 *                         2,3                    [aa],[b]
 *    --------------------------------------------
 *    3(i+1=start)
 *    --------------------------------------------
 *   start=s.length(),把temp添加到result中去。
 *    --------------------------------------------
 * 开始回溯
 *  --------------------------------------------
 *  2     2                 运行remove         [aa]
 *   --------------------------------------------
 *         3 不满足i<s.length();退出i=2
 *   --------------------------------------------
 *   0    1                  运行remove          []
 *   --------------------------------------------
 *          2
 *     --------------------------------------------
 *                          0,3(aab)剪枝contine  []
 *   --------------------------------------------
 *         3不满足i<s.length();退出i=start=0
 *  --------------------------------------------
 *  return result;结束
 *  --------------------------------------------
 * 0                        0               0,1                      [a]
 * 1                        1               1,2                      [a],[a]
 * 2                        2               2,3                      [a],[a],[b]
 * 3      start=s.length(),把temp添加到result中去。
 * 开始回溯（也是难点）
 * 2                        2                运行remove                 [a],[a]
 *                           3              不满足i<s.length()退出。 [a],[a]
 * 1                        1              运行remove                   [a]
 * 1                        2          1 ,3（ab）剪枝contine        [a]
 *                          3  不满足i<s.length();退出i=1循环
 *  0                       0                 运行remove                []
 *  0                       1                  0,2                           aa
 *  2(i+1=start)     2                  2,3                    [aa],[b]
 *  3(i+1=start)       start=s.length(),把temp添加到result中去
 * 开始回溯
 *  2                      2                 运行remove         [aa]
 *                          3 不满足i<s.length();退出i=2
 * 0                        1                  运行remove              []
 * 0                       2                  0,3(aab)剪枝contine  []
 *                          3不满足i<s.length();退出i=start=0
 *                          return result;结束
 */
public class BackTrackingpartition131LeetCode {
    ArrayList result = new ArrayList();
    public List<List<String>> partition(String s) {
        helperPartition(s,new ArrayList(),0);
        return  result;
    }
    private void helperPartition(String s, ArrayList temp,int index){
        if(index ==s.length()){
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i=index;i<s.length();i++){
            //注意这里子串为啥是index,i+1这里的写法和LeetCode93的写法很像，可以这
            // 么想，每个节点中，可以放的字符串是以index为开头的，遍历的字符串，又因
            // 为substring(a,b)不能算进去b，所以写成了 s.substring(index,i+1);本来意思
            // 是截取[index,i]。为什么不能写[index,index+1]这种写法就不和逻辑，首先在
            // 这个for循环里面，正常来说就应该写i,利用i变量。那么为啥不能写成[i,i+1],这
            // 种写法也是不科学的，因为本题的题目要求是子串，所以对于每个结点来说，开头
            // 应该是固定的(相对固定的，细品)。又因为每次节点的开头又不一样，所以用index。
            String  substring = s.substring(index,i+1);
            if (!isPalindrome(substring)){
                continue;
            }
            temp.add(substring);
            helperPartition(s,temp,i+1);
            temp.remove(temp.size()-1);
        }
    }
    private boolean isPalindrome(String s){
        int left=0;
        //注意这里是s.length()-1,right是下标，所以要-1
        int right=s.length()-1;
        while (left<right){
            if (s.charAt(left)==s.charAt(right)){
                left++;
                right--;
            }else {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new BackTrackingpartition131LeetCode().partition("aab"));
    }
}
