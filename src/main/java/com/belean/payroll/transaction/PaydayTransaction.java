package com.belean.payroll.transaction;

import com.belean.payroll.database.impl.MockDatabase;
import com.belean.payroll.database.PayrollDatabase;
import com.belean.payroll.abstraction.Transaction;
import com.belean.payroll.domain.Employee;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author belean
 * @date 2021/10/9
 */
public class PaydayTransaction implements Transaction {

    private Date payDate;
    private Map<Integer, PayCheck> payChecks = new HashMap<>();

    private PayrollDatabase payrollDatabase = new MockDatabase();

    public PaydayTransaction(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public void execute() {
        Map<Integer, Employee> employees = payrollDatabase.employees();
        employees.forEach((empId, employee) -> {
            if (employee.isPayDate(payDate)) {
                PayCheck payCheck = new PayCheck(employee.getPayPeriodStartDate(payDate), payDate);
                payChecks.put(empId, payCheck);
                employee.payDay(payCheck);
            }
        });
    }

    public PayCheck getPaycheck(int empId) {
        return payChecks.get(empId);
    }
}
