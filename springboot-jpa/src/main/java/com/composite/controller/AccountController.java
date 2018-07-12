package com.composite.controller;

import com.composite.dao.AccountDao;
import com.composite.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountDao accountDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Account> getAccounts() {
        return accountDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") int id) {
        return accountDao.findOne(id);
    }

    @RequestMapping(value = "/updateAccount/{id}", method = RequestMethod.GET)
    public String updateAccount(@PathVariable("id") int id) {
        Account account = new Account();
        account.setMoney(100);
        account.setName("name1");
        account.setId(id);
        Account account1 = accountDao.saveAndFlush(account);
        return account1.toString();
    }

    @RequestMapping(value = "/postAccount", method = RequestMethod.GET)
    public String postAccount() {
        Account account = new Account();
        account.setMoney(100);
        account.setName("name2");
        Account account1 = accountDao.save(account);
        return account1.toString();
    }

}
