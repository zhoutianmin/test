package test.demo;

public class DuoxiancTest {

    public static String a = "aaaaa";

    public static void toaa(){
        System.out.println("aaa");
    }
//        public synchronized void test1()
//        {
//            System.out.println(Thread.currentThread().getName()+" test1()");
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        public static void main(String[] args) {
//            DemoTest test = new DemoTest();
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    test.test1();
//                }
//            });
//
//            Thread thread1 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    test.test1();
//                }
//            });
//            //当thread线程正在执行test1方法的时候，thread1线程就得等着；
//            //等到thread执行完毕，thread1才能执行，但是thread1怎么才能知道thread执行完毕呢，就得不断去轮询。
//            //轮询的效率是比较低的，那有没有更高效率的办法呢，wait以及notify就是解决这个问题的。
//            thread.start();
//            thread1.start();
//        }

    public void test1()
    {
        while(true) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " test1()");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notify();
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DuoxiancTest test = new DuoxiancTest();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                test.test1();
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.test1();
            }
        });
        //thread执行完毕，唤醒thread1，阻塞自己；
        //thread1执行完毕，唤醒thread，阻塞自己。
        //如此，周而复始，不断循环。
        thread.start();
        thread1.start();
    }

}
