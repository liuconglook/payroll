package com.belean.payroll.impl.affiliation;

import com.belean.payroll.domain.Affiliation;
import com.belean.payroll.transaction.PayCheck;

/**
 * @author belean
 * @date 2021/10/8
 */
public class NoAffiliation implements Affiliation {
    @Override
    public double calculateDeductions(PayCheck payCheck) {
        return 0d;
    }
}
