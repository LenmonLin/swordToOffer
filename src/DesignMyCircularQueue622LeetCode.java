/**
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）
 * 原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦
 * 一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队
 * 列，我们能使用这些空间去存储新的值。
 * 你的实现应该支持如下操作：
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * 示例：
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 * 提示：
 * 所有的值都在 0 至 1000 的范围内；
 * 操作数将在 1 至 1000 的范围内；
 * 请不要使用内置的队列库。
 * @author LemonLin
 * @Description :DesignMyCircularQueue622LeetCode
 * @date 20.4.22-19:57
 * 参考：https://leetcode-cn.com/problems/design-circular-queue/solution/
 * shu-zu-shi-xian-de-xun-huan-dui-lie-by-liweiwei141/
 * 1、队列底层实现可用用数组或者链表，本题笔者选用数组实现：
 * 如果是顺序队列，进队的时候，数组下标0存放进队元素，然后rear从下标0指向1,出队的时候
 * 数组下标0元素先赋值给变量，然后front从下标0指向1，也就是虽然说队列是进队时从队尾
 * 进队，出队时从队头出队，但是实际底层用的指针都是从0一直往大的数移动。这样会出现
 * 假溢出。
 * 2、循环队列有几个需要记忆的点：
 * 首先解决循环的问题：
 *      进队 (rear+1)%maxsize
 *      出队 (front+1)%maxsize
 * 其次，是判断队空和队满的情况，
 * 判断队空用：front==rear
 * 队满的情况：(rear+1)%maxsize == front ,这里损失一个空间来区分队满和队空的情况。
 */
public class DesignMyCircularQueue622LeetCode {
    /** Initialize your data structure here. Set the size of the queue to be k. */
    private int rear ;
    private int front ;
    private int maxSize;
    private int [] queue;
    public DesignMyCircularQueue622LeetCode(int k) {
        //这里加一的原因，是看举例，如果k=3,因为要浪费一个空间来区分队空和队满，
        //而题目举例，k等于3，也可以插入三个元素，插入第四个元素才是队满，所以要加1
        maxSize = k+1;
        queue = new int [maxSize];
        rear = 0;
        front =0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        //判断是否队满
        if(isFull()){
            return false;
        }else {
            queue[rear] = value;
            rear = (rear+1)%maxSize;
            return true;
        }
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        //判断是否队空
        if (isEmpty()){
            return false;
        }else {
            front = (front+1)%maxSize;
            return true;
        }
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty())
            return -1;
        return queue[front];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        //这里应该写成队空判断，不能写队满判断，因为是返回队尾元素，只有队空的情况下
        //才应该返回-1.这里要和进队区分开来
        if (isEmpty()){
            return -1;
        }
        //这里很关键要加maxSize，否则负数会越界。
        return queue[(rear-1+maxSize)%maxSize];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        if (front==rear){
            return true;
        }
        return false;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if((rear+1)%maxSize == front){
            return true;
        }
        return false;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */