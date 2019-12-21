package gn.aopdemo;

import gn.aopdemo.dao.AccountDAO;
import gn.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // get membership bean from spring container
        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        // call the business method
        Account account = new Account();
        account.setName("Marry");
        account.setLevel("MegaVIP");
        accountDAO.addAccount(account, true);
        accountDAO.doWork();

        // call the accountDao getter/setter methods
        accountDAO.setName("fooBar");
        accountDAO.setServiceCode("silver");
        String name = accountDAO.getName();
        String code = accountDAO.getServiceCode();

        // call the membership business method
        membershipDAO.addSillyMember();
        membershipDAO.goToSleep();

        // close the context
        context.close();
    }

}
