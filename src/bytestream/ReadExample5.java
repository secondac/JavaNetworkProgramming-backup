package bytestream;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadExample5 {
    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("C:\\Users\\rhdeh\\OneDrive\\바탕 화면\\java\\JavaNetworkProgramming\\src\\bytestream\\folder\\test4.txt");

        char[] charArray = new char[2];

        while(true){
            int data = reader.read(charArray);
            if(data == -1) break;
            //for (int i = 0; i <data.length)

        }

        reader.close();
    }
}
