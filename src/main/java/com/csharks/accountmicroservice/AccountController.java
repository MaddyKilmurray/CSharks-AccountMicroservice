package com.csharks.accountmicroservice;

import com.csharks.accountmicroservice.dao.Account;
import com.csharks.accountmicroservice.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountDTO findById(@PathVariable(name = "id") Long id) {
        return accountService.findById(id);
    }

    @GetMapping("/mean")
    public Double findMeanEmployeeCount() {
        return accountService.findMeanEmployeeCount();
    }

    @GetMapping("/max")
    public Long findMaxEmployeeCount() {
        return accountService.findMaxEmployeeCount();
    }

    @GetMapping("/min")
    public Long findMinEmployeeCount() {
        return accountService.findMinEmployeeCount();
    }

    @GetMapping("/median")
    public Long findMedianEmployeeCount() {
        return accountService.getMedianEmployeeCount();
    }

    @PostMapping
    public AccountDTO create(@RequestBody AccountDTO accountDTO) {
        return accountService.create(accountDTO);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable(name = "id") Long id) {
        accountService.delete(id);
    }
}
