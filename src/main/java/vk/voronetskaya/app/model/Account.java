package vk.voronetskaya.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "account")
public class Account {
    @Id
    private long id;
    private final List<Book> books;
    private long balance;

    public Account(long balance, List<Book> books) {
        this.books = books;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public long getBalance() {
        return balance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void decreaseBalance(long decreaseBy) {
        balance -= decreaseBy;
    }
}
