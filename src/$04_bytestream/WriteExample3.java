package $04_bytestream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriteExample3 {
    public static void main(String[] args) throws IOException {

        Writer writer = new FileWriter("C:\\Users\\rhdeh\\OneDrive\\바탕 화면\\java\\JavaNetworkProgramming\\src\\bytestream\\folder\\test3.txt");

        char[] charArray = {'A','B','C','D','E','F','G','H'};

        writer.write(charArray,1,5);
        writer.flush();
        writer.close();

    }
}
