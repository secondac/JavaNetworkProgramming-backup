package $04_bytestream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriteExample4 {
    public static void main(String[] args) throws IOException {

        Writer writer = new FileWriter("C:\\Users\\rhdeh\\OneDrive\\바탕 화면\\java\\JavaNetworkProgramming\\src\\bytestream\\folder\\test4.txt");

        String string = "1234567";

        writer.write(string,1,5);
        writer.flush();
        writer.close();

    }
}
