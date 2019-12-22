package gn.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyApiAnalyticsAspect {

    @Before("gn.aopdemo.aspect.AopExpressions.forDaoNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n=====>>> Perform API analytics");
    }


}
