package ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.BlockingQueue;
/**
 * @author LemonLin
 * @Description :BlockingQueue
 * @date 19.9.14-16:33
 */
class QueueShop{

    BlockingQueue<String> blockingQueue  = null;
    private  AtomicInteger atomicInteger = new AtomicInteger();

    public QueueShop(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public   void  increment(){

        //1判断
        //2干活
        try {
            blockingQueue.put(Thread.currentThread().getName()+atomicInteger.incrementAndGet());
            System.out.println("生产者生产蛋糕"+Thread.currentThread().getName()+"当前队列内元素个数"+blockingQueue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //3通知
    }

    public   void  decrement(){
        //1判断
        //2干活
        try {
            blockingQueue.take();
            System.out.println("消费者消费蛋糕"+Thread.currentThread().getName()+"当前元素内的个数"+blockingQueue.size());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //3通知
    }
}

class ConsumerByBlockQue implements Runnable{
    private  QueueShop queue;

    public ConsumerByBlockQue(QueueShop queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true)
        queue.increment();
    }
}

class ProducerByBlockQue implements Runnable{
    private  QueueShop queue;

    public ProducerByBlockQue(QueueShop queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
            while (true){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.decrement();
            }
    }
}

public class BlockingQue {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        QueueShop queueShop = new QueueShop(blockingQueue);
        ConsumerByBlockQue consumerByBlockQue = new ConsumerByBlockQue(queueShop);
        ProducerByBlockQue producerByBlockQue = new ProducerByBlockQue(queueShop);
        ProducerByBlockQue producerByBlockQue2 = new ProducerByBlockQue(queueShop);
        new Thread(consumerByBlockQue).start();
        new Thread(producerByBlockQue).start();
        new Thread(producerByBlockQue2).start();
    }
}
