import java.util.HashMap;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支
 * 持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），
 * 否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限
 * 时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * 示例:
 * LRUCache cache = new LRUCache( 2 /* 缓存容量 );
*cache.put(1,1);
*cache.put(2,2);
*cache.get(1);       // 返回  1
*cache.put(3,3);    // 该操作会使得密钥 2 作废
*cache.get(2);       // 返回 -1 (未找到)
*cache.put(4,4);    // 该操作会使得密钥 1 作废
*cache.get(1);       // 返回 -1 (未找到)
*cache.get(3);       // 返回  3
*cache.get(4);       // 返回  4
 * @author LemonLin
 * @Description :DesignLRUCache146LeetCode
 * @date 20.2.28-17:14
 * 思路：参考：https://leetcode-cn.com/problems/lru-cache/solution/
 * lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/
 * 写得很好。
 * 1、首先弄懂什么是LRU算法：即(最近最少使用) 缓存机制。是一种对数据的淘汰机制。长期
 * 不被使用的数据，在未来被用到的几率也不大，所以当新的数据进来时我们可以优先把这
 * 些数据替换掉。
 * 2、然后其次思考设计这个LRU 算法的需求：get 和 put 方法必须都是 O(1)的时间复杂
 * 度，说明需要：
 * 查找快（get方法的需要 ），
 * 插入快（put方法的需要），
 * 删除快，有顺序之分(LRU的需要,淘汰最近最少使用的)。
 * 这里别看题目只有要求实现get和put方法。当缓存容量没达到上限时，只需要实现简单直
 * 接的put和get方法就够了。但是当缓存容量达到上限时，这个时候就需要删除，也就是一
 * 直put之后，总会到达一个缓存上限，这个删除时隐藏在put方法里面的。
 * 为什么要有顺序之分，第一反应是可以用很简单的给每个待访问元素设置一个计数变量，
 * 每次访问的时候加1。然后最大的那个值就是最近待访问的。
 * 1、即使这个算法能实现，这个需要每次很迅速的查找，O(1)的查找。
 * 2、但是这个算法是有问题的，因为最大的那个数不能代表最近访问的，所以必须使用让
 * 最近被访问到的有顺序，用链表实现，最近被访问的放到表头。表的第二个元素就是第二
 * 近被访问的，以此类推。链表末尾的那个就是元素最久没访问的，所以需要删除。删除之后倒数
 * 第二个最久没被访问的就变成最久没被访问的，这样就满足了需求。
 * 什么数据结构能够同时满足以上的需要：哈希表查找快，但是数据无固定顺序；链表有顺
 * 序之分，插入删除快，但是查找慢。所以结合一下，形成一种新的数据结构：哈希链表。
 * 3、先思考简单的get方法：
 *     if (key 不存在) {
 *         return -1;
 *     } else {
 *         将数据 (key, val) 提到开头；//这点很重要，一时间没想到。这一步伴随着先删除，再
 *         提到开头。
 *         return val;
 *     }
 *     因为有提到开头的操作，所以需要链表的在头部插入addFirst(),以及删除已经在头部
 *     插入的节点，所以还需要一个remove(node)方法。因为需要remove所以需要，使用双向
 *     链表，因为当前链表节点的删除需要前面的链表节点的指针。如果只需要删除某个元素，其实
 *     不需要使用双向链表，只需要两个指针就可以，一个指向待删除元素，一个指向待删除元素之
 *     前的元素。但是这个需求是要多次删除，这样每次都需要两个指针。无时无刻每个节点都需要
 *     用两个指针记录才可以，那么这不就是双向链表吗，每个节点都有两个指针。一个指向自身，
 *     一个指向前一个元素。
 *  4、再思考关键的put方法，如果原来存在，就修改value值即可。否则插入要考虑顺序，
 *  以及放入的顺序超过容量的时候的淘汰策略。
 *      先思考正常的能够放入，要放在链表开头，所以需要使用到链表的addFirst()方法，注意
 *      这里不是存入链表的尾部，因为本题认为，put的时候也算最近使用了这个结点，所以
 *      要放在链表开头，而不是放在链表结尾。hashmap中的key 和value也要对应上，
 *      value是链表的节点。如果放入的节点超过了cache容量，那么从链表最后一个结点删
 *      除，所以需要removeLast()方法。
 *      总结一下新的数据结构需要三个方法：
 *      addFirst(),
 *      remove(node),
 *      removeLast()
 *      以下是put伪代码：
 *      void put(int key, int val) {
 *            Node x = new Node(key, val);
 *     if (key 已存在) {
 *         把旧的数据删除；
 *         将新节点 x 插入到开头；
 *     } else {
 *         if (cache 已满) {
 *             删除链表的最后一个数据腾位置；
 *             删除 map 中映射到该数据的键；
 *         }
 *         将新节点 x 插入到开头；
 *         map 中新建 key 对新节点 x 的映射；
 *          }
 *      }
 * bug1:
 * 输入:
 * ["LRUCache","get","put","get","put","put","get","get"]
 * [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
 * 输出
 * [null,-1,null,-1,null,null,2,-1]
 * 预期结果
 * [null,-1,null,-1,null,null,2,6]
 */
/**
 * 这里包括三个类，最开始的时候一直想不通，导致了很多类中类的多余设置，三个类是：
 * DoubleLinkedListNode//双向链表结点类
 * DoubleList//双向链表类
 * LRUCache//本题的LRU算法类。
 */
public class DesignLRUCache146LeetCode {

    //双向链表节点
    public class DoubleLinkedListNode{
        //这里解释一下为什么要在链表中同时存储 key 和 val，而不是只存储 val”？当缓
        // 存容量已满，我们不仅仅要删除最后一个 Node 节点，还要把 map 中映射到该
        // 节点的 key 同时删除，而这个 key 只能由 Node 得到。如果 Node 结构中只存
        // 储 val，那么我们就无法得知 key 是什么，就无法删除 map 中的键，造成错误。
        int key;
        int value;
        DoubleLinkedListNode prev;
        DoubleLinkedListNode next;
        //默认的构造函数
        DoubleLinkedListNode(int key,int value){
            this.key = key;
            this.value = value;
        }
    }
    //双向链表
    class DoubleList {
        //双向链表的虚头尾节点，为了方便头尾操作，这个操作很重要，一开始没想到
        DoubleLinkedListNode head,tail;
        //需要统计双向链表的大小
        int size ;
        //addFirst()方法
        public DoubleList() {
            head = new DoubleLinkedListNode(0, 0);
            tail = new DoubleLinkedListNode(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        void addFirst(DoubleLinkedListNode node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }
        // remove(node),假设node一定存在
         void remove(DoubleLinkedListNode node){
            //设置两个临时变量更加直观
            DoubleLinkedListNode prev = node.prev;
            DoubleLinkedListNode next = node.next;
             next.prev = prev;
             prev.next = next;
            size--;
        }
        //删除链表中最后一个节点，并返回该节点,为什么要返回该节点是为了在hashmap
        //中删除对应的key
         DoubleLinkedListNode removeLast(){
            //如果是空节点，直接返回null
            if (tail.prev == head){
                return null;
            }
            DoubleLinkedListNode last = tail.prev;
            //要充分利用写过的函数
            remove(last);
            return last;
        }
         int size(){
            return size;
        }
    }
    class LRUCache {
        private  int capacity;
        HashMap<Integer,DoubleLinkedListNode> hashMap ;
        private  DoubleList cache;
        public LRUCache(int capacity) {

            //初始化双向链表
            cache = new DoubleList();

            hashMap = new HashMap<>();
            this.capacity = capacity;
        }

        /**
         *if (key 不存在) {
         *     return -1;
         * } else {
         *     将数据 (key, val) 提到开头；//这点很重要，一时间没想到。
         *     return val;
         * }
         */
        public int get(int key) {
            if (!hashMap.containsKey(key)){
                return -1;
            }
//                addFirst(hashMap.get(key));
            int value = hashMap.get(key).value;
            put(key,value);
            return value;
        }

        /**
         * void put(int key, int val) {
         *        Node x = new Node(key, val);
         * if (key 已存在) {
         *     把旧的数据删除；
         *     将新节点 x 插入到开头；
         *     更新key的新的value
         * } else {
         *     if (cache 已满) {
         *         删除链表的最后一个数据腾位置；
         *         删除 map 中映射到该数据的键；
         *     }
         *     将新节点 x 插入到开头；
         *     map 中新建 key 对新节点 x 的映射；
         *      }
         *  }
         */
        //解决bug1：这里很容易错，新旧节点千万不要搞错。新temp和旧key很容易就错了。
        public void put(int key, int value) {
            DoubleLinkedListNode temp = new DoubleLinkedListNode(key,value);
            if (hashMap.containsKey(key)){
                cache.remove(hashMap.get(key));
                cache.addFirst(temp);
                hashMap.put(key,temp);
            }else {
                if (capacity<=cache.size()){
                    DoubleLinkedListNode last = cache.removeLast();
                    hashMap.remove(last.key);
                }
                cache.addFirst(temp);
                hashMap.put(key,temp);
            }
        }
    }
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
}
