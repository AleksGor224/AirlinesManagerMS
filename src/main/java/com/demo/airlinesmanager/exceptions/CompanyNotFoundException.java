package com.demo.airlinesmanager.exceptions;

import lombok.Getter;

@Getter
public class CompanyNotFoundException extends RuntimeException {

    private final Integer errCode = 2;
    private final String errDesc = "Company not found";
    private final String companyId;

    public CompanyNotFoundException(String msg, String companyId) {
        super(msg);
        this.companyId = companyId;
    }
}