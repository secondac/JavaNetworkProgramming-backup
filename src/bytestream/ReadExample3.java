package bytestream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample3 {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("C:\\Users\\rhdeh\\OneDrive\\바탕 화면\\java\\JavaNetworkProgramming\\src\\bytestream\\folder\\test2.db");

        byte[] buffer = new byte[5];
        // int i = 0;

        while(true){
            int data = is.read(buffer,1,3);
            if(data == -1)
                break;
            for(int i = 0; i < buffer.length; i++){
                System.out.println(buffer[i]);
            }
        }

        is.close();

    }
}
