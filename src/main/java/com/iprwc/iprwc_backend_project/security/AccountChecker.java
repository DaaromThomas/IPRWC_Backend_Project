package com.iprwc.iprwc_backend_project.security;

import com.iprwc.iprwc_backend_project.controller.AccountController;
import com.iprwc.iprwc_backend_project.model.Account;
import com.iprwc.iprwc_backend_project.model.RoleType;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountChecker
{
    private final AccountController accountController;

    public AccountChecker(AccountController accountController) {
        this.accountController = accountController;
    }

    public boolean checkAccount(String accountId){
        if (accountId == null) {
            return false;
        }

        Account account;
        if (!this.checkIfAccountExists(accountId)) {
            return false;
        }

        account = this.accountController.getById(accountId);
        if (account.getRole() != RoleType.Admin){
            return false;
        }

        return true;
    }

    public boolean checkIfAccountExists(String id){
        if (id != null){
            return this.accountController.getById(id) != null;
        } else {
            return false;
        }

    }
}
