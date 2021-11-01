package com.csharks.accountmicroservice.dao;

import com.csharks.accountmicroservice.enums.Countries;
import com.csharks.accountmicroservice.enums.Industry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
public class Account {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated(EnumType.STRING)
        private Industry industry;
        @Column(name="employee_count")
        private Integer employeeCount;

        private String city;
        private Countries country;

        public Account(Industry industry, int employeeCount, String city, Countries country) {
            setIndustry(industry);
            setEmployeeCount(employeeCount);
            setCity(city);
            setCountry(country);
        }

        public void setEmployeeCount(int employeeCount) {
        if (employeeCount <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Employee count must be positive. Please try again.");
        }

        this.employeeCount = employeeCount;
        }


    public void setCity(String city) {
        if (city.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No city input. Please try again.");
        }
        else if(!city.matches("[a-zA-Z\\u00C0-\\u00FF]+")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "City can not contain numbers. Please try again.");

        } else if(city.length()>25){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exceeds maximum value of 25 characters. Please try again.");
        }

        this.city = city;
    }

}
