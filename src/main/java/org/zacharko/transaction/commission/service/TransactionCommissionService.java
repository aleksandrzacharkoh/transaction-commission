package org.zacharko.transaction.commission.service;

import org.zacharko.transaction.commission.dto.TransactionCommissionDto;
import org.zacharko.transaction.commission.dto.TransactionCommissionResultDto;
import org.zacharko.transaction.commission.exception.CurrencyNotFoundException;
import org.zacharko.transaction.commission.exception.ExchangeServiceUnreachableException;

public interface TransactionCommissionService
{
   TransactionCommissionResultDto calculate(TransactionCommissionDto commissionDto) throws CurrencyNotFoundException, ExchangeServiceUnreachableException;
}
