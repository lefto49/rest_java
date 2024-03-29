package vk.voronetskaya.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Optional;

@Document(collection = "market")
public class Market {
    @Id
    private int id;
    private List<Product> products;

    public Market() {

    }

    public Market(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products.stream().filter(x -> x.getAmount() > 0).toList();
    }

    public Optional<Product> getProductById(int id) {
        var foundProducts = products.stream().filter(x -> x.getId() == id).toList();
        if (foundProducts.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(foundProducts.get(0));
    }

    public void setId(int id) {
        this.id = id;
    }
}
