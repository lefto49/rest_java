package vk.voronetskaya.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
public class Book {
    @Id
    private int id;
    private String name;
    private String author;
    private int quantity;

    public Book() {

    }

    public Book(Book other) {
        this.id = other.id;
        this.name = other.name;
        this.author = other.author;
        this.quantity = other.quantity;
    }

    public Book(String name, String author, int quantity) {
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decreaseQuantity(int decreaseBy) {
        quantity -= decreaseBy;
    }

    public void setId(int id) {
        this.id = id;
    }
}
