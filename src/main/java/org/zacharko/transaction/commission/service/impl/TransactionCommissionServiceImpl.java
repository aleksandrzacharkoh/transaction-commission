package org.zacharko.transaction.commission.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;
import org.zacharko.transaction.commission.dto.TransactionCommissionResultDto;
import org.zacharko.transaction.commission.mapper.TransactionMapper;
import org.zacharko.transaction.commission.service.TransactionCommissionService;
import org.zacharko.transaction.commission.service.TransactionService;
import org.zacharko.transaction.commission.service.rule.CommissionCalculationRule;
import org.zacharko.transaction.commission.service.rule.exchange.ExchangeService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Set;

@Service
@Slf4j
public class TransactionCommissionServiceImpl implements TransactionCommissionService
{

   private final ExchangeService exchangeService;

   private final String defaultCurrency;

   private final Set<CommissionCalculationRule> commissionCalculationRules;
   
   private final TransactionService transactionService;

   public TransactionCommissionServiceImpl(ExchangeService exchangeService,
                                           @Value("${transaction.commission.response.currency}") String defaultCurrency, Set<CommissionCalculationRule> commissionCalculationRules, TransactionService transactionService)
   {
      this.exchangeService = exchangeService;
      this.defaultCurrency = defaultCurrency;
      this.commissionCalculationRules = commissionCalculationRules;
      this.transactionService = transactionService;
   }

   @Override
   @SneakyThrows
   public TransactionCommissionResultDto calculate(TransactionCommissionDto commissionDto)
   {
      exchangeCurrencyToDefault(commissionDto);

      // calculate rules after conversion, so every rule is calculating in a base currency
      BigDecimal minCommission = commissionCalculationRules.stream()
            .map(rule -> rule.getCommission(commissionDto))
            .min(Comparator.naturalOrder())
            .orElse(BigDecimal.ZERO);


      // assuming that we calculate commission based on previous transaction not including current on
      // It means in case with 1000 transaction per month current transaction is not included, so we save transaction date in database after calculation
      // In practice need to confirm with business before implementing

      transactionService.saveTransaction(TransactionMapper.map(commissionDto));

      return TransactionCommissionResultDto.builder()
            .amount(minCommission)
            .currency(defaultCurrency)
            .build();
   }

   @SneakyThrows
   private void exchangeCurrencyToDefault(TransactionCommissionDto commissionDto)
   {
      if (commissionDto.getCurrency().equalsIgnoreCase(defaultCurrency)) {
         log.info("Transaction currency {} is default currency so don't need converting", commissionDto.getCurrency());
      } else {
         log.info("Transaction currency {} is not a default currency so  need converting", commissionDto.getCurrency());
         BigDecimal exchangedResult = exchangeService.getExchangedResult(commissionDto);

         log.info("Convert transaction data from currency {} and value {} to new currency {} and value {}", commissionDto.getCurrency(), commissionDto.getAmount(), defaultCurrency, exchangedResult);

         // TODO probably will be better use another class after exchanging
         commissionDto.setAmount(exchangedResult);
         commissionDto.setCurrency(defaultCurrency);
      }
   }
}
