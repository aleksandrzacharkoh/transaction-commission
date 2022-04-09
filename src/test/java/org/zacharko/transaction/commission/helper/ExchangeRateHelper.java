package org.zacharko.transaction.commission.helper;

import lombok.experimental.UtilityClass;
import org.zacharko.transaction.commission.client.ExchangeRateResult;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class ExchangeRateHelper
{
   public static ExchangeRateResult exchangeRates() {
      Map<String, BigDecimal> exchangeRates = new HashMap<>();
      exchangeRates.put("USD", new BigDecimal("1.217582"));
      exchangeRates.put("PLN", new BigDecimal("4.570522"));

      ExchangeRateResult result = new ExchangeRateResult();
      result.setRates(exchangeRates);

      return result;
   }
}
