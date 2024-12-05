package com.buildledger.backend.model.abstraction;

import com.buildledger.backend.BaseEntity;
import com.buildledger.backend.model.ledger.Sell;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter// Това казва на JPA, че този клас не е самостоятелна таблица, а ще бъде базов клас
public abstract class Person extends BaseEntity {




    private String firstName;
    private String lastName;
    private String email;
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
