package com.iprwc.iprwc_backend_project.service;

import com.iprwc.iprwc_backend_project.model.Account;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface AccountService {

    Account save(Account account);

    List<Account> findAll();

    Optional<Account> findById(String id);

    Optional<Account> findByName(String username);

    void delete(Account account);
}
