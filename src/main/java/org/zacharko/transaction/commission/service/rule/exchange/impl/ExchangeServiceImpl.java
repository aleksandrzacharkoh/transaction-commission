package org.zacharko.transaction.commission.service.rule.exchange.impl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.zacharko.transaction.commission.client.ExchangeRateClient;
import org.zacharko.transaction.commission.client.ExchangeRateResult;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;
import org.zacharko.transaction.commission.exception.CurrencyNotFoundException;
import org.zacharko.transaction.commission.service.rule.exchange.ExchangeService;

import java.math.BigDecimal;

@Service
public class ExchangeServiceImpl implements ExchangeService
{
   private final ExchangeRateClient exchangeRateClient;

   public ExchangeServiceImpl(ExchangeRateClient exchangeRateClient)
   {
      this.exchangeRateClient = exchangeRateClient;
   }

   @SneakyThrows
   public BigDecimal getExchangedResult(TransactionCommissionDto commissionDto)
   {
      ExchangeRateResult exchangeRates = exchangeRateClient.getExchangeRates(commissionDto.getDate());
      if (!exchangeRates.getRates().containsKey(commissionDto.getCurrency())) {
         throw new CurrencyNotFoundException(commissionDto.getCurrency());
      }

      BigDecimal exchangeRate = exchangeRates.getRates().get(commissionDto.getCurrency());
      return commissionDto.getAmount().multiply(exchangeRate);
   }

}
