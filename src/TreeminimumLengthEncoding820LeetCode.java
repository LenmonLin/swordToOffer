import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
 * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#b
 * ell#" 和 indexes = [0, 2, 5]。
 * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结
 * 束，来恢复我们之前的单词列表。
 * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
 * 示例：
 * 输入: words = ["time", "me", "bell"]
 * 输出: 10
 * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 * 提示：
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * 每个单词都是小写字母 。
 * @author LemonLin
 * @Description :TreeminimumLengthEncoding820LeetCode
 * @date 20.3.28-21:29
 * 思路：参考：https://leetcode-cn.com/problems/short-encoding-of-words/
 solution/99-java-trie-tu-xie-gong-lue-bao-jiao-bao-hui-by-s/
 * 做之前可以先看一下LeetCode208题，再做会比较简单一些。
 * 自己对题解作了修改，增加一个判断前缀的方法。
 * 1、对字符串数组进行排序，长的字符串在前面。
 * 2、用前缀树保存字符，字符串顺序倒着插入
 * 3、用判断前缀的方法，如果是前缀就跳过，不计算最后的结果。
 * 问题：
 *      需要对字符串数组按照字符串长度进行排序，短的在后面，为啥要这么做，因为如果
 * 不这么做，如果先插入me,那么time就不是me的前缀，所以计算结构，me也算进去。就
 * 错误了。如果这么做了，就先插入time,遇到me,就不插入，不计算结果。
 *      最开始想的是，通过遍历最后生成的前缀树计算结果，其实想错了，题目要求计算的是
 * 全部单词的长度。如果按照遍历的做法，会产生这个bug：
 *  String[] words={"time", "atime", "btime"};    返回结果8，实际正确应该是12.
 *  为什么返回了8，因为atime 和btime中的time只计算了一次。所以发生了错误。
 */
public class TreeminimumLengthEncoding820LeetCode {
    public int minimumLengthEncoding(String[] words) {
        int result = 0;
        Trie trie = new Trie();
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length()-o1.length();
            }
        });
        for (String string :words){
            if (trie.startsWith(string)){
                continue;
            }
            //添加单词的长度
            result+=string.length();
            //添加#的长度
            result+=1;
            trie.insert(string);
        }
        return result;
    }
     class Trie {
        class TreeNode{
            boolean isEnd ;
            TreeNode [] childrens;
            TreeNode (){
                isEnd = false;
                childrens = new TreeNode[26];
            }
        }
        //这个相当于以下函数的全局变量
        TreeNode root ;
        public Trie() {
            root = new TreeNode() ;
        }
        //倒着插入单词
        public void insert(String word) {
            TreeNode node  = root;
            //这里是倒着插入。
            for (int i=word.length()-1;i>=0;i--){
                char c = word.charAt(i);
                if (node.childrens[c-'a']==null){
                    //如果当前节点的孩子节点没有存在就新建
                    node.childrens[c-'a'] = new TreeNode();
                }
                //当前节点的指针下移到孩子节点，然后进行下一轮的遍历
                node = node.childrens[c-'a'];
            }
            //for循环结束，就是最后一个trie树节点，所以要设置末尾isEnd = true
            node.isEnd = true;
        }
         public boolean startsWith(String prefix) {
             TreeNode node = root;
             //注意这里也要改成倒序遍历
             for (int i=prefix.length()-1;i>=0;i--){
                 char c = prefix.charAt(i);
                 node = node.childrens[c-'a'];
                 if (node==null){
                     return false;
                 }
             }
             return true;
         }
    }

    public static void main(String[] args) {
        String[] words={"time", "me", "bell"};
//        String[] words={"time", "atime", "btime"};
        System.out.println(new TreeminimumLengthEncoding820LeetCode().minimumLengthEncoding(words));
    }
}
