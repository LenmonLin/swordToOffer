package ProducerConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author LemonLin
 * @Description :ReentrantLock
 * @date 19.9.14-10:40
 * 1 线程  操作(方法)   资源类
 * 2 判断  干活             通知
 * 3 防止虚假唤醒机制
 */
class Shop{
    //商店货架还能存放的商品数量
    private int number =0;
    private Lock lock = new java.util.concurrent.locks.ReentrantLock();
    private Condition condition = lock.newCondition();

    public  void  increment(){
        try {
            lock.lock();
            //2.1判断
            while (number>=2){
                //等待，不能生产
                condition.await();
            }
            //2. 2干活
            number++;
            System.out.println("生产者线程"+Thread.currentThread().getName()+"\t"+number);
            //2.3、通知唤醒
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public  void  decrement(){
        try {
            lock.lock();
            //2.1判断
            while (number<=0){
                //等待，不能消费
                condition.await();
            }
            //2. 2干活
            number--;
            System.out.println("消费者线程"+Thread.currentThread().getName()+"\t"+number);
            //2.3、通知唤醒
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
class ProducerByReentrantLock implements Runnable{
    private Shop shop;

    public ProducerByReentrantLock(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true){
            shop.increment();
        }
    }
}
class ConsumerByReentrantLock implements Runnable{
    private Shop shop;
    public ConsumerByReentrantLock(Shop shop){
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true) {
            shop.decrement();
        }
    }
}

public class ReentrantLock {
    public static void main(String[] args) {
        Shop shop = new Shop();
        ConsumerByReentrantLock consumerByReentrantLock = new ConsumerByReentrantLock(shop);
        ProducerByReentrantLock producerByReentrantLock = new ProducerByReentrantLock(shop);
        Thread thread1 = new Thread(consumerByReentrantLock);
        Thread thread2 = new Thread(producerByReentrantLock);
        Thread thread3 = new Thread(consumerByReentrantLock);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
