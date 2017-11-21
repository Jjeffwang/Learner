package multi.thread.synchronize;

import sun.applet.Main;

import javax.xml.bind.SchemaOutputResolver;

/**
 * Created by jeff on 2017/11/14.
 */
public class Task {

    private String getData1;

    private String getData2;

    public synchronized void dolongTimeTask() {

        //sout
        try {
            System.out.println("begin task");
            Thread.sleep(3000);
            getData1 = "长时间处理任务返回结果1：" + Thread.currentThread().getName();
            getData1 = "长时间处理任务返回结果2：" + Thread.currentThread().getName();
            System.out.println(getData1);
            System.out.println(getData1);
            System.out.println("end task");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //psvm
    public static void main(String[] args) {
        Task task=new Task();
        Mythread1 mythread1=new Mythread1(task);
        mythread1.start();
        Mythread2 mythread2=new Mythread2(task);
        mythread2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long beginTime=CommounUtils.beginTime1;
        if(CommounUtils.beginTime2<CommounUtils.beginTime1){
            beginTime=CommounUtils.beginTime2;
        }
        long endTime=CommounUtils.endTime1;
        if(CommounUtils.endTime2>CommounUtils.endTime1){
            endTime=CommounUtils.endTime2;
        }
        System.out.println("耗时："+(endTime-beginTime)/1000);
    }
}
