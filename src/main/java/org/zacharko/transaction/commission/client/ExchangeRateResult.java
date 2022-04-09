package org.zacharko.transaction.commission.client;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ExchangeRateResult
{
   private Boolean success;
   private Boolean historical;
   private String base;
   private String date;
   private Map<String, BigDecimal> rates;
}
