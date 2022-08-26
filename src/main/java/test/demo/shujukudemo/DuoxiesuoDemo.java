package test.demo.shujukudemo;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DuoxiesuoDemo {
    //创建读写锁
    private static ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();

    //创建读锁
    private static ReentrantReadWriteLock.ReadLock readLock=reentrantReadWriteLock.readLock();
    //创建写锁
    private static ReentrantReadWriteLock.WriteLock writeLock=reentrantReadWriteLock.writeLock();

    //读锁方法
    private static void read(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"得到了读锁");
            Thread.sleep(1000);
        }catch (Exception e){

        }finally {
            System.out.println(Thread.currentThread().getName()+"释放读锁");
            readLock.unlock();
        }
    }

    //写锁方法
    private static void write(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"得到了写锁");
            Thread.sleep(1000);
        }catch (Exception e){

        }finally {
            System.out.println(Thread.currentThread().getName()+"释放写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(()->read(),"T1").start();
        new Thread(()->read(),"T2").start();
        new Thread(()->write(),"T3").start();
        new Thread(()->write(),"T4").start();
    }

}
