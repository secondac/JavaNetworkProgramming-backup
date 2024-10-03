package bytestream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample2 {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("C:\\Users\\rhdeh\\OneDrive\\바탕 화면\\java\\JavaNetworkProgramming\\src\\bytestream\\folder\\test2.db");

        byte[] buffer = new byte[100];
        // int i = 0;

        while(true){
            int data = is.read(buffer);
            if(data == -1)
                break;
            for(int i = 0; i < data; i++){
                System.out.println(buffer[i]);
            }
        }

        is.close();

    }
}
