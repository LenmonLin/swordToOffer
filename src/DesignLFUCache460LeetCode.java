import java.util.HashMap;
import java.util.LinkedList;

/**
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插
 * 入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个
 * 键具有相同使用频率）时，最近最少使用的键将被去除。
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 * 示例：
 * LFUCache cache = new LFUCache( 2 /* capacity (缓存容量) )
*  cache.put(1,1);
*  cache.put(2,2);
*  cache.get(1);       // 返回 1
*  cache.put(3,3);    // 去除 key 2
*  cache.get(2);       // 返回 -1 (未找到key 2)
*  cache.get(3);       // 返回 3
*  cache.put(4,4);    // 去除 key 1
*  cache.get(1);       // 返回 -1 (未找到 key 1)
*  cache.get(3);       // 返回 3
*  cache.get(4);       // 返回 4
 * @author LemonLin
 * @Description :DesignLFUCache460LeetCode
 * @date 20.4.5-11:33
 * 思路：
 * 1、在想到O(1)方法前，我想到的是用最小堆来记录最小出现次数，这个时间复杂度O(logn),
 * 因为堆排序的一次调整需要O(logN)。放弃。
 * 2、想到了利用LeetCode146的方法，一个HashMap，加一个双向链表的方法。双向链表
 * 先按照次数顺序放置，再按照最近最少使用放置。这样的话如果要删除次数一致的节点，
 * 需要遍历到末尾那个次数一致的最少使用节点，时间复杂度为O(N),放弃。
 * 3、使用两个hashMap，加上若干个双向链表，次数一致一个双向链表。时间复杂度为O(1);
 * 注意观察题目最后当存在平局（即两个或更多个键具有相同使用频率）时，最近
 * 最少使用的键将被去除。这一点描述和LeetCode146非常相像，所以还是需要使用LRU
 * 算法的实现。但是有个问题，每个元素被访问的计数值怎么被排序？
 * 用的是两个HashMap，HashMap<Integer,Node> cache类似LeetCode146为了get
 * 查找迅速。
 * 解决计数值就用到了另外一个hashmap：HashMap<Integer, LinkedList<Node>> countHashmap;
 * 这个countHashmap：key为出现次数(频率数)，value是对应出现次数串联起来的双向
 * 链表。这个双向链表就能解决题目最后的当存在平局（即两个或更多个键具有相同使用频率）
 * 时，最近最少使用的键将被去除的问题。
 * 这里还有一个容易想不明白的问题。就是如果在cache大小达到capacity的情况下，
 * 这个时候countHashmap中只有出现次数为1的双向链表上有一个节点，和出现次数为3的
 * 双向链表上面有节点三个。
 * 那么如果删除了出现次数为1的双向链表上有一个节点。那么最少出现次数没删之前为1，
 * 删除之后怎么跳到3.咋看是解决不了的问题，因为hashmap是无序的。其实这里想错了。
 * 因为你删除次数为1的双向链表上有旧节点，是什么情况下删除的，只有put的进来的节点
 * 的key是在cache的hashmap中没有的，同时这个时候cache的大小等于限定值capacity，
 * 这个时候才需要删除，那么删除之后，这个新添加的值的出现次数肯定为1，所以最少出现
 * 次数应该为1，而不是要跳到3。这个问题很容易搞混，需要想一想，别被绕进去了。
 */
public class DesignLFUCache460LeetCode {

    static  class Node{
        int key;
        int value;
        int frequent;
        public  Node(int key,int value,int frequent){
            this.key = key;
            this.value = value;
            this.frequent = frequent;
        }
    }


    static  class LFUCache{
        HashMap<Integer,Node> cache;
        HashMap<Integer, LinkedList<Node>> countHashmap;
        int capacity;
        int minCount;

