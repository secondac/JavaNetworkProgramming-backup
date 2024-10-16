package objectstream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BookObjectOutputTest {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        ArrayList list = new ArrayList();
        Book book1 = new Book("a0001", "Java완성", "홍길동", 10000);
        Book book2 = new Book("a0002", "스트럿츠", "김유신", 20000);
        Book book3 = new Book("a0003", "기초 EJB", "김성박", 25000);
        list.add(book1);
        list.add(book2);
        list.add(book3);

        try {
            fos = new FileOutputStream("booklist.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            System.out.println("저장 완료");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
