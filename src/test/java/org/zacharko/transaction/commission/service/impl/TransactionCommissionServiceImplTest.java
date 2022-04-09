package org.zacharko.transaction.commission.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.zacharko.transaction.commission.dto.TransactionCommissionResultDto;
import org.zacharko.transaction.commission.exception.CurrencyNotFoundException;
import org.zacharko.transaction.commission.exception.ExchangeServiceUnreachableException;
import org.zacharko.transaction.commission.service.TransactionCommissionService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_EUR;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_EUR_small_amount;

@SpringBootTest
class TransactionCommissionServiceImplTest
{

   @Autowired
   private TransactionCommissionService transactionCommissionService;

   @Value("${transaction.commission.response.currency}")
   private String defaultCurrency;

   @Test
   public void transactionCommissionService_calculate_defaultCommissionRule() throws ExchangeServiceUnreachableException, CurrencyNotFoundException
   {
      TransactionCommissionResultDto commissionResult = transactionCommissionService.calculate(transactionCommission_EUR());

      assertEquals(0, new BigDecimal("0.5").compareTo(commissionResult.getAmount()));
      assertEquals(defaultCurrency, commissionResult.getCurrency());
   }

   @Test
   public void transactionCommissionService_calculate_defaultCommissionRule_smallAmount() throws ExchangeServiceUnreachableException, CurrencyNotFoundException
   {
      TransactionCommissionResultDto commissionResult = transactionCommissionService.calculate(transactionCommission_EUR_small_amount());

      assertEquals(0, new BigDecimal("0.05").compareTo(commissionResult.getAmount()));
      assertEquals(defaultCurrency, commissionResult.getCurrency());
   }
}