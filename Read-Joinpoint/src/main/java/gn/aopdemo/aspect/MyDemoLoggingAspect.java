package gn.aopdemo.aspect;

import gn.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {

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
