package com.csharks.accountmicroservice.repository;

import com.csharks.accountmicroservice.dao.Account;
import com.csharks.accountmicroservice.enums.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findById(Long id);

    //Report mean number employee count for all Accounts
    @Query("SELECT AVG(employeeCount) FROM Account")
    Optional<Double> findMeanEmployeeCount();

    // *** Median Report is needed JPQL can give list of all employeecounts in an ordered int array, needs a second step to find the median from this ***
    @Query("SELECT employeeCount FROM Account order by employeeCount")
    int[]findMedianEmployeeCountStep1();

    //Report Maximum  employee count for all Accounts
    @Query("SELECT MAX(employeeCount) FROM Account")
    Optional<Integer> findMaxEmployeeCount();

    //Report Minimum  employee count for all Accounts
    @Query("SELECT MIN(employeeCount) FROM Account")
    Optional<Integer> findMinEmployeeCount();

    @Query(value = "SELECT id FROM account WHERE industry = :industry", nativeQuery = true)
    List<Long> findByIndustry(@Param("industry") String industry);

    List<Account> findIdByCountry(Countries country);

    @Query(value = "SELECT city FROM account", nativeQuery = true)
    List<String> getCities();

    @Query(value = "SELECT city FROM account WHERE id = :id", nativeQuery = true)
    String getCityById(@Param("id") Long id);
}
