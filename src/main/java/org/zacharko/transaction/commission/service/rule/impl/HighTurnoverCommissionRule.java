package org.zacharko.transaction.commission.service.rule.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zacharko.transaction.commission.db.entity.TransactionDao;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;
import org.zacharko.transaction.commission.service.TransactionService;
import org.zacharko.transaction.commission.service.rule.CommissionCalculationRule;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Slf4j
public class HighTurnoverCommissionRule implements CommissionCalculationRule
{
   private final BigDecimal highTurnoverLimit;

   private final BigDecimal highTurnoverCommission;

   private final TransactionService transactionService;

   public HighTurnoverCommissionRule(@Value("${transaction.commission.rule.highTurnover.limit}") BigDecimal highTurnoverLimit,
                                     @Value("${transaction.commission.rule.highTurnover.commission}") BigDecimal highTurnoverCommission,
                                     TransactionService transactionService)
   {
      this.highTurnoverLimit = highTurnoverLimit;
      this.highTurnoverCommission = highTurnoverCommission;
      this.transactionService = transactionService;
   }


   @Override
   public BigDecimal getCommission(TransactionCommissionDto commissionDto)
   {
      BigDecimal result;
      BigDecimal transactionAmountForLastMonth = getTransactionAmountForLastMonth(commissionDto.getDate(), commissionDto.getClientId());
      if (transactionAmountForLastMonth.compareTo(highTurnoverLimit) > 0) {
         log.info("Client with id {} reach high turnover limit now is {}", commissionDto.getClientId(), transactionAmountForLastMonth);
         result = highTurnoverCommission;
      } else {
         log.info("Client with id {} don't reach high turnover limit, now is {}", commissionDto.getClientId(), transactionAmountForLastMonth);
         result = new BigDecimal("100000000000000000000"); // TODO This value is a big that can be bigger than another rules. But it can cause a bugs, so need to change approach
      }
      log.info("HighTurnoverCommissionRule: Commission for transaction data {} is {}", commissionDto, result);
      return result;
   }

   private BigDecimal getTransactionAmountForLastMonth(LocalDate date, Integer clientId) {
      return transactionService.getClientTransactionForCurrentMonth(date, clientId)
            .stream()
            .map(TransactionDao::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
   }
}
