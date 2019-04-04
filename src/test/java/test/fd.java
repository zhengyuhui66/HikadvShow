package test;

import java.awt.Frame;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
public class fd {
	public static void main(String[] args) {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 50; index++) {
        	
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                    	System.out.println("====");
                        semp.acquire();
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 6000));
                        // 访问完后，释放
                        semp.release();
                        //availablePermits()指的是当前信号灯库中有多少个可以被使用
                        System.out.println("-----------------" + semp.availablePermits()); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();

	}
	
	    /**
	     * Prints this throwable and its backtrace to the specified print stream.
	     *
	     * @param s {@code PrintStream} to use for output
	     */
	}

class t{
	public synchronized void ts(){
		for(int i=0;i<10;i++){
			System.out.println("========="+i);
		}
		
	}
}