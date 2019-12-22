package gn.aopdemo.aspect;

import gn.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Around("execution(* gn.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("\n=====>>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            // log the exception
            logger.warning(e.getMessage());

            // rethrow exception
            throw e;
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        logger.info("\n======>>> Duration: " + duration/1000 + " seconds");

        return result;
    }

    @After(value = "execution(* gn.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyAccountsAdvice(JoinPoint joinPoint) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=====>>>> Executing @After (finally) on method: " + method);


    }

    @AfterThrowing(
            pointcut = "execution(* gn.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc"
    )
    public void afterThrowingFindAccountsAvvice(
            JoinPoint joinPoint, Throwable exc
    ) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=====>>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        logger.info("\n=====>>>> The exception is: " + exc);

    }

    // add a new advice for @AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut = "(execution(* gn.aopdemo.dao.AccountDAO.findAccounts(..)))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=====>>>> Executing @AfterReturning on method: " + method);

        // print out the resulting of the method call
        logger.info("\n=====>>>> result is: " + result);

        // let's post-process the data ... let's modify it :-)

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        logger.info("\"\\n=====>>>> result is: \" + result");
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
        logger.info("\n=======>>> Execution @Before advice on addAccount()");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        logger.info("Method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = joinPoint.getArgs();

        // loop thru args
        for (Object tenpArg: args
             ) {
            logger.info(tenpArg.toString());

            if (tenpArg instanceof Account) {
                // downcast and print Account specific stuff
                Account account = (Account) tenpArg;

                logger.info(("account name: " + account.getName()));
                logger.info(("account level: " + account.getLevel()));
            }
        }

    }

}
