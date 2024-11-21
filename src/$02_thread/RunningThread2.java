package $02_thread;

public class RunningThread2 extends Thread{
    public void run(){
        try{
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("RunningThread2 작동 중");
                Thread.sleep(200);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("스레드 종료");

    }
}
