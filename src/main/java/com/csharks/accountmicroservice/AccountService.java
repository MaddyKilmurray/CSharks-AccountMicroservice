package com.csharks.accountmicroservice;

import com.csharks.accountmicroservice.dao.Account;
import com.csharks.accountmicroservice.dto.AccountDTO;
import com.csharks.accountmicroservice.enums.Countries;
import com.csharks.accountmicroservice.enums.Industry;
import com.csharks.accountmicroservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public AccountDTO findById(Long id) {
        Optional<Account> foundAccount = accountRepository.findById(id);
        if (!foundAccount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Requested account could not be found.");
        }
        return convertToAccountDTO(foundAccount.get());
    }

    public AccountDTO create(AccountDTO accountDTO) {
        Optional<Account> checkAccount = accountRepository.findById(accountDTO.getId());
        if (checkAccount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account already exists.");
        }
        return convertToAccountDTO(accountRepository.save(convertToAccount(accountDTO)));
    }

    public void delete(Long id) {
        Optional<Account> foundAccount = accountRepository.findById(id);
        if (!foundAccount.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Requested account could not be found.");
        }
        accountRepository.delete(foundAccount.get());
    }

    public List<String> listOfCities() {
        return accountRepository.getCities();
    }

    public String getCityById(Long id) {
        return accountRepository.getCityById(id);
    }

    public Double findMeanEmployeeCount() {
        Optional<Double> mean = accountRepository.findMeanEmployeeCount();
        return mean.get();
    }

    public Long findMaxEmployeeCount() {
        Optional<Integer> max = accountRepository.findMaxEmployeeCount();
        return Long.valueOf(max.get());
    }

    public Long findMinEmployeeCount() {
        Optional<Integer> min = accountRepository.findMinEmployeeCount();
        return Long.valueOf(min.get());
    }

    public Long getMedianEmployeeCount(){
        int[] medianStep1 = accountRepository.findMedianEmployeeCountStep1();
        try {
            int sizeOfArray = medianStep1.length;
            if (sizeOfArray % 2 == 1) {
                return Long.valueOf(medianStep1[(sizeOfArray + 1) / 2 - 1]);
            } else {
                return Long.valueOf((medianStep1[sizeOfArray / 2 - 1] + medianStep1[sizeOfArray / 2]) / 2);
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return Long.valueOf(0);
        }
    }

    public List<Long> getListIdByIndustry(String industry){
        return accountRepository.findByIndustry(industry);
    }
    public List<Long> getListIdByCountry(Countries country){
        List<Account> accountList = accountRepository.findIdByCountry(country);
        List<Long> listId = new ArrayList<Long>();
        for(Account account: accountList) {
            listId.add(account.getId());
        }
        return listId;
    }

    public AccountDTO convertToAccountDTO(Account account) {
        AccountDTO convertedAccount = new AccountDTO(account.getId(), account.getIndustry().toString(),
                account.getEmployeeCount(),account.getCity(),account.getCountry().toString());
        return convertedAccount;
    }

    public Account convertToAccount(AccountDTO account) {
        Account convertedAccount = new Account(account.getId(), Industry.valueOf(account.getIndustry()),
                account.getEmployeeCount(),account.getCity(), Countries.getCountry(account.getCountry()));
        return convertedAccount;
    }

    public List<Long> getListIdByCity(String city){
        return accountRepository.findByCity(city);
    }
}
