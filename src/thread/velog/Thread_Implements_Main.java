package thread.velog;

public class Thread_Implements_Main {
    public static void main(String[] args) {

        Thread thread = new Thread(new Thread_Implements());
        thread.start();

        System.out.println("thread id = " + thread.threadId());
        System.out.println("thread name(default) =" + thread.getName());
        System.out.println("thread priority = " + thread.getPriority());
        System.out.println("thread state: " + thread.getState());

    }
}
