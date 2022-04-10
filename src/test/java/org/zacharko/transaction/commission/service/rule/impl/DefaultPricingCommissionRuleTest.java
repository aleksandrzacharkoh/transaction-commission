package org.zacharko.transaction.commission.service.rule.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_EUR_notVip;
import static org.zacharko.transaction.commission.helper.TransactionCommissionDtoHelper.transactionCommission_EUR_small_amount;

@RunWith(MockitoJUnitRunner.class)
class DefaultPricingCommissionRuleTest
{
   private final BigDecimal minimumCommissionAmount = new BigDecimal("0.05");

   private final BigDecimal defaultCommissionPercentage = new BigDecimal("0.005");

   private DefaultPricingCommissionRule rule;

   @BeforeEach
   public void setup() {
      rule = new DefaultPricingCommissionRule(minimumCommissionAmount, defaultCommissionPercentage);
   }

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