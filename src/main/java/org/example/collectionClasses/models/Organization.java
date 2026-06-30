package org.example.models;

import org.example.models.enums.OrganizationType;

public class Organization {
    private final Float annualTurnover;
    private final OrganizationType type;
    private final Address officialAddress;

    public Organization(Float annualTurnover, OrganizationType type, Address officialAddress) {
        if (annualTurnover == null || annualTurnover <= 0) {
            throw new IllegalArgumentException("Annual turnover must be greater than 0 and not null.");
        }
        if (officialAddress == null) {
            throw new IllegalArgumentException("Official address cannot be null.");
        }
        this.annualTurnover = annualTurnover;
        this.type = type;
        this.officialAddress = officialAddress;
    }

    public Float getAnnualTurnover() {
        return annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "annualTurnover=" + annualTurnover +
                ", type=" + type +
                ", officialAddress=" + officialAddress +
                '}';
    }
}
