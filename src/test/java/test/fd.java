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
        // �̳߳�
        ExecutorService exec = Executors.newCachedThreadPool();
        // ֻ��5���߳�ͬʱ����
        final Semaphore semp = new Semaphore(5);
        // ģ��20���ͻ��˷���
        for (int index = 0; index < 50; index++) {
        	
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // ��ȡ���
                    	System.out.println("====");
                        semp.acquire();
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 6000));
                        // ��������ͷ�
                        semp.release();
                        //availablePermits()ָ���ǵ�ǰ�źŵƿ����ж��ٸ����Ա�ʹ��
                        System.out.println("-----------------" + semp.availablePermits()); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        // �˳��̳߳�
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