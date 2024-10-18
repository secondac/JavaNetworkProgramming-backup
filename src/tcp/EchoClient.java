package tcp;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1", 10001);
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String line = null, echo = null;
            while ((line = keyboard.readLine()) != null) {
                if(line.equals("quit"))
                    break;
                pw.println(line);
                pw.flush();
                echo = br.readLine();
                System.out.println("서버로부터 전달받은 문자열: "+echo);
            }
            pw.close();
            br.close();
            socket.close();

        } catch (Exception e){

        }
    }
}
