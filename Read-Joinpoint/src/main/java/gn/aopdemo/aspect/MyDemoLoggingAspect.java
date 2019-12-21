package gn.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyDemoLoggingAspect {

    @Before("gn.aopdemo.aspect.AopExpressions.forDaoNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n=======>>> Execution @Before advice on addAccount()");
    }

}
