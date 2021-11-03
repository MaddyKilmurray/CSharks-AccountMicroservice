package com.csharks.accountmicroservice;

import com.csharks.accountmicroservice.dao.Account;
import com.csharks.accountmicroservice.dto.AccountDTO;
import com.csharks.accountmicroservice.enums.Countries;
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

    @GetMapping("/industry/{industry}")
    public List<Long> listIdByIndustry(@PathVariable String industry){
        return accountService.getListIdByIndustry(industry);
    }

    @GetMapping("/country/{country}")
    public List<Long> listIdByCountry(@PathVariable Countries country){
        return accountService.getListIdByCountry(country);
    }

    @GetMapping("/city")
    public List<String> getCityList() {
        return accountService.listOfCities();
    }

    @GetMapping("/city/{id}")
    public String getCityById(@PathVariable(name = "id") Long id) {
        return accountService.getCityById(id);
    }

    @PostMapping
    public AccountDTO create(@RequestBody AccountDTO accountDTO) {
        return accountService.create(accountDTO);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable(name = "id") Long id) {
        accountService.delete(id);
    }

    @GetMapping("/cityName/{city}")
    public List<Long> listIdByCity(@PathVariable String city) {
        return accountService.getListIdByCity(city);
    }
}
