package $06_objectstream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BookObjectInputTest {
    public static void main(String[] args) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream("booklist.dat");
            ois = new ObjectInputStream(fis);

            ArrayList list = (ArrayList) ois.readObject();
            Book b1 = (Book)list.get(0);
            Book b2 = (Book)list.get(1);
            Book b3 = (Book)list.get(2);
            System.out.println("b1.toString() : " + b1.toString());
            System.out.println("b2.toString() : " + b2.toString());
            System.out.println("b3.toString() : " + b3.toString());
        } catch(Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                fis.close();

            } catch (IOException ex){
                ex.printStackTrace();
            }

        }
    }


}
