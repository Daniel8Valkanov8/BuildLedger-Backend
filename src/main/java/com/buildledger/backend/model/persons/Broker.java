package com.buildledger.backend.model.persons;

import com.buildledger.backend.model.abstraction.Person;
import com.buildledger.backend.model.ledger.Sell;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Broker extends Person {
    private final String type = "broker";
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "broker")
    private Set<Sell> sells = new HashSet<>();

    public String getType() {
        return type;
    }

    public Set<Sell> getSells() {
        return sells;
    }

    public void setSells(Set<Sell> sells) {
        this.sells = sells;
    }
}
