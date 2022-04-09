package org.zacharko.transaction.commission.service.rule.exchange;

import org.zacharko.transaction.commission.dto.TransactionCommissionDto;

import java.math.BigDecimal;

public interface ExchangeService
{
   BigDecimal getExchangedResult(TransactionCommissionDto commissionDto);
}
