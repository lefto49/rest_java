package vk.voronetskaya.app.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vk.voronetskaya.app.service.AccountService;
import vk.voronetskaya.app.model.Account;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService service;
    private static final Logger LOGGER = LogManager.getLogger();

    @PostMapping(value = "/create", consumes = {"application/json"})
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        service.configureId(account);
        service.createAccount(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<?> getAccount(@PathVariable int id) {
        Account account;
        try {
            account = service.getAccountById(id);
        } catch (NoSuchElementException ex) {
            LOGGER.error(String.format("Got problems while trying to extract an account with id %d. %s", id, ex.getMessage()));
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
