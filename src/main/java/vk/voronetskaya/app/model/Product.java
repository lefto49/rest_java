package vk.voronetskaya.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {
    @Id
    private int id;
    private final Book book;
    private final int price;

    public Product(int id, Book book, int price) {
        this.id = id;
        this.book = new Book(book);
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return book.getQuantity();
    }

    public void decreaseAmount(int decreaseBy) {
        book.decreaseQuantity(decreaseBy);
    }
}
