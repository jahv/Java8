package own.test.threads;

/**
 * Created by ahernandez on 10/19/16.
 */
public class MainThreads {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getId());

        for(int i=0; i<5; i++) {
            Runnable r = () -> {
                for (int j = 1; j <= 3; j++) {
                    System.out.println(Thread.currentThread().getId() + " [" + j + "]");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
