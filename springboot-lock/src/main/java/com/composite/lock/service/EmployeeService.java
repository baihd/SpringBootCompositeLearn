package com.composite.lock.service;

import com.composite.lock.dao.EmployeeDao;
import com.composite.lock.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 未加锁
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public Integer increaseMoney(Integer id) {
        Employee employee = employeeDao.findById(id);
        final Integer oldMoney = employee.getMoney();
        LOGGER.info("oldMoney: {}", oldMoney);
        employee.setMoney(oldMoney + 1);
        //更新的行数
        Integer updateId = employeeDao.updateEmployee(employee);
        return updateId;
    }

    /**
     * 悲观锁
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public Integer increaseMoneyWithPessimisticLock(Integer id) {
        Employee employee = employeeDao.findByIdWithPessimisticLock(id);
        final Integer oldMoney = employee.getMoney();
        LOGGER.info("oldMoney: {}", oldMoney);
        employee.setMoney(oldMoney + 1);
        Integer updateId = employeeDao.updateEmployee(employee);
        return updateId;
    }

    /**
     * 失败尝试
     *
     * @param id
     */
    public void increaseMoneyWithOptimisticLock(Integer id) {
        int tryTimes = 0;
        while (true) {
            tryTimes++;
            if (internalIncreaseMoneyWithOptimisticLock(id) != 0) {
                // 说明更新成功，直接退出
                break;
            }
            if (tryTimes == 200) {
                // 达到最大重试次数，退出
                break;
            }
            try {
                // 休息一段时间后再重试
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        LOGGER.info("tryTimes: {}", tryTimes);
    }

    /**
     * 查找Employee对象，并进行更新
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public Integer internalIncreaseMoneyWithOptimisticLock(Integer id) {
        Employee employee = employeeDao.findById(id);
        final Integer oldMoney = employee.getMoney();
        LOGGER.info("oldMoney: {}", oldMoney);
        employee.setMoney(oldMoney + 1);
        return employeeDao.updateEmployeeWithOptimisticLock(employee);
    }
}
