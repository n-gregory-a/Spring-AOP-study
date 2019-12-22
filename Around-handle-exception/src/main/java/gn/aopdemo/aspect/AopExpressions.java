package gn.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* gn.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    // create pointcut for getter methods
    @Pointcut("execution(* gn.aopdemo.dao.*.get*(..))")
    public void getter() {}

    // create pointcut for setter methods
    @Pointcut("execution(* gn.aopdemo.dao.*.set*(..))")
    public void setter() {}

    // create point: include package ... exclude getter/setter methods
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoNoGetterSetter() {}

}