        public LFUCache(int capacity) {
            cache = new HashMap<>();
            countHashmap = new HashMap<>();
            this.capacity = capacity;
        }
        /**
         *if (key 不存在) {
         *     return -1;
         * }
         * //以下的1、2、3点放在put中执行。
     *     1、将数据 (key, val,frequent)中的frequent加一放在对应countHashMap中key值
         *     为frequent+1的双向链表的开头。
     *     2、删除原先次数为frequent在countHashMap中的双向链表中的结点。
     *     3、更新cache中的Node结点，次数frequent+1。
     *     return val;
         */
        public int get(int key) {
            // 测试测出来的，capacity 可能传 0
            if (capacity == 0) {
                return -1;
            }
            if (!cache.containsKey(key)){
                return -1;
            }
            int value = cache.get(key).value;
            put(key,value);
            return value;
        }

        /**
         * void put(int key, int val) {
         * if (key 已存在) {
         *     把旧的数据删除：在旧的frequent的countHashMap 中的双向链表中
         *     将新节点 x 插入到frequent+1的countHashMap中的双向链表的开头。
         *     更新key的新的frequent，在cache中操作,更新为frequent+1。
         * } else {
         *     if (cache 已满) {
         *         删除minCount的countHashMap 中的双向链表中的最后一个数据腾位置；
         *         删除 cache 中映射到该数据的键；
         *     }
         *     将新节点 newNoede 插入到count+1的countHashMap中的双向链表的开头；
         *    放入新结点count+1到cache中。
         *      }
         *  }
         */
        public void put(int key, int value) {
            // 测试测出来的，capacity 可能传 0
            if (capacity == 0) {
                return ;
            }
            if (cache.containsKey(key)){

                //这三行都是获取参数值方便后面操作
                int frequent = cache.get(key).frequent;
                LinkedList  oldCountLinkList = countHashmap.get(frequent);
                Node node = cache.get(key);


                //1、把旧的数据删除：在旧的frequent的countHashMap 中的双向链表中
                oldCountLinkList.remove(node);
                //如果这个frequent对应的双向链表节点被刚好删空了，要更新最小出现次数
                if (node.frequent==minCount&&oldCountLinkList.size()==0){
                    minCount = node.frequent+1;
                }
                //2、将新节点 x 插入到frequent+1的countHashMap中的双向链表的开头。
                LinkedList<Node> newCountLinkList = countHashmap.get(frequent+1);
                if (newCountLinkList ==null){
                    newCountLinkList = new LinkedList<Node>();
                }

                //两步操作，一步操作双向链表，一步操作对应的countHashmap
                Node newNode = new Node(key,value,frequent+1);
                newCountLinkList.addFirst(newNode);
                //这个key易错。
                countHashmap.put(frequent+1,newCountLinkList);

                //3、更新key的新的frequent，在cache中操作,更新为frequent+1。
                cache.put(key,newNode);
            }else {
                Node newNode = new Node(key,value,1);
                if (capacity<=cache.size()){
                    //删除minCount中的的countHashMap 中的双向链表中的最后一个数据腾位置；
                    Node removeLast = countHashmap.get(minCount).removeLast();
                    //删除 cache 中映射到该数据的键；
                    cache.remove(removeLast.key);
                }
                LinkedList<Node> newCountLinkList = countHashmap.get(1);
                if (newCountLinkList ==null){
                    newCountLinkList = new LinkedList<Node>();
                }
                newCountLinkList.addFirst(newNode);
                countHashmap.put(1,newCountLinkList);
                cache.put(key,newNode);
                //因为是刚刚插入的新key的节点，所以frequent肯定为1，那么最新的最小出现
                // 次数也应该为1.
                minCount=1;
            }
        }
    }
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1,1);
        lfuCache.put(2,2);
        lfuCache.get(1);
        lfuCache.put(3,3);
        lfuCache.get(2);
        lfuCache.get(3);
        lfuCache.put(4,4);
        lfuCache.get(1);
        lfuCache.get(3);
        lfuCache.get(4);
    }
}

