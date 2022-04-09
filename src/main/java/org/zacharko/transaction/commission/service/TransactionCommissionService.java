package org.zacharko.transaction.commission.service;

import org.zacharko.transaction.commission.dto.TransactionCommissionDto;
import org.zacharko.transaction.commission.dto.TransactionCommissionResultDto;

public interface TransactionCommissionService
{
   TransactionCommissionResultDto calculate(TransactionCommissionDto commissionDto);
}
