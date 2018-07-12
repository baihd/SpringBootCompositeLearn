package com.composite.controller;

import com.composite.dao.AccountDao;
import com.composite.entity.Account;
import org.beetl.sql.core.db.KeyHolder;
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
        return accountDao.all();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") int id) {
        return accountDao.unique(id);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Account getAccountById(@RequestParam("name") String name) {
        return accountDao.selectAccountByName(name);
    }

    @RequestMapping(value = "/updateAccount", method = RequestMethod.GET)
    public String updateAccount() {
        Account account = new Account();
        account.setMoney(100);
        account.setName("name3");
        account.setId(1);
        int t = accountDao.updateById(account);
        if (t == 1) {
            return account.toString();
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/postAccount", method = RequestMethod.GET)
    public String postAccount() {
        Account account = new Account();
        account.setMoney(200);
        account.setName("name4");
        account.setId(4);
        KeyHolder t = accountDao.insertReturnKey(account);
        if (t.getInt() > 0) {
            return account.toString();
        } else {
            return "fail";
        }
    }
}
