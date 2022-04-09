package org.zacharko.transaction.commission.client;

import java.time.LocalDate;

public interface ExchangeRateClient
{
   ExchangeRateResult getExchangeRates(LocalDate date);
}
