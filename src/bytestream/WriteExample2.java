package bytestream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteExample2 {
    public static void main(String[] args) throws IOException {
       // Class path = WriteExample1.class;


        //System.out.println(path.getResource(path.getSimpleName() + ".class"));

        OutputStream os = new FileOutputStream("C:\\Users\\rhdeh\\OneDrive\\바탕 화면\\java\\JavaNetworkProgramming\\src\\bytestream\\folder\\test2.db");

        byte[] array = {10,20,30};

        os.write(array);


        os.flush();
        os.close();
    }
}
