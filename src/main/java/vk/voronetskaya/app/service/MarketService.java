package vk.voronetskaya.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vk.voronetskaya.app.model.Account;
import vk.voronetskaya.app.model.Market;
import vk.voronetskaya.app.model.Product;
import vk.voronetskaya.app.repository.MarketRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MarketService {
    @Autowired
    private MarketRepository marketRepo;
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private AccountService accountService;

    public Market getMarket() {
        var markets = marketRepo.findAll();
        if (markets.isEmpty()) {
            throw new NoSuchElementException("A market does not exist.");
        }

        return markets.get(0);
    }

    public void makeDeal(int accountId, int productId, int amount) {
        Account account;
        try {
            account = accountService.getAccountById(accountId);
        } catch (NoSuchElementException ex) {
            LOGGER.error(String.format("Cannot complete deal with account with id %d and product with id %d. %s",
                    accountId, productId, ex.getMessage()));
            throw new IllegalArgumentException("User with such an id does not exist");
        }

        Market market = getMarket();
        Optional<Product> optionalProduct = market.getProductById(productId);
        if (optionalProduct.isEmpty()) {
            LOGGER.error(String.format("Cannot complete deal with account with id %d and product with id %d as product with " +
                    "such an id does not exist", accountId, productId));
            throw new IllegalArgumentException("Product with such an id does not exist");
        }

        Product product = optionalProduct.get();
        if (product.getAmount() < amount) {
            LOGGER.error(String.format("Cannot sell %d items of product with id %d to account with id %d as there" +
                    " are not enough items in store", amount, productId, accountId));
            throw new IllegalArgumentException("There are not enough items of product in store");
        }

        if (account.getBalance() < (long) product.getPrice() * amount) {
            LOGGER.error(String.format("Cannot sell %d items of product with id %d to account with id %d as there" +
                    " is not enough money at account's disposal", amount, productId, accountId));
            throw new IllegalArgumentException("There is not enough money at account's disposal");
        }

        product.decreaseAmount(amount);
        marketRepo.deleteAll();
        marketRepo.insert(market);
        accountService.updateAccount(account, (long) product.getPrice() * amount);
    }

    public void createMarket(Market market) {
        marketRepo.insert(market);
    }
}
