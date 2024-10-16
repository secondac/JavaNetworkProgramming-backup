package assignment2;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * 특정한 목록을 관리(검색, 수정, 추가, 삭제)하는 프로그램
 *
 * -필수 항목
 *
 * -객체스트림, 파일스트림을 사용하여 목록을 파일에 저장.
 * -사용자 입력으로 객체를 추가하는 기능
 * -객체 개수는 20개 이상
 * -객체의 내용엔 제한이 없지만, 최소한의 현실성 반영 필요
 *
 * -기타 사항
 *
 * -목록의 자료구조, 관리기능의 구현방식은 자유
 * -단일클래스일 필요없음
 *
 * **/
public class Main extends JFrame {
    FileInputStream fis = null;
    ObjectInputStream ois = null;

    FileOutputStream fos = null;
    ObjectOutputStream oos = null;

    ArrayList<Book> list = new ArrayList<>();

    public Main(){
        Book book1 = new Book(9780321714114L, "C++ Primer", 5, "Stanley B. Lippman", 2012, 41.82 );
        Book book2 = new Book(9781449357672L, "Java Network Programming",4, "Elliotte Rusty Harold", 2013, 26.49 );
        Book book3 = new Book(9780321334879L, "Effective C++",3, "Scott Meyers", 2005, 43.73 );

        list.add(book1);
        list.add(book2);
        list.add(book3);

        sendToFile();
        receiveFromFile();

    }


    public void sendToFile(){
        try{
            fos = new FileOutputStream("books.dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            System.out.println("저장 완료");

        }catch (Exception ex){}
        finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void receiveFromFile(){
        try{
            fis = new FileInputStream("booklist.dat");
            ois = new ObjectInputStream(fis);

            ArrayList list = (ArrayList) ois.readObject();
            objectstream.Book b1 = (objectstream.Book)list.get(0);
            objectstream.Book b2 = (objectstream.Book)list.get(1);
            objectstream.Book b3 = (objectstream.Book)list.get(2);
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

    public static void main(String[] args) {
        Main main = new Main();
    }
}
