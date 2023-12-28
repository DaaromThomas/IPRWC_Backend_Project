package com.iprwc.iprwc_backend_project.service;

import com.iprwc.iprwc_backend_project.Dao.AccountRepository;
import com.iprwc.iprwc_backend_project.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService
{
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return (List<Account>) this.accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(String id) {
        return this.accountRepository.findById(id);
    }

    @Override
    public Optional<Account> findByName(String username) {
        return this.accountRepository.findByName(username);
    }

    @Override
    public void delete(Account account) {
        this.accountRepository.delete(account);
    }
}
