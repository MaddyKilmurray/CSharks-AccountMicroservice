package com.csharks.accountmicroservice.dto;

import com.csharks.accountmicroservice.enums.Countries;
import com.csharks.accountmicroservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;

    private String industry;
    private Integer employeeCount;

    private String city;
    private String country;
}
