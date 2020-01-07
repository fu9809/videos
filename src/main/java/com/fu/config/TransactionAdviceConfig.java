package com.fu.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 *  AOP 切面方式配置事务
 * @author Administrator
 */
//@Aspect
//@Component
public class TransactionAdviceConfig {
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.fu.service..*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    // 配置事务拦截器
    @Bean
    public TransactionInterceptor transactionInterceptor() {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(transactionManager);
        // 设置事务属性
        DefaultTransactionAttribute tx_REQUIRED = new DefaultTransactionAttribute();
        tx_REQUIRED.setIsolationLevel(-1);    // 事务隔离级别：默认为 ISOLATION_DEFAULT(-1)
        tx_REQUIRED.setPropagationBehavior(0);   // 事务传播行为：默认为 PROPAGATION_REQUIRED(0)
        DefaultTransactionAttribute tx_REQUIRED_READONLY = new DefaultTransactionAttribute();
        tx_REQUIRED_READONLY.setReadOnly(true);     // 是否只读，默认为 false
        // 设置 NameMatchTransactionAttributeSource 名称匹配事务属性来源
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("add*", tx_REQUIRED);
        source.addTransactionalMethod("save*", tx_REQUIRED);
        source.addTransactionalMethod("insert*", tx_REQUIRED);
        source.addTransactionalMethod("delete*", tx_REQUIRED);
        source.addTransactionalMethod("update*", tx_REQUIRED);
        source.addTransactionalMethod("exec*", tx_REQUIRED);
        source.addTransactionalMethod("set*", tx_REQUIRED);
        source.addTransactionalMethod("get*", tx_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", tx_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", tx_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", tx_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", tx_REQUIRED_READONLY);
        source.addTransactionalMethod("is*", tx_REQUIRED_READONLY);
        // 设置 TransactionInterceptor 事务拦截器
        transactionInterceptor.setTransactionAttributeSource(source);
        return transactionInterceptor;
    }

    // 设置切入点
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor());
    }

}