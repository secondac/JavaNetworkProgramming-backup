package $08_tcp_1;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class EchoThread extends Thread {
    private Socket socket;

    public EchoThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println(inetAddress.getHostAddress() + "로 부터 접속하였습니다.");
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;

            while ((line = br.readLine())!=null){
                System.out.println("클라이언트로부터 전송받은 문자열: "+ line);
                pw.println(line);
                pw.flush();
            }

            pw.close();
            br.close();
            out.close();
            in.close();



        } catch ( Exception e ) {
            System.out.println(e);
        }
    }
}
