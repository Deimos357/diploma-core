package ua.nure.tanasiuk.routing.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.nure.tanasiuk.routing.Transaction;
import ua.nure.tanasiuk.routing.TransactionContextHolder;

@Component
@Aspect
public class TransactionalInterceptor implements Ordered {
    private static final int ORDER_BEFORE_TRANSACTIONAL = 20;

    @Override
    public int getOrder() {
        return ORDER_BEFORE_TRANSACTIONAL;
    }

    @Around("@annotation(transactional)")
    public Object proceed(ProceedingJoinPoint pjp, Transactional transactional) throws Throwable {
        try {
            Transaction transaction = transactional.readOnly() ? Transaction.READ : Transaction.WRITE;
            TransactionContextHolder.setTransactionType(transaction);
            return pjp.proceed();
        } finally {
            TransactionContextHolder.clearTransactionType();
        }
    }
}
