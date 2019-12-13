package gn.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public void addAccount() {
        System.out.println(getClass() + ": Doing stuff: ADDING A MEMBERSHIP ACCOUNT");
    }
}
