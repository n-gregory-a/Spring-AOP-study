package gn.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Pointcut("execution(* gn.aopdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    // create pointcut for getter methods
    @Pointcut("execution(* gn.aopdemo.dao.*.get*(..))")
    private void getter() {}

    // create pointcut for setter methods
    @Pointcut("execution(* gn.aopdemo.dao.*.set*(..))")
    private void setter() {}

    // create point: include package ... exclude getter/setter methods
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoNoGetterSetter() {}


    @Before("forDaoNoGetterSetter()")
    public void beforeAddAccountAdvice() {

        System.out.println("\n=======>>> Execution @Before advice on addAccount()");
    }

    @Before("forDaoNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n=====>>> Perform API analytics");
    }
}
