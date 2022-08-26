package test.demo;

public class Duoxianc implements Runnable { //extends Thread

    @Override
    public void run() {
        for (int i = 0; i <1000 ; i++) {
            System.out.println("vip"+i);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Duoxianc duoxianc = new Duoxianc();
        Thread thread = new Thread(duoxianc);
        thread.start();
        for (int i = 0; i <200 ; i++) {
            if (i==50){
                thread.join();
            }
            System.out.println("普通用户"+i);
        }
    }


//    public static void main(String[] args){
//        Thread thread1 = new Thread(){
//            @Override
//            public void run(){
//                try {
//                    Thread.sleep(500);//让线程休息500毫秒
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName());//打印出当前线程名
//            }
//        };
//
//        thread1.start();
//    }

}


