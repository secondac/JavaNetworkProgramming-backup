package $02_thread;

public class ThreadStop {
    public static void main(String[] args) {
        RunningThread t = new RunningThread();
        t.start();
        try{
            Thread.sleep(2000);

        } catch (InterruptedException e){
            e.printStackTrace();
        }
        t.stopThread();
    }
}
