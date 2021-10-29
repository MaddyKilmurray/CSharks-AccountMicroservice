package com.csharks.accountmicroservice;

import com.csharks.accountmicroservice.dao.Account;
import com.csharks.accountmicroservice.dto.AccountDTO;
import com.csharks.accountmicroservice.enums.Countries;
import com.csharks.accountmicroservice.enums.Industry;
import com.csharks.accountmicroservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (foundAccount.isEmpty()) {
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
        if (foundAccount.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Requested account could not be found.");
        }
        accountRepository.delete(foundAccount.get());
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
}
