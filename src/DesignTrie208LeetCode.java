/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 * @author LemonLin
 * @Description :DesignTrie208LeetCode
 * @date 20.3.28-19:09
 * 思路：https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution
 /trie-tree-de-shi-xian-gua-he-chu-xue-zhe-by-huwt/
参考材料：https://juejin.im/post/5c2c096251882579717db3d2
https://zhuanlan.zhihu.com/p/28891541
 * 本题是一种前缀树的实现。
 */
public class DesignTrie208LeetCode {
    /** Initialize your data structure here. */
    //结点类
    class TreeNode{
        //因为search方法要搜索是否到尾结点。所以需要一个标记
        boolean isEnd ;
        //这个是前缀树的核心，每个节点都是一个数组,数组元素是树节点。
        TreeNode [] childrens;

        TreeNode (){
            isEnd = false;
            //这里有点难以理解，可以类比int [] text = new int[26];
            childrens = new TreeNode[26];
        }
   }
   //这个相当于以下函数的全局变量，在每个方法中都要用到
    TreeNode root ;
    public DesignTrie208LeetCode() {
        root = new TreeNode() ;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode node  = root;
        for (char c:word.toCharArray()){
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

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        //注意这个函数传入的word很可能和insert传入的word是不一样的字符串
        TreeNode node = root;
        //搜索要是刚刚好全部匹配才能返回true，不能只匹配其中一些字符
        for (char c:word.toCharArray()){
            //给定字符串某个字符找不到，就说明没有匹配到
            //这里为啥不能直接写node.childrens[c-'a'] == null，要多用一层node？
            // 因为这样试把节点变化到孩子节点了，下一轮循环的时候就能够比较下一个字符
            node = node.childrens[c-'a'];
            if (node ==null){
                return false;
            }
        }
        //for循环都没有发生false的情况，就返回isEnd 因为可能不是最后一个节点
        return node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode node = root;
        for (char c:prefix.toCharArray()){
            node = node.childrens[c-'a'];
            if (node==null){
                return false;
            }
        }
        //这个函数和search很像，但是又不一样，主要区别在是否返回isEnd，找前缀是不需
        // 要看是否是最后一个节点的，只要给定的prefix遍历结束了，都在原来的word上能
        // 找到，就应该返回true
        return true;
    }
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
}
