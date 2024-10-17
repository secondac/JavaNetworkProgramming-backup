package assignment2;

import java.io.Serializable;

public class Book implements Serializable {

    // transient private Long isbn;
    private Long isbn;
    private String title;
    private int edition;
    private String author;
    private Double price;
    private int year;
    private String publisher;
    private int pages;


    public Book(Long isbn, String title, int edition, String author, int year, Double price, String publisher, int pages) {
        this.isbn = isbn;
        this.title = title;
        this.edition = edition;
        this.author = author;
        this.year = year;
        this.price = price;
        this.pages = pages;
        this.publisher = publisher;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getTitle(){
        return this.title;
    }

    public int getEdition(){
        return this.edition;
    }

    public int getYear(){
        return this.year;
    }

    public Long getIsbn(){
        return this.isbn;
    }

    public Double getPrice(){
        return this.price;
    }

    public String getPublisher(){
        return this.publisher;
    }

    public int getPages(){
        return this.pages;
    }



    public void setAuthor(String author){
        this.author = author;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setIsbn(Long isbn){
        this.isbn = isbn;
    }

    public void setEdition(int edition){
        this.edition = edition;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setPrice(Double price){
        this.price = price;
    }


    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public void setPages(int pages){
        this.pages = pages;
    }

    public String toString(){
        return  "Isbn = " + getIsbn() +
                ", Title = " + getTitle() +
                ", Edition = " + getEdition() +
                ", Author = " + getAuthor() +
                ", Year = " + getYear() +
                ", Price = " + getPrice() +
                ", Pages = " + getPages() +
                ", Publisher = " + getPublisher();
    }

}
