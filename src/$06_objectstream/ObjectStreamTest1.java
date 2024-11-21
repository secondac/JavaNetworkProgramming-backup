package $06_objectstream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class ObjectStreamTest1 {
    public static void main(String[] args) {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        Vector v = new Vector();
        Vector v1 = new Vector();
        Vector v2 = new Vector();
        Vector v3 = new Vector();

        v1.addElement(new String("Data 1"));
        v1.addElement(new String("Data 2"));
        v1.addElement(new String("Data 3"));
        v2.addElement(new String("Data 4"));
        v3.addElement(v2);
        v.addElement(v1);
        v.addElement(v3);

        try{
            fout = new FileOutputStream("Object.dat");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(v);
            oos.reset();
            System.out.println("저장 완료");
        } catch(Exception ex){
        }finally {
            try{
                oos.close();
                fout.close();
            }catch (IOException ex){}
        }
    }
}
