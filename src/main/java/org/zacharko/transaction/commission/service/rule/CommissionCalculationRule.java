package org.zacharko.transaction.commission.service.rule;

import org.zacharko.transaction.commission.dto.TransactionCommissionDto;

import java.math.BigDecimal;

public interface CommissionCalculationRule
{
   BigDecimal getCommission(TransactionCommissionDto commissionDto);
}
