package thread;

public class FirstThreadTest2 {
    public static void main(String[] args) {
        Thread t = new Thread(new FirstThread2());
        t.start();
    }
}
