package multi.test;

/**
 * Created by ${WangChengYong} on 2017/11/24.
 */
public class TestC {

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            try {
                ThreadA threadA = new ThreadA();
                threadA.setName("A");
                threadA.start();
//        ThreadC threadC=new ThreadC();
//        threadC.setName("C");
//        threadC.start();
                //等1秒钟再启动B线程，不然会因为B先释放使得A一直在等待
                //等待过于被动需要解决
                Thread.sleep(1000);
                ThreadB threadB = new ThreadB();
                threadB.setName("B");
                threadB.start();
                threadA.join();
                threadB.join();
                System.out.println("-------------------------------------------------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
