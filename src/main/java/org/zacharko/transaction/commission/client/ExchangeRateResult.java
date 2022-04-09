package org.zacharko.transaction.commission.client;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class ExchangeRateResult
{
   private Boolean success;
   private Boolean historical;
   private String base;
   private String date;
   private Map<String, BigDecimal> rates;
}
