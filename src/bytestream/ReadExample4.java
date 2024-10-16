package bytestream;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadExample4 {

    public static void main(String[] args) throws IOException {

        Reader reader = new FileReader("C:\\Users\\rhdeh\\OneDrive\\바탕 화면\\java\\JavaNetworkProgramming\\src\\bytestream\\folder\\test4.txt");

        while(true){
            int data = reader.read();
            if(data == -1) break;
            System.out.print((char)data);
        }

        reader.close();

    }
}
