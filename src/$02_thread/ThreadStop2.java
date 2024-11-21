package $02_thread;

public class ThreadStop2 {
    public static void main(String[] args) {
        RunningThread2 t2 = new RunningThread2();
        t2.start();
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        t2.interrupt();
    }
}
