package bytestream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadExample1 {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("C:\\Users\\rhdeh\\OneDrive\\바탕 화면\\java\\JavaNetworkProgramming\\src\\bytestream\\folder\\test2.db");

        while(true){
            int data = is.read();
            if(data == -1)
                break;
            System.out.println(data);
        }

    }
}
