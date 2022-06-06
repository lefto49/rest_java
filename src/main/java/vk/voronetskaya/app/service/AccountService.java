package vk.voronetskaya.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vk.voronetskaya.app.model.Account;
import vk.voronetskaya.app.repository.AccountRepository;

import java.util.NoSuchElementException;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepo;

    public Account getAccountById(int id) {
        var account = accountRepo.findById(id);
        if (account.isEmpty()) {
            throw new NoSuchElementException("An account with such an id does not exist.");
        }

        return account.get();
    }

    public void createAccount(Account account) {
        accountRepo.insert(account);
    }

    public void configureId(Account account) {
        int index = (int)(accountRepo.count() - 1);
        account.setId(accountRepo.findAll().get(index).getId() + 1);
    }
}
