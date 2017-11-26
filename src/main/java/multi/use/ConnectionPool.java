package multi.use;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by jeff on 2017/11/26.
 * 简单数据库连接池实例
 */
public class ConnectionPool {

    private LinkedList<Connection> pool=new LinkedList<Connection>();

    public ConnectionPool(int initialSize){

        if(initialSize>0){

            for(int i=0;i<initialSize;i++){
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection){
        if(connection!=null){
            synchronized (pool){
                pool.addLast(connection);
                //连接池释放后进行通知这样其他的消费者能够感知到线程池中已经归还了一个连接
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnetion(long mills) throws InterruptedException {

        synchronized (pool){
            if(mills<0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();

            }else{
               long future=System.currentTimeMillis()+mills;
               long remaining=mills;
                while (pool.isEmpty()&&remaining>0){
                    pool.wait(remaining);
                    remaining=future-System.currentTimeMillis();
                }
                Connection result=null;
                if(!pool.isEmpty()){
                    result=pool.removeFirst();
                }
                return result;
            }
        }

    }
}
