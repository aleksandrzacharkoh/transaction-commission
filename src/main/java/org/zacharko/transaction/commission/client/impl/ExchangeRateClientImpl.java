package org.zacharko.transaction.commission.client.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.zacharko.transaction.commission.client.ExchangeRateClient;
import org.zacharko.transaction.commission.client.ExchangeRateResult;
import org.zacharko.transaction.commission.exception.ExchangeServiceUnreachableException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

   @SneakyThrows
   @Cacheable("exchangeRates")
   public ExchangeRateResult getExchangeRates(LocalDate date)
   {
      RestTemplate exchangeRateTemplate = new RestTemplate();
      log.info("Trying for get exchange rates from {}", exchangeClientURL);
      String fullUrl = exchangeClientURL + DateTimeFormatter.ISO_LOCAL_DATE.format(date);
      Optional<ExchangeRateResult> exchangeRates = Optional.ofNullable(exchangeRateTemplate.getForObject(fullUrl , ExchangeRateResult.class));
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
