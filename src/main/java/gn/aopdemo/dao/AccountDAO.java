package gn.aopdemo.dao;

import gn.aopdemo.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {
    public void addAccount(Account account) {
        System.out.println(getClass() +": DOING MY DB WORK: ADDING AN ACCOUNT");
    }
}
