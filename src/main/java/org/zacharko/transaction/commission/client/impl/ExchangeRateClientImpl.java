package org.zacharko.transaction.commission.client.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.zacharko.transaction.commission.client.ExchangeRateClient;
import org.zacharko.transaction.commission.client.ExchangeRateResult;
import org.zacharko.transaction.commission.exception.ExchangeServiceUnreachableException;

import java.util.Optional;

@Component
@Slf4j
public class ExchangeRateClientImpl implements ExchangeRateClient
{
   private final String exchangeClientURL;

   public ExchangeRateClientImpl(@Value("${transaction.commission.exchange.client.url}") String exchangeClientURL)
   {
      this.exchangeClientURL = exchangeClientURL;
   }

   public ExchangeRateResult getExchangeRates() throws ExchangeServiceUnreachableException
   {
      RestTemplate exchangeRateTemplate = new RestTemplate();
      log.info("Trying for get exchange rates from {}", exchangeClientURL);
      Optional<ExchangeRateResult> exchangeRates = Optional.ofNullable(exchangeRateTemplate.getForObject(exchangeClientURL, ExchangeRateResult.class));
      if (exchangeRates.isPresent())
      {
         log.info("Exchange rates result status {}", exchangeRates.get().getSuccess());
      } else {
         log.info("Exchange rates result is empty");
         throw new ExchangeServiceUnreachableException();
      }
      return exchangeRates.get();
   }
}
