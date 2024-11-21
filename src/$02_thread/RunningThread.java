package $02_thread;

public class RunningThread extends Thread{
    boolean stopFlag = false;
    public void run(){
        while(!stopFlag){
            System.out.println("스레드 작동 중");
            try{
                Thread.sleep(250);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void stopThread(){
        stopFlag = true;
    }
}
