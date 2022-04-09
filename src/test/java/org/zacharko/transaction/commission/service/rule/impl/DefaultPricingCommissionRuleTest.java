package org.zacharko.transaction.commission.service.rule.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_EUR_notVip;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_EUR_small_amount;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class DefaultPricingCommissionRuleTest
{
   @Autowired
   private DefaultPricingCommissionRule rule;

   @Test
   public void defaultPricingCommissionRule_getCommission_calculatedAmount() {
      BigDecimal commission = rule.getCommission(transactionCommission_EUR_notVip());

      assertEquals(0, new BigDecimal("0.5").compareTo(commission));
   }

   @Test
   public void defaultPricingCommissionRule_getCommission_minAmount() {
      BigDecimal commission = rule.getCommission(transactionCommission_EUR_small_amount());

      assertEquals(0, new BigDecimal("0.05").compareTo(commission));
   }
}