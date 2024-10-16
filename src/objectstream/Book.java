package objectstream;

import java.io.Serializable;

public class Book implements Serializable {

    transient private String isbn;
    private String title;
    private String author;
    private int price;

    // Constructor
    public Book(String isbn, String title, String author, int price) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getTitle(){
        return this.title;
    }

    public String getIsbn(){
        return this.isbn;
    }

    public int getPrice(){
        return this.price;
    }


    public void setAuthor(String author){
        this.author = author;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public String toString(){
        return "Isbn(transient) = " + getIsbn() +", Title = " + getTitle() + ", Author = " + getAuthor() + ", Price = " + getPrice();
    }



    public static void main(String[] args) {
    }
}
