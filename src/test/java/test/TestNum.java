package test;
public class TestNum {  
    // ��ͨ�������ڲ��า��ThreadLocal��initialValue()������ָ����ʼֵ  
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {  
        public Integer initialValue() {  
            return 0;  
        }  
    };  
//     �ڻ�ȡ��һ������ֵ  
    public int getNextNum() {  
        seqNum.set(seqNum.get() + 1);  
        return seqNum.get();  
    }
	
//	int i=0;
//    public void set(int n){
//    	this.i=n;
//    }
//    
//    public int get(){
//    	return this.i;
//    }
//    public int getNextNum() {
//    	set(get()+1);
//    	return get();
//    }  
  
    public static void main(String[] args) {  
        TestNum sn = new TestNum();  
        
//        TestNum sn1 = new TestNum();
//        
//        TestNum sn2 = new TestNum();
        // �� 3���̹߳���sn�����Բ������к�  
        TestClient t1 = new TestClient(sn);  
        TestClient t2 = new TestClient(sn);  
        TestClient t3 = new TestClient(sn);  
        t1.start();  
        t2.start();  
        t3.start();  
    }  
  
    private static class TestClient extends Thread {  
        private TestNum sn;  
  
        public TestClient(TestNum sn) {  
            this.sn = sn;  
        }  
  
        public void run() {  
            for (int i = 0; i < 3; i++) {  
                // ��ÿ���̴߳��3������ֵ  
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["  
                         + sn.getNextNum() + "]");  
            }  
        }  
    }  
}  