package $06_objectstream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Vector;

public class ObjectStreamTest2 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("Object.dat");
            ois = new ObjectInputStream(fis);

            Vector v = (Vector) ois.readObject();
            Vector v1 = (Vector) v.get(0);
            String d1 = (String) v1.get(0);
            String d2 = (String) v1.get(1);
            String d3 = (String) v1.get(2);
            Vector v3 = (Vector) v.get(1);
            Vector v2 = (Vector) v3.get(0);
            String d4 = (String) v2.get(0);

            System.out.println(d1);
            System.out.println(d2);
            System.out.println(d3);
            System.out.println(d4);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                ois.close();
                fis.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}

