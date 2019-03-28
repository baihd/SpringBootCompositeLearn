package com.composite.lock.utils;

import com.composite.lock.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.function.Consumer;

@Component
public class TransactionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionUtil.class);

    @Resource(name = "transactionManager1")
    private PlatformTransactionManager transactionManager;

    public boolean transact(Consumer consumer) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            consumer.accept(null);
            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            LOGGER.error("transact error: {}" + e.getMessage());
            transactionManager.rollback(status);
            return false;
        }
    }

}
