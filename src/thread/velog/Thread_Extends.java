package thread.velog;

public class Thread_Extends extends Thread{
    @Override
    public void run() {
        // super.run();
        System.out.println("Thread_Extends run");


        Thread_Extends thread = new Thread_Extends();
        System.out.println(thread.getName());
        System.out.println(thread.getPriority());
        System.out.println(thread.getState());
    }
}
