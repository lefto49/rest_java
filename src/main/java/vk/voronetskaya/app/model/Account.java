package vk.voronetskaya.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "account")
public class Account {
    @Id
    private int id;
    private List<Book> books;
    private long balance;

    public Account() {

    }

    public Account(long balance, List<Book> books) {
        this.books = books;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public long getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void decreaseBalance(long decreaseBy) {
        balance -= decreaseBy;
    }
}
