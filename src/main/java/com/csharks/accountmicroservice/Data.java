package com.csharks.accountmicroservice;

import com.csharks.accountmicroservice.dao.Account;
import com.csharks.accountmicroservice.enums.Countries;
import com.csharks.accountmicroservice.enums.Industry;
import com.csharks.accountmicroservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Data {

    @Autowired
    AccountRepository accountRepository;

    public void populate(){
//        List<Account> opportunities = accountRepository.saveAll(List.of(
//                new Account(Industry.ECOMMERCE, 50, "London", Countries.UNITEDKINGDOM),
//                new Account(Industry.MANUFACTURING, 150, "Berlin", Countries.GERMANY),
//                new Account(Industry.MEDICAL, 250, "Madrid", Countries.SPAIN)
//        ));

        accountRepository.save(new Account(Industry.ECOMMERCE, 50, "London", Countries.UNITEDKINGDOM));
        accountRepository.save(new Account(Industry.MANUFACTURING, 150, "Berlin", Countries.GERMANY));
        accountRepository.save(new Account(Industry.MEDICAL, 250, "Madrid", Countries.SPAIN));

    }
}
