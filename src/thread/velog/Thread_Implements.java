package thread.velog;

public class Thread_Implements implements Runnable {
    int number = 11;

    public void run() {

        System.out.println("Thread_Implements run");

        Thread_Implements thread_implements = new Thread_Implements();
        thread_implements.number = this.number;

        System.out.println(thread_implements.number);
    }
}
