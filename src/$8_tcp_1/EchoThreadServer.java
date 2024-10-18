package $8_tcp_1;

import java.net.ServerSocket;
import java.net.Socket;

public class EchoThreadServer {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(10001);
            System.out.println("접속을 기다립니다.");
            while(true){
                Socket socket = serverSocket.accept();
                EchoThread echoThread = new EchoThread(socket);
                echoThread.start();
            }

        } catch (Exception e){
            System.out.println(e);
            //e.printStackTrace();
        }
    }
}
