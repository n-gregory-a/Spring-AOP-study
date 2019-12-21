package gn.aopdemo.aspect;

import gn.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {

    @AfterThrowing(
            pointcut = "execution(* gn.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc"
    )
    public void afterThrowingFindAccountsAvvice(
            JoinPoint joinPoint, Throwable exc
    ) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n=====>>>> The exception is: " + exc);

    }

    // add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "(execution(* gn.aopdemo.dao.AccountDAO.findAccounts(..)))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>>> Executing @AfterReturning on method: " + method);

        // print out the resulting of the method call
        System.out.println("\n=====>>>> result is: " + result);

        // let's post-process the data ... let's modify it :-)

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\"\\n=====>>>> result is: \" + result");
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts
        for (Account account: result
             ) {
            // get uppercase version name
            String upperName = account.getName().toUpperCase();

            //update the name on the account
            account.setName(upperName);
        }



    }

    @Before("gn.aopdemo.aspect.AopExpressions.forDaoNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n=======>>> Execution @Before advice on addAccount()");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = joinPoint.getArgs();

        // loop thru args
        for (Object tenpArg: args
             ) {
            System.out.println(tenpArg);

            if (tenpArg instanceof Account) {
                // downcast and print Account specific stuff
                Account account = (Account) tenpArg;

                System.out.println(("account name: " + account.getName()));
                System.out.println(("account level: " + account.getLevel()));
            }
        }

    }

}
