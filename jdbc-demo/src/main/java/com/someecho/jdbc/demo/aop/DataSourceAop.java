package com.someecho.jdbc.demo.aop;

import com.someecho.jdbc.demo.config.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {
    
    @Pointcut("!@annotation(com.someecho.jdbc.demo.annotation.Master) " +
            "&& (execution(* com.someecho.jdbc.demo.service..*.select*(..)) " +
            "|| execution(* com.someecho.jdbc.demo..service..*.get*(..)))")
    public void readPointcut() {
    
    }
    
    @Pointcut("@annotation(com.someecho.jdbc.demo.annotation.Master) " +
            "|| execution(* com.someecho.jdbc.demo.service..*.insert*(..)) " +
            "|| execution(* com.someecho.jdbc.demo.service..*.add*(..)) " +
            "|| execution(* com.someecho.jdbc.demo.service..*.update*(..)) " +
            "|| execution(* com.someecho.jdbc.demo.service..*.edit*(..)) " +
            "|| execution(* com.someecho.jdbc.demo.service..*.delete*(..)) " +
            "|| execution(* com.someecho.jdbc.demo.service..*.remove*(..))")
    public void writePointcut() {
    
    }
    
    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }
    
    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
    
    
    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
    //    @Before("execution(* com.cjs.example.service.impl.*.*(..))")
    //    public void before(JoinPoint jp) {
    //        String methodName = jp.getSignature().getName();
    //
    //        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
    //            DBContextHolder.slave();
    //        }else {
    //            DBContextHolder.master();
    //        }
    //    }
}