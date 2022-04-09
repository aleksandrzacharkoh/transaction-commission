package org.zacharko.transaction.commission.service.rule.exchange.impl;

import org.springframework.stereotype.Service;
import org.zacharko.transaction.commission.client.ExchangeRateClient;
import org.zacharko.transaction.commission.client.ExchangeRateResult;
import org.zacharko.transaction.commission.dto.TransactionCommissionDto;
import org.zacharko.transaction.commission.exception.CurrencyNotFoundException;
import org.zacharko.transaction.commission.exception.ExchangeServiceUnreachableException;
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

   public BigDecimal getExchangedResult(TransactionCommissionDto commissionDto) throws CurrencyNotFoundException, ExchangeServiceUnreachableException
   {
      ExchangeRateResult exchangeRates = exchangeRateClient.getExchangeRates();
      if (!exchangeRates.getRates().containsKey(commissionDto.getCurrency())) {
         throw new CurrencyNotFoundException(commissionDto.getCurrency());
      }

      BigDecimal exchangeRate = exchangeRates.getRates().get(commissionDto.getCurrency());
      return commissionDto.getAmount().multiply(exchangeRate);
   }

}
