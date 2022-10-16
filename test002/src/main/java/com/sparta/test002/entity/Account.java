package com.sparta.test002.entity;

import com.sparta.test002.dto.AccountReqDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String phoneNumber;

    public Account(AccountReqDto accountReqDto){
        this.email = getEmail();
        this.password = getPassword();
        this.phoneNumber = getPhoneNumber();
    }

}
