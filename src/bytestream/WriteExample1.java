package bytestream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteExample1 {
    public static void main(String[] args) throws IOException {
       // Class path = WriteExample1.class;


        //System.out.println(path.getResource(path.getSimpleName() + ".class"));

        OutputStream os = new FileOutputStream("C:\\Users\\rhdeh\\OneDrive\\바탕 화면\\java\\JavaNetworkProgramming\\test.db");

        byte a = 10;
        byte b = 20;
        byte c = 30;

        os.write(a);
        os.write(b);
        os.write(c);

        os.flush();
        os.close();
    }
}
