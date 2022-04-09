package org.zacharko.transaction.commission.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zacharko.transaction.commission.dto.TransactionCommissionResultDto;
import org.zacharko.transaction.commission.helper.TransactionDaoHelper;
import org.zacharko.transaction.commission.service.TransactionCommissionService;
import org.zacharko.transaction.commission.service.TransactionService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_EUR_VipClient;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_EUR_notVipClient;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_EUR_small_amount;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TransactionCommissionServiceImplTest
{

   @Autowired
   private TransactionCommissionService transactionCommissionService;

   @Autowired
   private TransactionService transactionService;

   @Value("${transaction.commission.response.currency}")
   private String defaultCurrency;


   @Test
   public void transactionCommissionService_calculate()
   {
      TransactionCommissionResultDto commissionResult = transactionCommissionService.calculate(transactionCommission_EUR_notVipClient());

      assertEquals(0, new BigDecimal("0.5").compareTo(commissionResult.getAmount()));
      assertEquals(defaultCurrency, commissionResult.getCurrency());
   }

   @Test
   public void transactionCommissionService_calculate_smallAmount()
   {
      TransactionCommissionResultDto commissionResult = transactionCommissionService.calculate(transactionCommission_EUR_small_amount());

      assertEquals(0, new BigDecimal("0.05").compareTo(commissionResult.getAmount()));
      assertEquals(defaultCurrency, commissionResult.getCurrency());
   }

   @Test
   public void transactionCommissionService_calculate_vipClient()
   {
      TransactionCommissionResultDto commissionResult = transactionCommissionService.calculate(transactionCommission_EUR_VipClient());

      assertEquals(0, new BigDecimal("0.05").compareTo(commissionResult.getAmount()));
      assertEquals(defaultCurrency, commissionResult.getCurrency());
   }

   @Test
   public void transactionCommissionService_calculate_highTurnover()
   {
      transactionService.saveTransaction(TransactionDaoHelper.transaction_bigAmount());
      TransactionCommissionResultDto commissionResult = transactionCommissionService.calculate(transactionCommission_EUR_VipClient());

      assertEquals(0, new BigDecimal("0.03").compareTo(commissionResult.getAmount()));
      assertEquals(defaultCurrency, commissionResult.getCurrency());
   }
}