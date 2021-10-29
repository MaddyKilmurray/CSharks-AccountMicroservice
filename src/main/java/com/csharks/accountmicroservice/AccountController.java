package com.csharks.accountmicroservice;

import com.csharks.accountmicroservice.dao.Account;
import com.csharks.accountmicroservice.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO findById(@PathVariable(name = "id") Long id) {
        return accountService.findById(id);
    }

    @PostMapping("/accounts")
    public AccountDTO create(@RequestBody AccountDTO accountDTO) {
        return accountService.create(accountDTO);
    }

    @DeleteMapping("/accounts/{id}")
    public void remove(@PathVariable(name = "id") Long id) {
        accountService.delete(id);
    }
}
