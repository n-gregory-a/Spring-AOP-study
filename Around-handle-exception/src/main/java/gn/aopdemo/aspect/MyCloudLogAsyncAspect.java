package gn.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyCloudLogAsyncAspect {

    @Before("gn.aopdemo.aspect.AopExpressions.forDaoNoGetterSetter()")
    public void loadToCloudAsync() {
        System.out.println("\n=======>>> Logging to Cloud in async fashion");
    }


}
