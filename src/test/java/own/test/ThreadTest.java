package own.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by ahernandez on 10/31/16.
 */
public class ThreadTest {

    @Ignore
    @Test
    public void WaitNotifyTest() {
        final List<Integer> sharedList = new ArrayList<>();

        Thread t1 = new Thread(()->{
            for(int i=1; i<=10; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    synchronized (sharedList) {
                        sharedList.add(i);
                        sharedList.notifyAll();
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(()->{
            for(int i=11; i<=20; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    synchronized (sharedList) {
                        sharedList.add(i);
                        sharedList.notifyAll();
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        //t2.start();

        boolean keepRunning = true;
        while(keepRunning) {
            synchronized (sharedList) {
                System.out.println("### Verify List: " + sharedList.size());
                if(sharedList.size() == 10) {
                    System.out.println("### Full list");
                    keepRunning = false;
                } else {
                    try {
                        sharedList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
