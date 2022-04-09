package org.zacharko.transaction.commission.service.rule.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;
import org.zacharko.transaction.commission.service.rule.CommissionCalculationRule;

import java.math.BigDecimal;

@Component
@Slf4j
public class DefaultPricingCommissionRule implements CommissionCalculationRule
{

   private final BigDecimal minimumCommissionAmount;

   private final BigDecimal defaultCommissionPercentage;

   public DefaultPricingCommissionRule(@Value("${transaction.commission.rule.default.minAmount}") BigDecimal minimumCommissionAmount,
                                       @Value("${transaction.commission.rule.default.percentage}") BigDecimal defaultCommissionPercentage)
   {
      this.minimumCommissionAmount = minimumCommissionAmount;
      this.defaultCommissionPercentage = defaultCommissionPercentage;
   }


   @Override
   public BigDecimal getCommission(TransactionCommissionDto commissionDto)
   {
      BigDecimal commission = commissionDto.getAmount().multiply(defaultCommissionPercentage);
      BigDecimal result = commission.compareTo(minimumCommissionAmount) > 0 ? commission : minimumCommissionAmount;
      log.info("DefaultPricingCommissionRule: Commission for transaction data {} is {}", commissionDto, result);
      return result;
   }
}
