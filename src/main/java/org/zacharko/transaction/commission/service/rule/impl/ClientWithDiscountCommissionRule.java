package org.zacharko.transaction.commission.service.rule.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;
import org.zacharko.transaction.commission.service.rule.CommissionCalculationRule;

import java.math.BigDecimal;
import java.util.Set;

@Component
@Slf4j
public class ClientWithDiscountCommissionRule implements CommissionCalculationRule
{
   private final Set<Integer> clientWithDiscountIds;

   private final BigDecimal clintWithDiscountCommission;

   public ClientWithDiscountCommissionRule(@Value("#{'${transaction.commission.rule.clientDiscount.ids}'.split(',')}") Set<Integer> clientWithDiscountIds,
                                           @Value("${transaction.commission.rule.clientDiscount.commission}") BigDecimal clintWithDiscountCommission)
   {
      this.clientWithDiscountIds = clientWithDiscountIds;
      this.clintWithDiscountCommission = clintWithDiscountCommission;
   }

   @Override
   public BigDecimal getCommission(TransactionCommissionDto commissionDto)
   {
      BigDecimal result;
      if (clientWithDiscountIds.contains(commissionDto.getClientId())) {
         log.info("Client with id {} found on client discounts list", commissionDto.getClientId());
         result = clintWithDiscountCommission;
      } else {
         log.info("Client with id {} don't found on client discounts list", commissionDto.getClientId());
         result = new BigDecimal("100000000000000000000"); // TODO This value is a big that can be bigger than another rules. But it can cause a bugs, so need to change approach
      }
      log.info("ClientWithDiscountCommissionRule: Commission for transaction data {} is {}", commissionDto, result);
      return result;
   }
}
